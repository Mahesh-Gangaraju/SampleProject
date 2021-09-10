package Utilities;

import BaseClass.BaseClass;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PDF_Handling {

    //Below method is used to search in PDF document.
    public static boolean verifyPDFContent(String filePath, List<String> dataToSearch) {
        boolean searchStatus = true;
        PDDocument document = null;
        try{
            document = PDDocument.load(new File(filePath));
            String fileContent = new PDFTextStripper().getText(document);
            for(String str : dataToSearch) {
                if(fileContent.toUpperCase(Locale.ROOT).contains(str.toUpperCase(Locale.ROOT))){
                    BaseClass.scenario.log("String \"" + str + "\" Exists in PDF!");
                }else{
                    BaseClass.scenario.log("String \"" + str + "\" Does Not Exist in PDF!");
                    searchStatus = false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return searchStatus;
    }

}
