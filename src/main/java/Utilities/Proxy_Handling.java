package Utilities;

import Managers.ConfigFileReader;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Proxy_Handling {
    //********Popup Handling Function*******//
    public void proxy_handling() throws Exception{
        Thread.sleep(2000);
        Robot robot = new Robot();
        //****Entering ID in Proxy*****//
        String ProxyID = ConfigFileReader.properties.getProperty("Proxy_ID");
        StringSelection stringSelection = new StringSelection(ProxyID);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_TAB); // Switching to Password Field
        Thread.sleep(2000);

        //****Entering Password in Proxy*****//
        String ProxyPWD = ConfigFileReader.properties.getProperty("Proxy_Pwd");
        StringSelection stringSelection1 = new StringSelection(ProxyPWD);
        Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard1.setContents(stringSelection1, stringSelection1);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(4000);
    }
}
