package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Handling {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;


//    Author: Mahesh Gangaraju
//    Method Description: It is used to create a New Excel File at provided path with required sheet name and columns.
//    Required Parameters:
//                         filePath : Complete File Path of New Excel File
//                         sheetName : SheetName
//                         columnNames: Column names in the form of a string array
    public boolean createNewExcelFile(String filePath,String SheetName,String[] columnNames){
        try{
            File file = new File(filePath);
            if(file.exists()) { //Checking if File Already Exists. If Exists, it will fail the script.
                return false;
            }
            else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet(SheetName);
                Row row  = sheet.createRow(0);
                for(int i = 0; i<columnNames.length;i++){
                    row.createCell(i).setCellValue(columnNames[i]);
                    sheet.autoSizeColumn(i);
                }
                fileOut = new FileOutputStream(filePath);
                workbook.write(fileOut);
                workbook.close();
                fileOut.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    Author: Mahesh Gangaraju
//    Method Description: It is used to get Row Number of a particular value in first column.
//                          Important Note: It Assumes that you are searching only in Column1. As most of time first column has the unique identifier for each row
//    Required Parameters:
//                         filePath : Complete File Path of New Excel File
//                         sheetName : SheetName
//                         cellData: String to search
    public int getCellRowNum(String filePath,String sheetName,String cellData){
        int cellRowNum = -1;
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            int sheetIndex = workbook.getSheetIndex(sheetName);
            if(sheetIndex == -1){
                // It will return -1. Which indicates that we couldn't find the required sheet.
            }
            else{
                sheet  = workbook.getSheetAt(sheetIndex);
                //Starting Search from 2nd row as first row will be column names
                for(int i = 1 ; i<=sheet.getLastRowNum() ; i++){
                    Row row = sheet.getRow(i);
                    //Hardcoded Cell Value to Zero for mentioned reason in method description.
                    if(row.getCell(0).getStringCellValue().trim().equalsIgnoreCase(cellData.trim())){
                        cellRowNum = i;
                        break;
                    }
                }
            }
            fis.close();
            workbook.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cellRowNum;
    }


//    Author: Mahesh Gangaraju
//    Method Description: It is used to write data into excel sheet.
//    Required Parameters:
//                         filePath : Complete File Path of New Excel File
//                         sheetName : SheetName
//                         rowNum : Row into Which data needs to be entered [Starting with Index=0]
//                         columnNum = Column Number [Starting with Index=0]
//                         data : String to be written
    public boolean setCellData(String filPath,String sheetName,int rowNum,int columnNum,String data){
        try{
            fis = new FileInputStream(filPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(columnNum);
            if(cell == null || cell.getCellType() == CellType.BLANK){
                row.createCell(columnNum).setCellValue(data);
            }else {
                row.getCell(columnNum).setCellValue(data);
            }
            fileOut = new FileOutputStream(filPath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            fis.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

//    Author: Mahesh Gangaraju
//    Method Description: It is used to get data from excel sheet.
//    Required Parameters:
//                         filePath : Complete File Path of New Excel File
//                         sheetName : SheetName
//                         rowNum : Row Num [Starting with Index=0]
//                         columnNum = Column Number [Starting with Index=0]
    public String getCellData(String filePath, String sheetName, int rowNum, int columnNum){
        String stringFound = "";
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(columnNum);
            if(cell == null || cell.getCellType() == CellType.BLANK){
                stringFound = "";
            }else{
                stringFound = cell.getStringCellValue().trim();
            }
        }catch (Exception e) {
            e.printStackTrace();
            stringFound = "";
        }
        return stringFound;
    }

}
