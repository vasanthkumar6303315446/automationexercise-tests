package com.AutomationExercrise.utils;


import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println(" Test Suite started: " + suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println(" Test Suite finished: " + suite.getName());
    }
}