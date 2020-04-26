package com.amazon.automation.dataprovider;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.amazon.automation.basepack.Testbase;
import com.amazon.automation.utilities.ExcelReader;

public class DataUtil extends Testbase {

	private static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\Excel\\TestData.xlsx");

	@DataProvider(name = "data")
	public static Object[][] getData(Method m) {

		int rows = excel.getRowCount(config.getProperty("testDataSheetName"));

		String testName = m.getName();

		System.out.println("Test name is : " + testName);

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(config.getProperty("testDataSheetName"), 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;
		while (!excel.getCellData(config.getProperty("testDataSheetName"), 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(config.getProperty("testDataSheetName"), testCols, colStartColNum).equals("")) {

			testCols++;

		}

		Object[][] data = new Object[testRows][1];

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

			for (int cNum = 0; cNum < testCols; cNum++) {

				String testData = excel.getCellData(config.getProperty("testDataSheetName"), cNum, rNum);
				String colName = excel.getCellData(config.getProperty("testDataSheetName"), cNum, colStartColNum);

				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}

		return data;
	}

}
