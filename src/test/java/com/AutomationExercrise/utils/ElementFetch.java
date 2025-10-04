package com.AutomationExercrise.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementFetch {

    public WebElement getWebElement(WebDriver driver, String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return driver.findElement(By.id(locatorValue));
            case "xpath":
                return driver.findElement(By.xpath(locatorValue));
            case "css":
                return driver.findElement(By.cssSelector(locatorValue));
            case "name":
                return driver.findElement(By.name(locatorValue));
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }
    public List<WebElement> getWebElements(WebDriver driver, String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return driver.findElements(By.id(locatorValue));
            case "xpath":
                return driver.findElements(By.xpath(locatorValue));
            case "css":
                return driver.findElements(By.cssSelector(locatorValue));
            case "name":
                return driver.findElements(By.name(locatorValue));
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }
}
