package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.annotations.Test;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

public class JSONUtility {

	
	public static Environment readJson(Env env)  {
		
		File file= new File(System.getProperty("user.dir")+"\\config\\config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson= new Gson();//Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. -->
		Config config= gson.fromJson(fileReader, Config.class); // It will  give the config class object
		Environment environment= config.getEnvironments().get("QA");
		return environment;
	}
}
