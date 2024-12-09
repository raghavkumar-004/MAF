package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;



public class ExcelReaderUtility {

	
	public static Iterator<User> readExcelFile(String fileName) throws InvalidFormatException, IOException {
		Row row;
		Cell emailCell;
		Cell passwordCell;
		User user;
		
		File file= new File(System.getProperty("user.dir")+"//testData//"+ fileName+ ".xlsx");
		XSSFWorkbook xssFWorkbook= new XSSFWorkbook(file);
		XSSFSheet xssFSheet= xssFWorkbook.getSheet("LoginTestData");
		Iterator<Row> rowIterator=xssFSheet.iterator();
		rowIterator.next();//point to the first ROW
		List<User> userList= new ArrayList<User>();
		
		while(rowIterator.hasNext()) {
		 row= rowIterator.next();
		 emailCell	 =row.getCell(0);
		 passwordCell=row.getCell(1);
		 user= new User(emailCell.toString(), passwordCell.toString());
		 userList.add(user);
		
		}
		 xssFWorkbook.close();
		 return userList.iterator();
	}
}
