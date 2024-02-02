package com.demo.TaskMannagment.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadConfig {

	private final Logger logger = LogManager.getLogger(ReadConfig.class);
	private Properties properties;

	public ReadConfig() {
		properties = new Properties();
		loadPropertiesFile();
	}

    private void loadPropertiesFile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(getPropertiesFile());
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.error("Unable to load configuration file.", e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    logger.error("Error closing FileInputStream.", e);
                }
            }
        }
    }
	
	private File getPropertiesFile() {
		String filePath = "E:\\Qpot\\Properties\\Config.properties";
		return new File(filePath);
	}

	public String readQpotUrl() {
		return properties.getProperty("URL");
	}
	
	public String readPM_Url() {
		return properties.getProperty("PM_URL");
	}
	
	public String readPM_Username() {
		return properties.getProperty("PM_Username");
	}
	
	public String readPM_Password() {
		return properties.getProperty("PM_Password");
	}
	
}
