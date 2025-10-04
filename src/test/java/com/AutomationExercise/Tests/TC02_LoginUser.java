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
    }

    @Test(priority = 2, groups = {"smoke"})
    // Optional dependency: Uncomment if you want this to run after registration
    // @Test(priority = 2, groups = {"smoke"}, dependsOnMethods = {"com.AutomationExercise.Tests.TC01_RegisterUser.registerUserTest"})
    public void loginUserTest() {
        HomePage home = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);
        AccountCreatedPage accountPage = new AccountCreatedPage(driver, wait);

        // Launch browser and verify homepage
        driver.get(Constants.BASE_URL);
        Assert.assertTrue(home.isHomePageVisible(), "Home page is not visible");

        // Click Signup/Login and verify login section
        home.clickSignupLogin();
        Assert.assertTrue(loginPage.isLoginVisible(), "Login to your account section not visible");

        // Enter correct email and password and click login
        String email = "katikutivasanthkumar6622@gmail.com";
        String password = "Vasanth@1";
        String expectedUsername = "vasanth";

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Verify logged in as username
        Assert.assertTrue(home.isLoggedIn(expectedUsername), "User is not logged in");

        // Delete account
        home.clickDeleteAccount();

        // Verify account deleted
        Assert.assertTrue(accountPage.isAccountDeletedVisible(), "Account deletion confirmation not visible");
        accountPage.clickContinueAfterDelete();
    }
}
