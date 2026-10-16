package mphillips.cukes.project.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Test {
    // Property names in the config file
    public static final String BROWSER = "browser";
    public static final String USERNAME = "username";
    public static final String BASEURL = "baseUrl";

    public static final String RESULTS_DIR = "resultsDir";
    private static final String resultsDir = "results";

    // Names of the property files
    private static final String propertiesFile = "application.properties";
    private static final String localPropertiesFile = "local.properties";


    public static Properties env;
    public static WebDriver driver;
    public static Logger log = LoggerFactory.getLogger(Test.class);
    public static final String runStartTime = timestamp();

    // Only expected to be called once
    public static void SetupEnv()
    {
        env = new Properties();
        env.putAll(loadProps(propertiesFile, true));
        env.putAll(loadProps(localPropertiesFile, false));
        env.putAll(System.getProperties());

        // create output folder
        Path workignDir = Path.of(System.getProperty("user.dir"));
        Path resultsFolder = workignDir.resolve(resultsDir);
        try {
            Files.createDirectories(resultsFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        env.put(RESULTS_DIR, resultsFolder.toAbsolutePath().toString());
        log.info("Log dir = {}", env.getProperty(RESULTS_DIR));

    }


    public static void setupWebDriver()
    {
        teardownWebDriver();
        var browser = env.getProperty(BROWSER, "undefined");
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException(String.format("Browser unknown or undefined in config : %s=%s", BROWSER, browser));
        }

    }

    public static void teardownWebDriver()
    {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static Properties loadProps(String filename, boolean required)
    {
        Properties props = new Properties();
        try {
            InputStream in = Test.class
                    .getClassLoader().getResourceAsStream(filename);
            if (null == in)
            {
                if(required)
                    throw new RuntimeException(filename + " not found but required");
                else
                    return props;
            }
            props.load(in);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return props;
    }

    public static String timestamp()
    {
        return LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss")
        );
    }

}
