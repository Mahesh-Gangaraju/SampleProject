package BaseClass;

import Managers.ConfigFileReader;
import Managers.WebDriverManager;
import Reporting.CucumberReport;
import Utilities.Screenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class BaseClass {

    public static Scenario scenario;
    public static WebDriver driver;

    @Before
    public void beforeHook(Scenario scenerio) throws Exception {
        System.out.println("RUNNING BEFORE HOOK");
        this.scenario = scenerio;
        System.out.println("Scenario Name: " + scenario.getName());
        killProcesses();
        driver = WebDriverManager.getDriver(ConfigFileReader.readConfigFileContents().getProperty("DriverType"));
    }

    @After
    public void afterHook() throws Exception {
        System.out.println("RUNNING AFTER HOOK");
        //Capturing Screen In case of Any Failure
        if(scenario.isFailed()){
            Screenshot.WebCapture(driver,"Failure ScreenShot");
        }
        CucumberReport cucumberReport = new CucumberReport();
        cucumberReport.generateReport();
        killProcesses();
        WebDriverManager.closeDriver();
    }

    //Below method is used to close unwanted processes which should be closed before/after running scenario.
    @SuppressWarnings("deprecation")
    public static void killProcesses() throws InterruptedException
    {
        String taskKillCommand = "TASKKILL /F /IM ";
        try
        {
           // Runtime.getRuntime().exec(taskKillCommand + "chrome.exe" + " /T");
            Runtime.getRuntime().exec(taskKillCommand + "EXCEL.exe" + " /T");
        }
        catch(Exception e)
        {

        }
    }

    //This method used to launch any .exe file
    public static boolean launchAnApplication(String folerPath, String executableFileName) {

        try{
            //Getting current active processes from task list!
            String line,pidInfo ="";
            Process p =Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                pidInfo += line;
            }
            input.close();
            if(pidInfo.contains(executableFileName)){ //Condition To Check if the process is already running
                System.out.println("Application: " + executableFileName + " is Already Running");
                return true;
            }else{
                if(Desktop.isDesktopSupported()){ // Condition to check if Desktop is Supported
                    Desktop dsktop = Desktop.getDesktop();
                    dsktop.open(new File(folerPath+executableFileName)); //Launching the required application
                    return true;
                }else{
                    System.out.println("Desktop Class Is Not Supported!");
                    return false;
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }
}
