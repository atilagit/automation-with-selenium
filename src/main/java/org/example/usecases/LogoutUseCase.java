package org.example.usecases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.Constants.WAIT_LESS_TIME;
import static org.example.utils.Constants.WAIT_SOME_TIME;
import static org.example.utils.Pauser.waiting;
import static org.example.utils.WebDriverSingleton.*;

public class LogoutUseCase {

    public static final String MAIS_OPCOES = "//div[@title='Mais opções']";
    public static final String DESCONECTAR = "//*[@id=\"app\"]/div/div[2]/div[3]/header/div[2]/div/span/div[5]/span/div/ul/li[6]/div";
    public static final String CONFIRMAR_DESCONEXAO = "//*[@id=\"app\"]/div/span[2]/div/div/div/div/div/div/div[3]/div/button[2]/div/div";
    public static final String SCAN_ME = "//*[@id=\"app\"]/div/div[2]/div[3]/div[1]/div/div/div[2]/div/canvas";
    public static final int INDEX_MENU_LATERAL_ESQUERDA = 0;

    static WebDriver driver = getWebDriverInstance();

    public void execute() {
        disconnect();
        closeWebDriverInstance();
    }

     private static void disconnect() {
        driver.findElements(By.xpath(MAIS_OPCOES))
                .get(INDEX_MENU_LATERAL_ESQUERDA)
                .click();
        waiting(WAIT_LESS_TIME);

        clickOn(By.xpath(DESCONECTAR), WAIT_LESS_TIME);
        clickOn(By.xpath(CONFIRMAR_DESCONEXAO), WAIT_LESS_TIME);
        waitDeauthentication();
    }

    private static void waitDeauthentication() {
        // Inicializar o WebDriverWait com o WebDriver e o tempo de espera máximo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));

        // Esperar até que a condição seja satisfeita
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SCAN_ME)));

        waiting(WAIT_SOME_TIME);
    }
}