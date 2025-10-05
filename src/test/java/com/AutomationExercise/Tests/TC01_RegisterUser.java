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
        log.info("WebDriverWait initialized with {} seconds", Constants.DEFAULT_WAIT);
    }

    @Test(priority = 1, groups = {"smoke"})
    public void registerUserTest() {
        HomePage home = new HomePage(driver, wait);
        SignupLoginPage signupPage = new SignupLoginPage(driver, wait);
        SignupFormPage formPage = new SignupFormPage(driver, wait);
        AccountCreatedPage accountPage = new AccountCreatedPage(driver, wait);

        String name = "Vasanth Kumar Chowdhary Katikuti";
        String email = "vasanth" + System.currentTimeMillis() + "@gmail.com";

        try {
            log.info("Launching homepage: {}", Constants.BASE_URL);
            test.get().info("Navigating to homepage");
            driver.get(Constants.BASE_URL);
            Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");
            log.info("Homepage is visible");

            // Signup/Login
            home.clickSignupLogin();
            log.info("Clicked Signup/Login");
            test.get().info("Clicked Signup/Login button");
            Assert.assertTrue(signupPage.isNewUserSignupVisible(), "New user signup not visible");
            log.info("New user signup section visible");

            // Enter name/email and click signup
            signupPage.enterNameAndEmail(name, email);
            test.get().info("Entered Name and Email: " + name + " / " + email);
            log.info("Entered Name: {} and Email: {}", name, email);

            signupPage.clickSignupButton();
            log.info("Clicked Signup button");
            test.get().info("Clicked Signup button");

            // Fill account info
            Assert.assertTrue(formPage.isEnterAccountInfoVisible(), "Enter account info not visible");
            log.info("Enter Account Info section visible");
            test.get().info("Filling account details");

            formPage.fillAccountDetails("test123", "10", "October", "1990");
            formPage.selectCheckboxes();
            formPage.fillPersonalDetails("Vasanth", "Kumar", "OpenAI", "123 Lane", "Block A",
                    "Canada", "Ontario", "Toronto", "12345", "1234567890");
            log.info("Filled account and personal details");

            // Create account
            formPage.clickCreateAccount();
            log.info("Clicked Create Account");
            test.get().info("Clicked Create Account button");

            Assert.assertTrue(accountPage.isAccountCreatedVisible(), "Account not created");
            log.info("Account created successfully");
            test.get().pass("Account created successfully");

            // Continue
            accountPage.clickContinue();
            log.info("Clicked Continue after account creation");

            // Verify logged in
            Assert.assertTrue(home.isLoggedIn(name), "User not logged in");
            log.info("User logged in as: {}", name);
            test.get().info("User logged in successfully");

            // Delete account
            home.clickDeleteAccount();
            log.info("Clicked Delete Account");
            test.get().info("Clicked Delete Account button");

            Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account not deleted");
            log.info("Account deleted successfully");
            test.get().pass("Account deleted successfully");

            accountPage.clickContinueAfterDelete();
            log.info("Clicked Continue after account deletion");

        } catch (Exception e) {
            log.error("Exception in registerUserTest", e);
            test.get().fail("Test failed due to exception: " + e.getMessage());
            throw e;
        }
    }
}
