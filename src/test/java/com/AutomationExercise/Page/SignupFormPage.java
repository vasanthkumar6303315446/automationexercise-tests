package com.AutomationExercise.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupFormPage {
    WebDriver driver;
    WebDriverWait wait;

    private By enterAccountInfoText = By.xpath("//b[text()='Enter Account Information']");
    private By passwordField = By.id("password");
    private By dayDropdown = By.id("days");
    private By monthDropdown = By.id("months");
    private By yearDropdown = By.id("years");

    private By newsletterCheckbox = By.id("newsletter");
    private By offersCheckbox = By.id("optin");

    // Personal details
    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");

    private By createAccountBtn = By.xpath("//button[text()='Create Account']");

    public SignupFormPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isEnterAccountInfoVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(enterAccountInfoText));
        return element.isDisplayed();
    }

    public void fillAccountDetails(String password, String day, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dayDropdown)).sendKeys(day);
        wait.until(ExpectedConditions.visibilityOfElementLocated(monthDropdown)).sendKeys(month);
        wait.until(ExpectedConditions.visibilityOfElementLocated(yearDropdown)).sendKeys(year);
    }

    public void selectCheckboxes() {
        wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox)).click();
        wait.until(ExpectedConditions.elementToBeClickable(offersCheckbox)).click();
    }

    public void fillPersonalDetails(String fname, String lname, String comp, String addr1, String addr2,
                                    String cntry, String st, String ct, String zip, String mob) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(company)).sendKeys(comp);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address1)).sendKeys(addr1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(address2)).sendKeys(addr2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(country)).sendKeys(cntry);
        wait.until(ExpectedConditions.visibilityOfElementLocated(state)).sendKeys(st);
        wait.until(ExpectedConditions.visibilityOfElementLocated(city)).sendKeys(ct);
        wait.until(ExpectedConditions.visibilityOfElementLocated(zipcode)).sendKeys(zip);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mobile)).sendKeys(mob);
    }

    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();
    }
}
