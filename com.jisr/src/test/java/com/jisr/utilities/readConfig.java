package com.jisr.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class readConfig {
	
	
	
	Properties pro;
	
	public readConfig() {
		
		File src = new File("./ConfigurationFiles/config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(src);
			pro= new Properties();
			pro.load(fis);
		}catch(Exception e) {
			
			System.out.println("Exception is " + e.getMessage());
		}
		
		
		
		
	}
	public String getApplicationUrl(){
		
		String url= pro.getProperty("baseUrl");
		return url;
	}
	
	
	public String getChromePath() {
		
		String chromePath = pro.getProperty("chromepath");
		return chromePath;
	}
	
	public String getFireFoxPath() {
		
		String firefoxPath = pro.getProperty("firefoxpath");
		return firefoxPath;
	}
	
	
}
