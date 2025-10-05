package com.AutomationExercise.BaseTest;

import com.AutomationExercrise.utils.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
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

    // SLF4J Logger
    protected static final Logger log = LoggerFactory.getLogger(baseTest.class);

   
    //Initialize ExtentReports
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

   
    // Launch Browser
    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        logStep("Launching browser: " + browser);
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
                logStep("Unsupported browser: " + browser);
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
        logStep("Navigated to " + Constants.BASE_URL);
    }

  
    //  Start Test Logging
    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        test.set(extent.createTest(getClass().getSimpleName() + " :: " + method.getName()));
        logStep("Starting test: " + method.getName());
    }

   
    //  Capture Test Result
    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        ExtentTest currentTest = test.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            logStep("Test FAILED: " + result.getName() + " - " + result.getThrowable());

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + result.getName() + "_" + System.currentTimeMillis() + ".png";
            try {
                FileUtils.copyFile(src, new File(screenshotPath));
                currentTest.fail("❌ Test Failed: " + result.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath("../" + screenshotPath).build());
            } catch (IOException e) {
                log.error("Failed to save screenshot", e);
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logStep("Test PASSED: " + result.getName());
            currentTest.pass("✅ Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            logStep("Test SKIPPED: " + result.getName() + " - " + result.getThrowable());
            currentTest.skip("⚠️ Test Skipped: " + result.getThrowable());
        }
    }

   
    // Close Browser
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logStep("Browser closed");
        }
    }

   
    // Flush Extent Report
    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();
            logStep("Extent Report generated at " + reportPath);
        }
    }

    
    //  Utility: Clean directories
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

  
    //  Utility: Log step in both Console & ExtentReports
    protected void logStep(String message) {
        // Console log
        log.info(message);
        // ExtentReports log
        if (test.get() != null) {
            test.get().info(message);
        }
    }

  
    //  Optional: Log step with screenshot
    protected void logStepWithScreenshot(String message) {
        log.info(message);

        if (test.get() != null && driver != null) {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = "screenshots/" + System.currentTimeMillis() + ".png";
            try {
                FileUtils.copyFile(src, new File(path));
                test.get().info(message,
                        MediaEntityBuilder.createScreenCaptureFromPath("../" + path).build());
            } catch (IOException e) {
                log.error("Failed to save screenshot", e);
            }
        }
    }
}
