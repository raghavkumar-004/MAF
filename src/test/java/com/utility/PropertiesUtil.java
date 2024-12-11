package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	
	public static String readProperty(Env env, String property) {
		
		
		File file= new File(System.getProperty("user.dir")+"//config//"+env+".properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties properties= new Properties();
		try {
			properties.load(fileReader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String value=properties.getProperty(property.toUpperCase()); //just for consistency nothing else
		return value;
	}
}
