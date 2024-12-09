package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {

	/*
	 * DataProvider method will return data in the form of two dimensional object, single dimensional object and iterator also so below we have return the Iterator 
	 */
	@DataProvider(name="loginTestDataProvider")
	public Iterator<Object[]> loginDataProvider()
	{
		
		Gson gson= new Gson();
		File file= new File(System.getProperty("user.dir")+"\\testData\\loginData.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestData testData= gson.fromJson(fileReader, TestData.class); //map the file reader to a java class
		
		
		List<Object[]> dataToReturn= new ArrayList<Object[]>();
		for( User user: testData.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
	}
	@DataProvider(name="loginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() throws CsvValidationException, IOException {
	return	CSVReaderUtility.readCSVFile("loginData"); 
		
		
		
	}
	@DataProvider(name="loginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() throws InvalidFormatException, IOException {
	return ExcelReaderUtility.readExcelFile("loginData");
		
		
		
	}
	
	
	
}
