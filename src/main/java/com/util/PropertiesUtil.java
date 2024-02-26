package com.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    private static final Properties INSTANCE = new Properties();



    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            INSTANCE.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private PropertiesUtil () {

    }

    public static String getKey (String key) {
        return INSTANCE.getProperty(key);
    }


}
