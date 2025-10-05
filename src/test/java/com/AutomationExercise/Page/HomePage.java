package com.AutomationExercise.Page;

import com.AutomationExercise.BaseTest.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends baseTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isHomePageVisible() {
        log.info("Verifying if home page is visible");
        boolean visible = driver.getTitle().contains("Automation Exercise");
        log.info("Home page visibility: {}", visible);
        test.get().info("Home page visibility: " + visible);
        return visible;
    }

    public void clickSignupLogin() {
        log.info("Clicking 'Signup / Login' button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signupLoginBtn)).click();
            test.get().info("Clicked 'Signup / Login' button");
        } catch (Exception e) {
            log.error("Failed to click 'Signup / Login' button", e);
            test.get().fail("Failed to click 'Signup / Login' button: " + e.getMessage());
        }
    }

    public boolean isLoggedIn(String username) {
        log.info("Checking if user '{}' is logged in", username);
        try {
            WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs));
            boolean loggedIn = loggedInElement.getText().contains(username);
            log.info("User '{}' logged in status: {}", username, loggedIn);
            test.get().info("User '" + username + "' logged in status: " + loggedIn);
            return loggedIn;
        } catch (Exception e) {
            log.error("Failed to verify logged in status for user: {}", username, e);
            test.get().fail("Failed to verify logged in status for user '" + username + "': " + e.getMessage());
            return false;
        }
    }

    public void clickDeleteAccount() {
        log.info("Clicking 'Delete Account' button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(deleteAccountBtn)).click();
            test.get().info("Clicked 'Delete Account' button");
        } catch (Exception e) {
            log.error("Failed to click 'Delete Account' button", e);
            test.get().fail("Failed to click 'Delete Account' button: " + e.getMessage());
        }
    }
}
