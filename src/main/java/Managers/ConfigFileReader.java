package Managers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ConfigFileReader {

    public static Properties properties;
    public static final String propertyFilePath = "src/main/java/Config/Config.properties";

    public static Properties readConfigFileContents() throws Exception {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(propertyFilePath));
        properties = new Properties();
        properties.load(reader);
        return properties;
    }

}
