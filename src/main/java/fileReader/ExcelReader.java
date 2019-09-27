package fileReader;

import java.io.FileInputStream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import utility.TimeAndDate;

public class ExcelReader {

	String filePath;
	static FileInputStream fis;
	static Workbook workbook;
	static Sheet sheet;
	static Row row;
	static Cell cell;
	static String cellValue;

	public ExcelReader(String filePath) {

		this.filePath = filePath;

		try {
			fis = new FileInputStream(filePath);
			workbook = WorkbookFactory.create(fis);

		} catch (Exception e) {
			System.out.println("Path Not mantaion " + e.getMessage());
		}

	}

	public  Sheet getSheet(String sheetName) {
		Sheet sheet = null;
		if (workbook != null) {
			sheet = workbook.getSheet(sheetName);

		}
		return sheet;

	}

	public  int TotalRowNumber(String sheetName) {

		int rowNumber = 0;

		if (workbook != null) {
			rowNumber = getSheet(sheetName).getLastRowNum();
			// System.out.println(rowNumber);

		}
		return rowNumber;

	}

	public static Sheet getSheetAt(int sheetIndex) {

		Sheet sheet = null;

		if (workbook != null) {

			sheet = workbook.getSheetAt(sheetIndex);
		}
		return sheet;

	}

	public  Row getRow(String sheetName, int rowNum) {
		Row row = null;
		getSheet(sheetName);

		if (sheet != null) {
			row = sheet.getRow(rowNum);

		}
		return row;

	}

	public  Cell getCell(String sheetName, int rowNum, int cellNum) {

		return getSheet(sheetName).getRow(rowNum).getCell(cellNum);

	}

	public  String getSingleExcelCellValue(String sheetName, int rowNum, int cellNum) {

		cell = getCell(sheetName, rowNum, cellNum);

		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			cellValue = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			// cellValue = cell.getNumericCellValue() + "";
			// to get adject value form excel sheet
			BigDecimal bd = new BigDecimal(cell.getNumericCellValue());

			cellValue = bd.longValueExact() + "";
		}
		return cellValue;
	}
     public int getTotalColumnCount(String sheetName){
    	   int columnCount = 0;
    	    
    	    sheet = getSheet(sheetName);
    		 row = sheet.getRow(0);
    		columnCount =   row.getLastCellNum();
    	   
    	    return columnCount;
     }
	
	public List getAllExcelCellValue(String sheetName) {

		sheet = getSheet(sheetName);
		List list = new ArrayList();
		System.out.println("No of row "+sheet.getLastRowNum());
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {


			row = sheet.getRow(i);
         int cellNumber =   row.getLastCellNum();
         
             System.out.println(cellNumber);
			for (int j = 0; j < cellNumber; j++) {

				cell = row.getCell(j);

				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

					cellValue = cell.getStringCellValue();
					list.add(cellValue);
				} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                         if(cell.toString().contains(".")){
					// cellValue = cell.getNumericCellValue() + "";
					// to get adject value form excel sheet
					BigDecimal bd = new BigDecimal(cell.getNumericCellValue());

					cellValue = bd.longValueExact() + "";
                          }else{
                        	  
                        	 Date date = cell.getDateCellValue();
                        	 
                        	
                        	 Calendar calendar = new GregorianCalendar();
                        	 calendar.setTime(date);
                        	 String year = calendar.get(Calendar.YEAR)+"";
                        	 //Add one to month {0 - 11}
                        	 
                        	 int month = calendar.get(Calendar.MONTH) + 1;
                        	 int  day = calendar.get(Calendar.DAY_OF_MONTH);
                        	 String  monthValue = month+""; 
                        	 String dayValue = day+"";
                        	 
                        	 if(month < 10 ){
                        		
                        		  monthValue  = 0+""+month+"";
                        		  
                        	 }
                        	 if(day < 10){
                        		 
                        		  dayValue  = 0+""+day+""; 
                        	 }
                        	
                            String formater = year+"-"+monthValue+"-"+dayValue ;
                        	
                        	cellValue = formater;
                            
                        	 
                          }
					list.add(cellValue);
                          
				}else{
					
				}
			}
		}
		return list ;
	}

	// get column wise data
	public String getColumnData(String sheetName, String colName, int rowNum) {
		String cellValue = "";
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
					col_Num = i;
					sheet = workbook.getSheetAt(index);
					row = sheet.getRow(rowNum);

					if (row == null)
						return "";
					cell = row.getCell(col_Num);

					if (cell == null)
						return "";
					// System.out.println(cell.getCellType());
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						cellValue = cell.getStringCellValue();
						return cellValue;
					}
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

						BigDecimal bd = new BigDecimal(cell.getNumericCellValue());

						cellValue = bd.longValueExact() + "";
						return cellValue;

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist ";
		}

		return cellValue;
	}

	public  String[][] getExcelData(String sheetName) {
		try {
			// logger.info("Creating excel object:-"+excellocation);
			String dataSets[][] = null;
			String dataSet = "";

			sheet = getSheet(sheetName);
			// count number of active tows
			int totalRow = sheet.getLastRowNum() + 1;
			// count number of active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			// Create array of rows and column
			dataSets = new String[totalRow - 1][totalColumn];
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			int i = 0;
			int t = 0;

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (i++ != 0) {
					int k = t;
					t++;
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					int j = 0;
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						// Check the cell type and format accordingly
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							// System.out.print(k+",");
							// System.out.print(j+",");
							// dataSets[k][j++] = cell.getNumericCellValue()+
							// "";
							// System.out.println(cell.getNumericCellValue());

							BigDecimal bd = new BigDecimal(cell.getNumericCellValue());

							dataSets[k][j++] = bd.longValueExact() + "";

							// System.out.println(bd.longValueExact()+ "");

							break;
						case Cell.CELL_TYPE_STRING:

							dataSets[k][j++] = cell.getStringCellValue();

							break;
						case Cell.CELL_TYPE_BOOLEAN:

							dataSets[k][j++] = cell.getStringCellValue();

							break;
						case Cell.CELL_TYPE_FORMULA:

							dataSets[k][j++] = cell.getStringCellValue();
							System.out.println(cell.getStringCellValue());
							break;

						default:
							dataSet = "";
						}

					}

				}
			}

			fis.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}