package com.AutomationExercise.Page;

import com.AutomationExercise.BaseTest.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountCreatedPage extends baseTest {
	
	@SuppressWarnings("unused")		
    private WebDriver driver;
    private WebDriverWait wait;

    private By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    private By continueBtn = By.xpath("//a[text()='Continue']");
    private By accountDeletedText = By.xpath("//b[text()='Account Deleted!']");
    private By continueAfterDeleteBtn = By.xpath("//a[text()='Continue']");

    public AccountCreatedPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isAccountCreatedVisible() {
        log.info("Checking if 'Account Created!' message is visible");
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));
            boolean displayed = element.isDisplayed();
            log.info("'Account Created!' visibility: {}", displayed);
            test.get().info("'Account Created!' message visibility: " + displayed);
            return displayed;
        } catch (Exception e) {
            log.error("Failed to locate 'Account Created!' message", e);
            test.get().fail("Failed to locate 'Account Created!' message: " + e.getMessage());
            return false;
        }
    }

    public void clickContinue() {
        log.info("Clicking 'Continue' button after account creation");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
            test.get().info("Clicked 'Continue' button after account creation");
        } catch (Exception e) {
            log.error("Failed to click 'Continue' button", e);
            test.get().fail("Failed to click 'Continue' button: " + e.getMessage());
        }
    }

    public boolean isAccountDeletedVisible() {
        log.info("Checking if 'Account Deleted!' message is visible");
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText));
            boolean displayed = element.isDisplayed();
            log.info("'Account Deleted!' visibility: {}", displayed);
            test.get().info("'Account Deleted!' message visibility: " + displayed);
            return displayed;
        } catch (Exception e) {
            log.error("Failed to locate 'Account Deleted!' message", e);
            test.get().fail("Failed to locate 'Account Deleted!' message: " + e.getMessage());
            return false;
        }
    }

    public void clickContinueAfterDelete() {
        log.info("Clicking 'Continue' button after account deletion");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueAfterDeleteBtn)).click();
            test.get().info("Clicked 'Continue' button after account deletion");
        } catch (Exception e) {
            log.error("Failed to click 'Continue' button after deletion", e);
            test.get().fail("Failed to click 'Continue' button after deletion: " + e.getMessage());
        }
    }
}
