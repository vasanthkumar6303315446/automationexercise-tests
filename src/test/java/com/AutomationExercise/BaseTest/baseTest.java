package com.AutomationExercise.BaseTest;

import com.AutomationExercrise.utils.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class baseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;

    // ✅ SLF4J Logger
    protected static final Logger log = LoggerFactory.getLogger(baseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setupReport() throws IOException {
        log.info("Initializing ExtentReports and cleaning old reports/screenshots");
        cleanDirectory("reports");
        cleanDirectory("screenshots");

        File reportDir = new File("reports");
        if (!reportDir.exists()) reportDir.mkdir();

        reportPath = "reports/ExtentReport_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";

        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("AutomationExerciseDemo");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Tester", "Vasanth Kumar");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", System.getProperty("browser", "chrome"));

            log.info("ExtentReports initialized at {}", reportPath);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        log.info("Launching browser: {}", browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                log.error("Unsupported browser: {}", browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
        log.info("Navigated to {}", Constants.BASE_URL);
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        test.set(extent.createTest(getClass().getSimpleName() + " :: " + method.getName()));
        log.info("Starting test: {}", method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        ExtentTest currentTest = test.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test FAILED: {}", result.getName(), result.getThrowable());
            currentTest.fail("❌ Test Failed: " + result.getThrowable());

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + result.getName() + "_" + System.currentTimeMillis() + ".png";
            try {
                FileUtils.copyFile(src, new File(screenshotPath));
                currentTest.addScreenCaptureFromPath("../" + screenshotPath);
                log.info("Screenshot captured at {}", screenshotPath);
            } catch (IOException e) {
                log.error("Failed to save screenshot", e);
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            log.info("Test PASSED: {}", result.getName());
            currentTest.pass("✅ Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            log.warn("Test SKIPPED: {}", result.getName(), result.getThrowable());
            currentTest.skip("⚠️ Test Skipped: " + result.getThrowable());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("Browser closed");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();
            log.info("Extent Report generated at {}", reportPath);
        }
    }

    private void cleanDirectory(String folderName) throws IOException {
        File dir = new File(folderName);
        if (dir.exists()) {
            FileUtils.cleanDirectory(dir);
            log.info("Cleaned directory: {}", folderName);
        } else {
            dir.mkdir();
            log.info("Created directory: {}", folderName);
        }
    }
}
