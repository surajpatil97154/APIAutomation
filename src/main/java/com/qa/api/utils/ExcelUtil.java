package com.qa.api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private final static String EXCEL_FILE_PATH = "./src/test/resources/testdata/APITestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;

	public static Object[][] readExcelData(String sheetName) {
		Object[][] data = null;
		try {
			FileInputStream f = new FileInputStream(EXCEL_FILE_PATH);
			book = WorkbookFactory.create(f);
			sheet = book.getSheet(sheetName);

			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

}
