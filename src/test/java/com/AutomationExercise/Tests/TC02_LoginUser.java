package com.AutomationExercise.Tests;

import com.AutomationExercise.BaseTest.baseTest;
import com.AutomationExercise.Page.*;
import com.AutomationExercrise.utils.Constants;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC02_LoginUser extends baseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void setupWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
        log.info("WebDriverWait initialized with {} seconds", Constants.DEFAULT_WAIT);
    }

    @Test(priority = 2, groups = {"smoke"})
    public void loginUserTest() {
        HomePage home = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        AccountCreatedPage accountPage = new AccountCreatedPage(driver, wait);

        String email = "katikutivasanthkumar6622@gmail.com";
        String password = "Vasanth@1";
        String expectedUsername = "vasanth";

        try {
            log.info("Navigating to homepage: {}", Constants.BASE_URL);
            test.get().info("Navigating to homepage");
            driver.get(Constants.BASE_URL);
            Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");
            log.info("Homepage is visible");

            // Navigate to login
            home.clickSignupLogin();
            test.get().info("Clicked Signup/Login button");
            log.info("Clicked Signup/Login");

            Assert.assertTrue(loginPage.isLoginVisible(), "Login section not visible");
            log.info("Login section is visible");

            // Enter credentials
            loginPage.enterEmail(email);
            loginPage.enterPassword(password);
            test.get().info("Entered login credentials");
            log.info("Entered email: {} and password: {}", email, "katikutivasanthkumar@gmail.com",password,"Vasanth@1");

            loginPage.clickLogin();
            test.get().info("Clicked Login button");
            log.info("Clicked Login");

            // Verify logged in
            Assert.assertTrue(home.isLoggedIn(expectedUsername), "User is not logged in");
            test.get().pass("User logged in successfully as " + expectedUsername);
            log.info("User logged in successfully as {}", expectedUsername);

            // Delete account
            home.clickDeleteAccount();
            test.get().info("Clicked Delete Account button");
            log.info("Clicked Delete Account");

            Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account deletion confirmation not visible");
            test.get().pass("Account deleted successfully");
            log.info("Account deleted successfully");

            accountPage.clickContinueAfterDelete();
            log.info("Clicked Continue after deletion");

        } catch (Exception e) {
            log.error("Exception in loginUserTest", e);
            test.get().fail("Test failed due to exception: " + e.getMessage());
            throw e;
        }
    }
}
