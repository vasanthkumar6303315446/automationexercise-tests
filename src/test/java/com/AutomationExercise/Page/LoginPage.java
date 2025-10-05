package com.AutomationExercise.Page;

import com.AutomationExercise.BaseTest.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends baseTest {
	
	@SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginToYourAccountText = By.xpath("//h2[text()='Login to your account']");
    private By emailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[text()='Login']");
    private By errorText = By.xpath("//*[contains(text(),'Your email or password is incorrect!')]");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isLoginVisible() {
        log.info("Verifying if 'Login to your account' section is visible");
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(loginToYourAccountText)).isDisplayed();
            test.get().info("'Login to your account' visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Login section not visible", e);
            test.get().fail("'Login to your account' section not visible: " + e.getMessage());
            return false;
        }
    }

    public void enterEmail(String email) {
        log.info("Entering email: {}", email);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            test.get().info("Entered email: " + email);
        } catch (Exception e) {
            log.error("Failed to enter email", e);
            test.get().fail("Failed to enter email: " + e.getMessage());
        }
    }

    public void enterPassword(String password) {
        log.info("Entering password");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            test.get().info("Entered password");
        } catch (Exception e) {
            log.error("Failed to enter password", e);
            test.get().fail("Failed to enter password: " + e.getMessage());
        }
    }

    public void clickLogin() {
        log.info("Clicking Login button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
            test.get().info("Clicked Login button");
        } catch (Exception e) {
            log.error("Failed to click Login button", e);
            test.get().fail("Failed to click Login button: " + e.getMessage());
        }
    }

    public boolean isLoginErrorVisible() {
        log.info("Checking if login error message is displayed");
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).isDisplayed();
            test.get().info("Login error visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("Login error message not visible", e);
            test.get().fail("Login error message not visible: " + e.getMessage());
            return false;
        }
    }
}
