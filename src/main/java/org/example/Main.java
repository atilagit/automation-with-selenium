package org.example;

import org.example.entities.Artist;
import org.example.usecases.SendMessagesUseCase;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        SendMessagesUseCase sendMessagesUseCase = new SendMessagesUseCase();

        Artist artist1 = Artist.builder()
                .artistName("Cris Cremosa 2021")
                .artistPhoneNumber("5519993xxxxxx")
                .soloSinger(true)
                .build();
        Artist artist2 = Artist.builder()
                .artistName("Amanda Bocó")
                .artistPhoneNumber("5519993xxxxxx")
                .soloSinger(false)
                .build();

        sendMessagesUseCase.execute(new ArrayList<>(Arrays.asList(artist1, artist2)));
    }
}