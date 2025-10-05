package com.AutomationExercise.Page;

import com.AutomationExercise.BaseTest.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupLoginPage extends baseTest {
	
	@SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;

    private By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[text()='Signup']");

    public SignupLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isNewUserSignupVisible() {
        log.info("Checking visibility of 'New User Signup!' section");
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(newUserSignupText)).isDisplayed();
            test.get().info("'New User Signup!' visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("'New User Signup!' section not visible", e);
            test.get().fail("'New User Signup!' section not visible: " + e.getMessage());
            return false;
        }
    }

    public void enterNameAndEmail(String name, String email) {
        log.info("Entering name '{}' and email '{}'", name, email);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            test.get().info("Entered name and email for signup");
        } catch (Exception e) {
            log.error("Failed to enter name and email", e);
            test.get().fail("Failed to enter name and email: " + e.getMessage());
        }
    }

    public void clickSignupButton() {
        log.info("Clicking 'Signup' button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
            test.get().info("'Signup' button clicked");
        } catch (Exception e) {
            log.error("Failed to click 'Signup' button", e);
            test.get().fail("Failed to click 'Signup' button: " + e.getMessage());
        }
    }
}
