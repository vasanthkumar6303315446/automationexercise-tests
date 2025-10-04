package com.AutomationExercise.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginToYourAccountText)).isDisplayed();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public boolean isLoginErrorVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorText)).isDisplayed();
    }
}
