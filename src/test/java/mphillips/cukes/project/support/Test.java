package mphillips.cukes.project.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Test {
    public static Properties env;
    private static final String propertiesFile = "application.properties";
    private static final String localPropertiesFile = "local.properties";

    public static void SetupEnv()
    {
        var props = new Properties();
        props.putAll(loadProps(propertiesFile, true));
        props.putAll(loadProps(localPropertiesFile, false));
        props.putAll(System.getProperties());
        env = props;
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


}
