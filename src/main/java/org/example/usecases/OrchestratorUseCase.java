package org.example.usecases;

import org.example.entities.Artist;

import java.util.List;

public class OrchestratorUseCase {

    LoadArtistsUseCase loadArtistsUseCase;
    LoginUseCase loginUseCase;
    SendMessageUseCase sendMessageUseCase;
    SendSongUseCase sendSongUseCase;
    LogoutUseCase logoutUseCase;

    public OrchestratorUseCase(LoadArtistsUseCase loadArtistsUseCase, LoginUseCase loginUseCase, SendMessageUseCase sendMessageUseCase, SendSongUseCase sendSongUseCase, LogoutUseCase logoutUseCase) {
        this.loadArtistsUseCase = loadArtistsUseCase;
        this.loginUseCase = loginUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.sendSongUseCase = sendSongUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    public void execute() {
        List<Artist> artists = loadArtistsUseCase.execute();
        loginUseCase.execute();

        for (Artist artist : artists) {
            sendMessageUseCase.execute(artist);
            sendSongUseCase.execute();
        }
        logoutUseCase.execute();
    }
}