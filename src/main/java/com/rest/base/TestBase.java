package com.rest.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    public Properties prop;

    public final int RESPONSE_CODE_200 = 200;
    public final int RESPONSE_CODE_201 = 201;
    public final int RESPONSE_CODE_204 = 204;
    public final int RESPONSE_CODE_400 = 400;
    public final int RESPONSE_CODE_500 = 500;
    public final int RESPONSE_CODE_401 = 401;
    public final int RESPONSE_CODE_404 = 404;

    public TestBase() {
        prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/rest/config/config.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
