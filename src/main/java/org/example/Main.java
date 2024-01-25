package org.example;

import org.example.datasources.ExcelArtistDataSource;
import org.example.usecases.*;

public class Main {
    public static void main(String[] args) {

        OrchestratorUseCase orchestratorUseCase = new OrchestratorUseCase(
                new LoadArtistsUseCase(new ExcelArtistDataSource()),
                new LoginUseCase(),
                new SendMessageUseCase(),
                new SendSongUseCase(),
                new LogoutUseCase());

        orchestratorUseCase.execute();
    }
}