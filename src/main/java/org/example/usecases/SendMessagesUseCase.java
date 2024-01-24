package org.example.usecases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.datasources.ArtistDataSource;
import org.example.entities.Artist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.example.utils.Pauser.waitSomeTimeBetween;

public class SendMessagesUseCase {

    public static final String TITLE = "{title}";
    public static final String INFORMAL_NAME_OF_ARTIST = "{informalNameOfArtist}";
    public static final String MESSAGE = "Fala " + INFORMAL_NAME_OF_ARTIST +" tudo blz? Isso é uma mensagem de teste, pode ignorar.";
    public static final String WAIT_LESS_TIME = "waitLessTime";
    public static final Integer MIN_WAIT_LESS_TIME = 1000;
    public static final Integer MAX_WAIT_LESS_TIME = 3000;
    public static final String WAIT_SOME_TIME = "waitSomeTime";
    public static final Integer MIN_WAIT_SOME_TIME = 2500;
    public static final Integer MAX_WAIT_SOME_TIME = 6000;

    public static final String WHATSAPP_WEB_URL = "https://web.whatsapp.com/";
    public static final String SEARCH_FIELD = "//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p";
    public static final String MESSAGE_FIELD = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p";
    public static final String SEND_BUTTON = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span";
    public static final String SPAN_ELEMENT_BY_TITLE = "//span[@title='" + TITLE +"']";

    ArtistDataSource artistDataSource;
    static WebDriver driver = WebDriverManager.chromedriver().create();


    public SendMessagesUseCase(ArtistDataSource artistDataSource) {
        this.artistDataSource = artistDataSource;
    }

    public void execute() {
        List<Artist> artists = artistDataSource.loadArtists();

        openTheWebSite();
        waitAuthentication();

        for (Artist artist : artists) {
            clickOn(SEARCH_FIELD, WAIT_LESS_TIME);
            writeText(artist.getArtistPhoneNumber(), SEARCH_FIELD, WAIT_SOME_TIME);

            String resultOfTheSearch = getPathOfResult(artist.getWhatsappName());
            clickOn(resultOfTheSearch, WAIT_SOME_TIME);

            clickOn(MESSAGE_FIELD, WAIT_LESS_TIME);
            String message = getMessage(artist.getInformalName());
            writeText(message, MESSAGE_FIELD, WAIT_LESS_TIME);

            clickOn(SEND_BUTTON, WAIT_LESS_TIME);
        }
        driver.quit();
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

    private static void clickOn(String xpath, String typeOfWaiting) {
        driver.findElement(By.xpath(xpath)).click();
        waiting(typeOfWaiting);
    }

    private static void waiting(String typeOfWaiting) {
        if (WAIT_LESS_TIME.equals(typeOfWaiting)){
            waitSomeTimeBetween(MIN_WAIT_LESS_TIME, MAX_WAIT_LESS_TIME);
        } else if (WAIT_SOME_TIME.equals(typeOfWaiting)) {
            waitSomeTimeBetween(MIN_WAIT_SOME_TIME, MAX_WAIT_SOME_TIME);
        }
    }

    private static void writeText(String text, String xpath, String typeOfWaiting) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
        waiting(typeOfWaiting);
    }

    private String getPathOfResult(String title) {
        return SPAN_ELEMENT_BY_TITLE.replace(TITLE, title);
    }

    private String getMessage(String informalName) {
        return MESSAGE.replace(INFORMAL_NAME_OF_ARTIST, informalName);
    }
}