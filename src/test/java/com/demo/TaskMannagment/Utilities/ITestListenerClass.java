package com.demo.TaskMannagment.Utilities;

import java.io.IOException;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerClass extends Take_Screenshot implements ITestListener {

    public void onTestFailure(ITestResult result) {
        try {
            captureScreenshot(result.getTestContext().getName() + "//" + result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 