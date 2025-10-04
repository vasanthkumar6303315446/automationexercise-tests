package com.AutomationExercise.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupLoginPage {
    WebDriver driver;
    WebDriverWait wait;

    private By newUserSignupText = By.xpath("//h2[text()='New User Signup!']");
    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupBtn = By.xpath("//button[text()='Signup']");

    public SignupLoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isNewUserSignupVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(newUserSignupText));
        return element.isDisplayed();
    }

    public void enterNameAndEmail(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void clickSignupButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupBtn)).click();
    }
}

