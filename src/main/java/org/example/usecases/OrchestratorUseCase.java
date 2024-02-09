package org.example.usecases;

import org.example.entities.Artist;
import org.example.entities.ConfiguredMessages;
import org.example.entities.ObjectToSend;

import java.util.List;

public class OrchestratorUseCase {

    LoadArtistsUseCase loadArtistsUseCase;
    LoadMessagesUseCase loadMessagesUseCase;
    LoginUseCase loginUseCase;
    LoadObjectToSendUseCase loadObjectToSendUseCase;
    SendMessageUseCase sendMessageUseCase;
    SendSongUseCase sendSongUseCase;
    LogoutUseCase logoutUseCase;

    public OrchestratorUseCase(LoadArtistsUseCase loadArtistsUseCase, LoadMessagesUseCase loadMessagesUseCase, LoginUseCase loginUseCase, LoadObjectToSendUseCase loadObjectToSendUseCase, SendMessageUseCase sendMessageUseCase, SendSongUseCase sendSongUseCase, LogoutUseCase logoutUseCase) {
        this.loadArtistsUseCase = loadArtistsUseCase;
        this.loadMessagesUseCase = loadMessagesUseCase;
        this.loginUseCase = loginUseCase;
        this.loadObjectToSendUseCase = loadObjectToSendUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.sendSongUseCase = sendSongUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    public void execute() {
        List<Artist> artists = loadArtistsUseCase.execute();
        ConfiguredMessages configuredMessages = loadMessagesUseCase.execute();
        loginUseCase.execute();

        for (Artist artist : artists) {
            ObjectToSend objectToSend = loadObjectToSendUseCase.execute(artist, configuredMessages);
            sendMessageUseCase.execute(objectToSend);
            //sendSongUseCase.execute();
        }
        logoutUseCase.execute();
    }
}