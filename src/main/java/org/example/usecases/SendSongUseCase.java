package org.example.usecases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.example.utils.Constants.WAIT_LESS_TIME;
import static org.example.utils.Constants.WAIT_SOME_TIME;
import static org.example.utils.WebDriverSingleton.*;

public class SendSongUseCase {

    public static final String MUSIC_NAME = "O sim eu já tenho";
    public static final String CAMINHO_DO_ARQUIVO = "C:\\composicao\\automacao\\musicas_para_disparo\\" + MUSIC_NAME + ".mp3";
    public static final String FIELD_TO_TEXT_FOR_FILE = "//*[@id=\"app\"]/div/div[2]/div[2]/div[2]/span/div/span/div/div/div[2]/div/div[1]/div[3]/div/div/div[1]/div[1]/p";
    public static final String DIV_INPUT_FILE = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[1]/div[2]/div/span/div/ul/div/div[1]/li/div/input";
    public static final String ATTACH_ICON = "//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[1]/div[2]/div/div/div/span";

    public void execute() {
        clickOn(By.xpath(ATTACH_ICON), WAIT_LESS_TIME);
        writeText(CAMINHO_DO_ARQUIVO, By.xpath(DIV_INPUT_FILE), WAIT_SOME_TIME);
        writeText(MUSIC_NAME, By.xpath(FIELD_TO_TEXT_FOR_FILE), WAIT_LESS_TIME);
        writeText(Keys.ENTER, By.xpath(FIELD_TO_TEXT_FOR_FILE), WAIT_LESS_TIME);
    }
}