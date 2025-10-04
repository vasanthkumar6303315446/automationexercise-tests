package com.AutomationExercise.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountCreatedPage {
    WebDriver driver;
    WebDriverWait wait;

    private By accountCreatedText = By.xpath("//b[text()='Account Created!']");
    private By continueBtn = By.xpath("//a[text()='Continue']");
    private By accountDeletedText = By.xpath("//b[text()='Account Deleted!']");
    private By continueAfterDeleteBtn = By.xpath("//a[text()='Continue']");

    public AccountCreatedPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isAccountCreatedVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));
        return element.isDisplayed();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public boolean isAccountDeletedVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText));
        return element.isDisplayed();
    }

    public void clickContinueAfterDelete() {
        wait.until(ExpectedConditions.elementToBeClickable(continueAfterDeleteBtn)).click();
    }
}

