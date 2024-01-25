package org.example.usecases;

import org.example.datasources.ArtistDataSource;
import org.example.entities.Artist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.utils.Constants.*;
import static org.example.utils.Pauser.waiting;
import static org.example.utils.WebDriverSingleton.*;

public class SendMessagesUseCase {

    public static final String TITLE = "{title}";
    public static final String INFORMAL_NAME_OF_ARTIST = "{informalNameOfArtist}";
    public static final String MESSAGE = "Fala " + INFORMAL_NAME_OF_ARTIST +" tudo blz? Isso é uma mensagem de teste, pode ignorar.";

    public static final String WHATSAPP_WEB_URL = "https://web.whatsapp.com/";
    public static final String SEARCH_FIELD = "//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p";
    public static final String MESSAGE_FIELD = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p";
    public static final String SEND_BUTTON = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span";
    public static final String SPAN_ELEMENT_BY_TITLE = "//span[@title='" + TITLE +"']";

    ArtistDataSource artistDataSource;
    static WebDriver driver = getWebDriverInstance();


    public SendMessagesUseCase(ArtistDataSource artistDataSource) {
        this.artistDataSource = artistDataSource;
    }

    public void execute() {
        List<Artist> artists = artistDataSource.loadArtists();

        openTheWebSite();
        waitAuthentication();

        for (Artist artist : artists) {
            clickOn(By.xpath(SEARCH_FIELD), WAIT_LESS_TIME);
            writeText(artist.getArtistPhoneNumber(), By.xpath(SEARCH_FIELD), WAIT_SOME_TIME);

            String resultOfTheSearch = getPathOfResult(artist.getWhatsappName());
            clickOn(By.xpath(resultOfTheSearch), WAIT_SOME_TIME);

            clickOn(By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);
            String message = getMessage(artist.getInformalName());
            writeText(message, By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);

            clickOn(By.xpath(SEND_BUTTON), WAIT_LESS_TIME);
        }
        closeWebDriverInstance();
    }

    private static void openTheWebSite() {
        driver.get(WHATSAPP_WEB_URL);
    }

    private static void waitAuthentication() {
        // Inicializar o WebDriverWait com o WebDriver e o tempo de espera máximo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));

        // Esperar até que o elemento com o ID "side" esteja presente na página
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("side")));

        waiting(WAIT_SOME_TIME);
    }


    private String getPathOfResult(String title) {
        return SPAN_ELEMENT_BY_TITLE.replace(TITLE, title);
    }

    private String getMessage(String informalName) {
        return MESSAGE.replace(INFORMAL_NAME_OF_ARTIST, informalName);
    }
}