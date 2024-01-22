package com.kavi.demo.Utilities;

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
		String filePath = "E:\\Data_Driven_Demo\\Properties\\Config.properties";
		return new File(filePath);
	}

	public String readUrl() {
		return properties.getProperty("URL");
	}
}
