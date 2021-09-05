package Utilities;

import BaseClass.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

    public static void WebCapture(WebDriver driver, String fileName) {
        try {
            BaseClass.scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES) , "image/png" , fileName);
        } catch (Exception e) {
            System.out.println("Error While Taking Screenshot "+ e.getMessage());
            e.printStackTrace();
        }
    }

}
