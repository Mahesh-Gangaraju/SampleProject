package Utilities;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GenericFunctions {

//    Author: Mahesh Gangaraju
//    Method Description: It is used to get the current date in required format
//    Required Parameters:
//                          dateFormat : Required Date Format
//                          Allowed Strings:
//                            For Day: dd
//                            For Month : MM //Output: 01
//                                        MMMM //Output: January(i.e. Complete Name of the Moth)
//                            For Year : yy //Output: 21
//                                       yyyy //Output: 2021
//                            For Hours/Minutes/Seconds: HH/mm/ss
//                            For day of week: E //Output: Mon/Tue/Wed etc.
//                            For TimeZone: z //Output: IST/EST
//                                          zzzz //Output: Indian Standard Time
    public static String getDateinGivenFormat(String dateFormat){
        try{
            return new SimpleDateFormat(dateFormat).format(new Date());
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    //This method is used to type a string into current activated cell
    public static void typeThisString(String StringToType) throws Exception {
        //Code For Typing Given String At Current Field
        Robot robot =new Robot();
        StringSelection stringSelection = new StringSelection(StringToType);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    //This method is used to generate a random number based on given length
    public static String generateRandNo(int charLength) {
        String randomnumber="";
        Random random=new Random();
        for(int i=0;i<charLength+1;i++){
            randomnumber = randomnumber + random.nextInt(9);
        }
        return randomnumber.substring(0,charLength);
    }

}
