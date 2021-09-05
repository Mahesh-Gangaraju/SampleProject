package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperations {
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

    public boolean setCellData(String filPath,String sheetName,int rowNum,int columnNum,String data){

        return true;
    }

//    // returns the row count in a sheet
//    public int getRowCount(String sheetName) {
//        int index = workbook.getSheetIndex(sheetName);
//        if (index == -1)
//            return 0;
//        else {
//            sheet = workbook.getSheetAt(index);
//            int number = sheet.getLastRowNum() + 1;
//            return number;
//        }
//
//    }
//
//
//    // returns the data from a cell
//    public String getCellData(String sheetName, String colName, int rowNum) {
//        try {
//            if (rowNum <= 0)
//                return "";
//
//            int index = workbook.getSheetIndex(sheetName);
//            int col_Num = -1;
//            if (index == -1)
//                return "";
//
//            sheet = workbook.getSheetAt(index);
//            row = sheet.getRow(0);
//            for (int i = 0; i < row.getLastCellNum(); i++) {
//                // System.out.println(row.getCell(i).getStringCellValue().trim());
//                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
//                    col_Num = i;
//            }
//            if (col_Num == -1)
//                return "";
//
//            sheet = workbook.getSheetAt(index);
//            row = sheet.getRow(rowNum - 1);
//            if (row == null)
//                return "";
//            cell = row.getCell(col_Num);
//
//            if (cell == null)
//                return "";
//
//            //System.out.println(cell.getCellType().name());
//            //
//            if (cell.getCellType().name().equals("STRING"))
//                return cell.getStringCellValue();
//
//                // if (cell.getCellType().STRING != null)
//
//                // if(cell.getCellType()==Xls_Reader.CELL_TYPE_STRING)
//                // return cell.getStringCellValue();
//            else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {
//
//                String cellText = String.valueOf(cell.getNumericCellValue());
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    // format in form of M/D/YY
//                    double d = cell.getNumericCellValue();
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(HSSFDateUtil.getJavaDate(d));
//                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
//                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
//
//                    // System.out.println(cellText);
//
//                }
//
//                return cellText;
//            } else if (cell.getCellType().BLANK != null)
//                return "";
//            else
//                return String.valueOf(cell.getBooleanCellValue());
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return "row " + rowNum + " or column " + colName + " does not exist in xls";
//        }
//    }
//
//
//    // returns the data from a cell
//    public String getCellData(String sheetName, int colNum, int rowNum) {
//        try {
//            if (rowNum <= 0)
//                return "";
//
//            int index = workbook.getSheetIndex(sheetName);
//
//            if (index == -1)
//                return "";
//
//            sheet = workbook.getSheetAt(index);
//            row = sheet.getRow(rowNum - 1);
//            if (row == null)
//                return "";
//            cell = row.getCell(colNum);
//            if (cell == null)
//                return "";
//
//            if (cell.getCellType().name().equals("STRING"))
//                return cell.getStringCellValue();
//            else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {
//
//                String cellText = String.valueOf(cell.getNumericCellValue());
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    // format in form of M/D/YY
//                    double d = cell.getNumericCellValue();
//
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTime(HSSFDateUtil.getJavaDate(d));
//                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
//                    cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
//                }
//
//                return cellText;
//            } else if (cell.getCellType().BLANK != null)
//                return "";
//            else
//                return String.valueOf(cell.getBooleanCellValue());
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
//        }
//    }
//
//    // returns true if data is set successfully else false
//    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
//        try {
//            fis = new FileInputStream(path);
//            workbook = new HSSFWorkbook(fis);
//
//            if (rowNum <= 0)
//                return false;
//
//            int index = workbook.getSheetIndex(sheetName);
//            int colNum = -1;
//            if (index == -1)
//                return false;
//
//            sheet = workbook.getSheetAt(index);
//
//            row = sheet.getRow(0);
//            for (int i = 0; i < row.getLastCellNum(); i++) {
//                // System.out.println(row.getCell(i).getStringCellValue().trim());
//                if (row.getCell(i).getStringCellValue().trim().equals(colName))
//                    colNum = i;
//            }
//            if (colNum == -1)
//                return false;
//
//            sheet.autoSizeColumn(colNum);
//            row = sheet.getRow(rowNum - 1);
//            if (row == null)
//                row = sheet.createRow(rowNum - 1);
//
//            cell = row.getCell(colNum);
//            if (cell == null)
//                cell = row.createCell(colNum);
//
//            // cell style
//            // CellStyle cs = workbook.createCellStyle();
//            // cs.setWrapText(true);
//            // cell.setCellStyle(cs);
//            cell.setCellValue(data);
//
//            fileOut = new FileOutputStream(path);
//
//            workbook.write(fileOut);
//
//            fileOut.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//
//    // returns true if sheet is created successfully else false
//    public boolean addSheet(String sheetname) {
//
//        FileOutputStream fileOut;
//        try {
//            workbook.createSheet(sheetname);
//            fileOut = new FileOutputStream(path);
//            workbook.write(fileOut);
//            fileOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    // returns true if sheet is removed successfully else false if sheet does
//    // not exist
//    public boolean removeSheet(String sheetName) {
//        int index = workbook.getSheetIndex(sheetName);
//        if (index == -1)
//            return false;
//
//        FileOutputStream fileOut;
//        try {
//            workbook.removeSheetAt(index);
//            fileOut = new FileOutputStream(path);
//            workbook.write(fileOut);
//            fileOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//    // returns true if column is created successfully
//    public boolean addColumn(String sheetName, String colName) {
//        // System.out.println("**************addColumn*********************");
//
//        try {
//            fis = new FileInputStream(path);
//            workbook = new HSSFWorkbook(fis);
//            int index = workbook.getSheetIndex(sheetName);
//            if (index == -1)
//                return false;
//
//            HSSFCellStyle style = workbook.createCellStyle();
//            // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//            // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//
//            sheet = workbook.getSheetAt(index);
//
//            row = sheet.getRow(0);
//            if (row == null)
//                row = sheet.createRow(0);
//
//            // cell = row.getCell();
//            // if (cell == null)
//            // System.out.println(row.getLastCellNum());
//            if (row.getLastCellNum() == -1)
//                cell = row.createCell(0);
//            else
//                cell = row.createCell(row.getLastCellNum());
//
//            cell.setCellValue(colName);
//            cell.setCellStyle(style);
//
//            fileOut = new FileOutputStream(path);
//            workbook.write(fileOut);
//            fileOut.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//
//    }
//
//    // removes a column and all the contents
//    public boolean removeColumn(String sheetName, int colNum) {
//        try {
//            if (!isSheetExist(sheetName))
//                return false;
//            fis = new FileInputStream(path);
//            workbook = new HSSFWorkbook(fis);
//            sheet = workbook.getSheet(sheetName);
//            HSSFCellStyle style = workbook.createCellStyle();
//            // style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//            HSSFCreationHelper createHelper = workbook.getCreationHelper();
//            // style.setFillPattern(XSSFCellStyle.NO_FILL);
//            for (int i = 0; i < getRowCount(sheetName); i++) {
//                row = sheet.getRow(i);
//                if (row != null) {
//                    cell = row.getCell(colNum);
//                    if (cell != null) {
//                        cell.setCellStyle(style);
//                        row.removeCell(cell);
//                    }
//                }
//            }
//            fileOut = new FileOutputStream(path);
//            workbook.write(fileOut);
//            fileOut.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//
//    }
//
//    // find whether sheets exists
//    public boolean isSheetExist(String sheetName) {
//        int index = workbook.getSheetIndex(sheetName);
//        if (index == -1) {
//            index = workbook.getSheetIndex(sheetName.toUpperCase());
//            if (index == -1)
//                return false;
//            else
//                return true;
//        } else
//            return true;
//    }
//
//    // returns number of columns in a sheet
//    public int getColumnCount(String sheetName) {
//        // check if sheet exists
//        if (!isSheetExist(sheetName))
//            return -1;
//
//        sheet = workbook.getSheet(sheetName);
//        row = sheet.getRow(0);
//
//        if (row == null)
//            return -1;
//
//        return row.getLastCellNum();
//
//    }
//
//    //Returns the Row Number Based on the CEll Value
//    public int getCellRowNum(String sheetName, String colName, String cellValue) {
//
//        for (int i = 2; i <= getRowCount(sheetName); i++) {
//            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
//                return i;
//            }
//        }
//        return -1;
//
//    }

}
