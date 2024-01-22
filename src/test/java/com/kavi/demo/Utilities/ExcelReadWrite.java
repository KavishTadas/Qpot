package com.kavi.demo.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

	// Class variables to store workbook, sheet, and Excel file path
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String excelPath;

	// Constructor to initialize the ExcelReadWrite object with the Excel file path and sheet name
	public ExcelReadWrite(String excelPath, String sheetName) {
		this.excelPath = excelPath;
		initializeWorkbook(sheetName);
	}

	// Private method to initialize the workbook and sheet based on the provided sheet name
	private void initializeWorkbook(String sheetName) {
		try (FileInputStream fs = new FileInputStream(new File(excelPath))) {
			workbook = new XSSFWorkbook(fs);
			sheet = workbook.getSheet(sheetName);
		} catch (IOException e) {
			System.out.println("Error initializing workbook: " + e.getMessage());
		}
	}

	// Method to retrieve data from a specific cell in the Excel sheet
	public String getData(int row, int column) {
	    try {
	        Cell cell = sheet.getRow(row).getCell(column);
	        if (cell != null) {
	            switch (cell.getCellType()) {
	                case STRING:
	                    return cell.getStringCellValue();
	                case NUMERIC:
	                    // Use DataFormatter to format numeric values as strings
	                    DataFormatter dataFormatter = new DataFormatter();
	                    return dataFormatter.formatCellValue(cell);
	                default:
	                    // Handle other cell types if needed
	                    return null;
	            }
	        } else {
	            // Handle null cell case if needed
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error getting data: " + e.getMessage());
	        return null;
	    }
	}


	// Method to write data to a specific cell in the Excel sheet
	public void writeData(int row, int column, String value) {
		try {
			// Get the specified row; create a new row if it doesn't exist
			Row dataRow = sheet.getRow(row);
			if (dataRow == null) {
				dataRow = sheet.createRow(row);
			}

			// Get the specified cell; create a new cell if it doesn't exist
			Cell cell = dataRow.getCell(column, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			// Set the cell value to the provided value
			cell.setCellValue(value);

			// Write the changes back to the Excel file
			try (FileOutputStream fos = new FileOutputStream(excelPath)) {
				workbook.write(fos);
			}
		} catch (Exception e) {
			System.out.println("Error writing data: " + e.getMessage());
		}
	}
	
	 // Method to get the physical row count for the first two columns
    public int getRowCount() {
        try {
            // Iterate through the rows until the first null cell in the first column is encountered
            int rowCount = 0;
            while (sheet.getRow(rowCount) != null && sheet.getRow(rowCount).getCell(0) != null) {
                rowCount++;
            }
            return rowCount;
        } catch (Exception e) {
            System.out.println("Error getting row count: " + e.getMessage());
            return -1; // Return -1 to indicate an error
        }
    }

	// Method to close the workbook when done
	public void closeWorkbook() {
		try {
			if (workbook != null) {
				workbook.close();
			}
		} catch (IOException e) {
			System.out.println("Error closing workbook: " + e.getMessage());
		}
	}
}
