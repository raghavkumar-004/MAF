package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {

	public static Iterator<User> readCSVFile(String fileName) throws CsvValidationException, IOException {

		File file = new File(System.getProperty("user.dir") + "//testData//"+fileName+".csv");
		String[] line;
		FileReader fis = new FileReader(file);
		CSVReader csvReader = new CSVReader(fis);

		csvReader.readNext(); // read next will give the first line value and if the value is present in the
								// second line then again we need to do read next again
		User userData;
		List<User> userList = new ArrayList<User>();
		while ((line = csvReader.readNext()) != null) {
			 userData = new User(line[0], line[1]);
			userList.add(userData);
		}

		return userList.iterator();
	}

}
