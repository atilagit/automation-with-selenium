package org.example.usecases;

import org.example.entities.ObjectToSend;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.example.utils.Constants.WAIT_LESS_TIME;
import static org.example.utils.Constants.WAIT_SOME_TIME;
import static org.example.utils.WebDriverSingleton.clickOn;
import static org.example.utils.WebDriverSingleton.writeText;

public class SendMessageUseCase {

    public static final String TITLE = "{title}";
    public static final String SEARCH_FIELD = "//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p";
    public static final String MESSAGE_FIELD = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p";
    public static final String SPAN_ELEMENT_BY_TITLE = "//span[@title='" + TITLE +"']";

    public void execute(ObjectToSend objectToSend) {
        clickOn(By.xpath(SEARCH_FIELD), WAIT_LESS_TIME);
        writeText(objectToSend.getPhoneNumber(), By.xpath(SEARCH_FIELD), WAIT_SOME_TIME);

        String resultOfTheSearch = getPathOfResult(objectToSend.getWhatsappName());
        clickOn(By.xpath(resultOfTheSearch), WAIT_SOME_TIME);

        for (String message : objectToSend.getMessagesToSend()) {
            writeText(message, By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);
            writeText(Keys.ENTER, By.xpath(MESSAGE_FIELD), WAIT_LESS_TIME);
        }
    }

    private String getPathOfResult(String title) {
        return SPAN_ELEMENT_BY_TITLE.replace(TITLE, title);
    }
}