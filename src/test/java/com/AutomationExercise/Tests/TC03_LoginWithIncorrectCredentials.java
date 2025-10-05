package com.AutomationExercise.Tests;

import com.AutomationExercise.BaseTest.baseTest;
import com.AutomationExercise.Page.*;
import com.AutomationExercrise.utils.Constants;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC03_LoginWithIncorrectCredentials extends baseTest {

    WebDriverWait wait;

    @BeforeMethod
    public void setupWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.DEFAULT_WAIT));
        log.info("WebDriverWait initialized with {} seconds", Constants.DEFAULT_WAIT);
    }

    @Test(priority = 3, groups = {"negative", "regression"})
    public void shouldDisplayErrorForInvalidLoginCredentials() {
        HomePage home = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);

        String invalidEmail = "invalid_user@mail.com";
        String invalidPassword = "wrongpass123";

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

            Assert.assertTrue(loginPage.isLoginVisible(), "'Login to your account' section not visible");
            log.info("Login section is visible");

            // Enter invalid credentials
            loginPage.enterEmail(invalidEmail);
            loginPage.enterPassword(invalidPassword);
            test.get().info("Entered invalid login credentials");
            log.info("Entered invalid email: {} and password: {}", invalidEmail, "********");

            loginPage.clickLogin();
            test.get().info("Clicked Login button");
            log.info("Clicked Login");

            // Verify error message
            Assert.assertTrue(loginPage.isLoginErrorVisible(), "Error message not displayed for invalid login");
            test.get().pass("Error message displayed correctly for invalid login");
            log.info("Error message displayed correctly for invalid login");

        } catch (Exception e) {
            log.error("Exception in shouldDisplayErrorForInvalidLoginCredentials", e);
            test.get().fail("Test failed due to exception: " + e.getMessage());
            throw e;
        }
    }
}
