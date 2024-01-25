package org.example.usecases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.WAIT_SOME_TIME;
import static org.example.utils.Pauser.waiting;
import static org.example.utils.WebDriverSingleton.getWebDriverInstance;

public class LoginUseCase {

    public static final String WHATSAPP_WEB_URL = "https://web.whatsapp.com/";
    public static final String SIDE = "side";

    static WebDriver driver = getWebDriverInstance();


    public void execute() {
        openTheWebSite();
        waitAuthentication();
    }

    private static void openTheWebSite() {
        driver.get(WHATSAPP_WEB_URL);
    }

    private static void waitAuthentication() {
        // Inicializar o WebDriverWait com o WebDriver e o tempo de espera máximo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));

        // Esperar até que o elemento com o ID "side" esteja presente na página
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(SIDE)));

        waiting(WAIT_SOME_TIME);
    }
}