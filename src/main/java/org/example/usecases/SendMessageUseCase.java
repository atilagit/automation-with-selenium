package org.example.usecases;

import org.example.entities.Artist;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.example.utils.Constants.WAIT_LESS_TIME;
import static org.example.utils.Constants.WAIT_SOME_TIME;
import static org.example.utils.WebDriverSingleton.clickOn;
import static org.example.utils.WebDriverSingleton.writeText;

public class SendMessageUseCase {

    public static final String TITLE = "{title}";
    public static final String INFORMAL_NAME_OF_ARTIST = "{informalNameOfArtist}";
    public static final String MESSAGE = "Fala " + INFORMAL_NAME_OF_ARTIST +" tudo blz? Isso é uma mensagem de teste, pode ignorar.";

    public static final String SEARCH_FIELD = "//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p";
    public static final String MESSAGE_FIELD = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p";
    public static final String SPAN_ELEMENT_BY_TITLE = "//span[@title='" + TITLE +"']";

    public void execute(Artist artist) {
        clickOn(By.xpath(SEARCH_FIELD), WAIT_LESS_TIME);
        writeText(artist.getArtistPhoneNumber(), By.xpath(SEARCH_FIELD), WAIT_SOME_TIME);

        String resultOfTheSearch = getPathOfResult(artist.getWhatsappName());
        clickOn(By.xpath(resultOfTheSearch), WAIT_SOME_TIME);

        String message = getMessage(artist.getInformalName());
        writeText(message, By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);

        writeText(Keys.ENTER, By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);
    }

    private String getPathOfResult(String title) {
        return SPAN_ELEMENT_BY_TITLE.replace(TITLE, title);
    }

    private String getMessage(String informalName) {
        return MESSAGE.replace(INFORMAL_NAME_OF_ARTIST, informalName);
    }
}