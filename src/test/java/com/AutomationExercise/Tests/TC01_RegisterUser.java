package com.AutomationExercise.Tests;

import com.AutomationExercise.BaseTest.baseTest;
import com.AutomationExercise.Page.*;
import com.AutomationExercrise.utils.Constants;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01_RegisterUser extends baseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void setupWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
    }

    @Test(priority = 1, groups = {"smoke"})
    public void registerUserTest() {
        HomePage home = new HomePage(driver, wait);
        SignupLoginPage signupPage = new SignupLoginPage(driver, wait);
        SignupFormPage formPage = new SignupFormPage(driver, wait);
        AccountCreatedPage accountPage = new AccountCreatedPage(driver, wait);

        // Launch browser and verify homepage
        driver.get(Constants.BASE_URL);
        Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");

        // Click Signup/Login and verify new user section
        home.clickSignupLogin();
        Assert.assertTrue(signupPage.isNewUserSignupVisible(), "New user signup not visible");

        // Enter name/email and click signup
        String name = "vasanth kumar chowdhary katikuti";
        String email = "vasanth" + System.currentTimeMillis() + "@gmail.com";
        signupPage.enterNameAndEmail(name, email);
        signupPage.clickSignupButton();

        // Verify 'Enter Account Info' and fill form
        Assert.assertTrue(formPage.isEnterAccountInfoVisible(), "Enter account info not visible");
        formPage.fillAccountDetails("test123", "10", "October", "1990");

        // Select checkboxes
        formPage.selectCheckboxes();

        // Fill personal details
        formPage.fillPersonalDetails("Vasanth", "Kumar", "OpenAI", "123 Lane", "Block A",
                "Canada", "Ontario", "Toronto", "12345", "1234567890");

        // Click Create Account
        formPage.clickCreateAccount();

        // Verify account created
        Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Account not created");

        // Click Continue
        accountPage.clickContinue();

        // Verify logged in as username
        Assert.assertTrue(home.isLoggedIn(name), "User not logged in");

        // Delete account
        home.clickDeleteAccount();

        // Verify account deleted
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account not deleted");
        accountPage.clickContinueAfterDelete();
    }
}
