package com.AutomationExercise.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    private By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");
    private By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation Exercise");
    }

    public void clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginBtn)).click();
    }

    public boolean isLoggedIn(String username) {
        WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInAs));
        return loggedInElement.getText().contains(username);
    }

    public void clickDeleteAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountBtn)).click();
    }
}
