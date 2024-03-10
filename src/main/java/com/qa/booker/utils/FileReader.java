package com.qa.booker.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {

	public static String readFile(String filePath) {
		String jsonData = null;
		try {
			jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;

	}

	public static HashMap<String, String> readExcelData() {
		HashMap<String, String> hashMap = null;
		try {
			hashMap = new HashMap<String, String>();
			FileInputStream finp = new FileInputStream(Constants.TESTDATA_FILE_PATH);
			XSSFWorkbook workBook = new XSSFWorkbook(finp);
			XSSFSheet sheet = workBook.getSheet("CreateBooking");
			int columnNum = sheet.getRow(0).getLastCellNum();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				for (int j = 0; j < columnNum; j++) {
					hashMap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i).getCell(j).toString());
				}
			}

			workBook.close();
		} catch (Exception e) {

		}
		return hashMap;
	}

}
