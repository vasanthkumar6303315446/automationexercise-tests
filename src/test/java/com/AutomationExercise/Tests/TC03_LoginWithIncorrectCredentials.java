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
    }

    @Test(priority = 3, groups = {"negative", "regression"})
    public void shouldDisplayErrorForInvalidLoginCredentials() {
        HomePage home = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);

        // Step 1-3: Launch browser and verify home page
        driver.get(Constants.BASE_URL);
        Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");

        // Step 4-5: Click on Signup/Login and verify login section
        home.clickSignupLogin();
        Assert.assertTrue(loginPage.isLoginVisible(), "'Login to your account' is not visible");

        // Step 6-7: Enter incorrect email and password and click login
        String invalidEmail = "invalid_user@mail.com";
        String invalidPassword = "wrongpass123";
        loginPage.enterEmail(invalidEmail);
        loginPage.enterPassword(invalidPassword);
        loginPage.clickLogin();

        // Step 8: Verify error message is visible
        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Error message is not displayed for invalid login");
    }
}
