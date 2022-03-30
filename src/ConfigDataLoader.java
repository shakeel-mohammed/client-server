import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ConfigDataLoader {
    private static ConfigDataLoader configDataLoader = null;
    private Properties props = new Properties();

    ConfigDataLoader(File configFile) {
        try {
            FileInputStream in = new FileInputStream(configFile); 
            this.props.load(in);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find config file: " + e);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public static ConfigDataLoader getInstance() {
        if (configDataLoader == null) {
            File configFile = new File("config.properties");
            configDataLoader = new ConfigDataLoader(configFile);
        }
        return configDataLoader;
    }

    public String get(String key) {
        return this.props.getProperty(key);
    }
}
