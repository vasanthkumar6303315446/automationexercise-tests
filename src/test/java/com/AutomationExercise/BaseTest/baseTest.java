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

    @BeforeSuite(alwaysRun = true)
    public void setupReport() throws IOException {
        
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
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
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
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(Constants.BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method method) {
        
        test.set(extent.createTest(getClass().getSimpleName() + " :: " + method.getName()));
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        ExtentTest currentTest = test.get();

        if (result.getStatus() == ITestResult.FAILURE) {
            currentTest.fail("❌ Test Failed: " + result.getThrowable());

            // ✅ Screenshot on failure
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + result.getName() + "_" + System.currentTimeMillis() + ".png";
            try {
                FileUtils.copyFile(src, new File(screenshotPath));
                currentTest.addScreenCaptureFromPath("../" + screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            currentTest.pass("✅ Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            currentTest.skip("⚠️ Test Skipped: " + result.getThrowable());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println(" Extent Report generated at: " + reportPath);
        }
    }

    
    private void cleanDirectory(String folderName) throws IOException {
        File dir = new File(folderName);
        if (dir.exists()) {
            FileUtils.cleanDirectory(dir); 
        } else {
            dir.mkdir();
        }
    }
}

