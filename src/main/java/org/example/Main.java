package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class Main {
    public static void main(String[] args) {

        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("https://www.google.com");
        driver.quit();
    }
}