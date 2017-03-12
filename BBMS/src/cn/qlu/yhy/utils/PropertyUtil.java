package cn.qlu.yhy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {

    private static Map<String, Properties> fileProperties = null;
    private static final String[] propertiesPath = new String[]{"app.properties"};

    static {
        fileProperties = new HashMap<String, Properties>();

        for (String propertyPath : propertiesPath) {
            System.out.println(propertyPath);
            InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyPath);
            Properties property = new Properties();

            try {
                property.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {

                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }

            fileProperties.put(propertyPath, property);

        }

    }

    public static String getJDBCPropertiesValues(String str) {
        return fileProperties.get("app.properties").getProperty(str);
    }

}
