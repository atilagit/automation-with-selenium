package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.Pauser.waiting;

public class WebDriverSingleton {

    // Instância única do WebDriver
    private static WebDriver instance;

    // Construtor privado para evitar a criação de instâncias externas
    private WebDriverSingleton() {
        instance = WebDriverManager.chromedriver().create();
    }

    // Método para obter a instância única do WebDriver
    public static WebDriver getWebDriverInstance() {
        if (instance == null) {
            // Se a instância ainda não foi criada, crie-a agora
            new WebDriverSingleton();
        }
        return instance;
    }

    public static void clickOn(By element, String typeOfWaiting) {
        getWebDriverInstance().findElement(element).click();
        waiting(typeOfWaiting);
    }

    public static void writeText(CharSequence text, By element, String typeOfWaiting) {
        getWebDriverInstance().findElement(element).sendKeys(text);
        waiting(typeOfWaiting);
    }

    // Método para fechar o WebDriver
    public static void closeWebDriverInstance() {
        if (instance != null) {
            instance.quit();
            instance = null;
        }
    }
}
