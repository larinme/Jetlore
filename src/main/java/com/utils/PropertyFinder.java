package com.utils;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertyFinder {

    private static volatile PropertyFinder instance = null;
    private Properties properties = null;

    public static PropertyFinder getInstance() {
        if (instance == null) {
            synchronized (PropertyFinder.class) {
                if (instance == null) {
                    instance = new PropertyFinder();
                }
            }
        }
        return instance;
    }

    public String getProperty(String property) {
        if (properties == null) {
            properties = new Properties();
            URL url = Resources.getResource("resource.properties");
            final ByteSource byteSource = Resources.asByteSource(url);
            try (InputStream inputStream = byteSource.openBufferedStream()) {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String value = properties.getProperty(property);
        if (value == null) {
            throw new NullPointerException("Could not find property with key = " + property);
        }
        return value;
    }
}
