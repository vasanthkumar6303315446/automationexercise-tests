package com.AutomationExercise.Page;

import com.AutomationExercise.BaseTest.baseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupFormPage extends baseTest {
	
	@SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;

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
        log.info("Checking if 'Enter Account Information' section is visible");
        try {
            boolean visible = wait.until(ExpectedConditions.visibilityOfElementLocated(enterAccountInfoText)).isDisplayed();
            test.get().info("'Enter Account Information' visibility: " + visible);
            return visible;
        } catch (Exception e) {
            log.error("'Enter Account Information' section not visible", e);
            test.get().fail("'Enter Account Information' section not visible: " + e.getMessage());
            return false;
        }
    }

    public void fillAccountDetails(String password, String day, String month, String year) {
        log.info("Filling account details");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(dayDropdown)).sendKeys(day);
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthDropdown)).sendKeys(month);
            wait.until(ExpectedConditions.visibilityOfElementLocated(yearDropdown)).sendKeys(year);
            test.get().info("Account details filled: day={}, month={}, year={}");
        } catch (Exception e) {
            log.error("Failed to fill account details", e);
            test.get().fail("Failed to fill account details: " + e.getMessage());
        }
    }

    public void selectCheckboxes() {
        log.info("Selecting newsletter and offers checkboxes");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(newsletterCheckbox)).click();
            wait.until(ExpectedConditions.elementToBeClickable(offersCheckbox)).click();
            test.get().info("Newsletter and offers checkboxes selected");
        } catch (Exception e) {
            log.error("Failed to select checkboxes", e);
            test.get().fail("Failed to select checkboxes: " + e.getMessage());
        }
    }

    public void fillPersonalDetails(String fname, String lname, String comp, String addr1, String addr2,
                                    String cntry, String st, String ct, String zip, String mob) {
        log.info("Filling personal details for {} {}", fname, lname);
        try {
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
            test.get().info("Personal details filled for user: " + fname + " " + lname);
        } catch (Exception e) {
            log.error("Failed to fill personal details", e);
            test.get().fail("Failed to fill personal details: " + e.getMessage());
        }
    }

    public void clickCreateAccount() {
        log.info("Clicking 'Create Account' button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();
            test.get().info("'Create Account' button clicked");
        } catch (Exception e) {
            log.error("Failed to click 'Create Account'", e);
            test.get().fail("Failed to click 'Create Account': " + e.getMessage());
        }
    }
}
