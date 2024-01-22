package org.example;

import org.example.datasources.ExcelArtistDataSource;
import org.example.usecases.SendMessagesUseCase;

public class Main {
    public static void main(String[] args) {

        SendMessagesUseCase sendMessagesUseCase = new SendMessagesUseCase(new ExcelArtistDataSource());
        sendMessagesUseCase.execute();
    }
}