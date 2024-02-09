package org.example.usecases;

import org.example.datasources.MessagesDataSource;
import org.example.entities.ConfiguredMessages;

import java.util.List;
import java.util.Map;

public class LoadMessagesUseCase {

    private static final String MENSAGEM = "mensagem";
    private static final String ARTISTA = "artista";
    private static final String SOLO = "solo";
    private static final String NAO_SOLO = "nao_solo";
    private static final String RESPONSAVEL_PELO_ARTISTA = "responsavel_pelo_artista";
    private static final String PRODUTOR_DE_VARIOS_ARTISTAS = "produtor_de_varios_artistas";
    private static final String PADRAO = "padrao";

    MessagesDataSource messagesDataSource;

    public LoadMessagesUseCase(MessagesDataSource messagesDataSource) {
        this.messagesDataSource = messagesDataSource;
    }

    public ConfiguredMessages execute() {
        var messages = messagesDataSource.loadMessages();

        Map<String, Map<String, List<String>>> mensagem = messages.get(MENSAGEM);

        return ConfiguredMessages.builder()
                .soloSingerMessages(mensagem.get(ARTISTA).get(SOLO))
                .noSoloSingerMessages(mensagem.get(ARTISTA).get(NAO_SOLO))
                .responsibleSoloSingerMessages(mensagem.get(RESPONSAVEL_PELO_ARTISTA).get(SOLO))
                .responsibleNoSoloSingerMessages(mensagem.get(RESPONSAVEL_PELO_ARTISTA).get(NAO_SOLO))
                .musicalProducerMessages(mensagem.get(PRODUTOR_DE_VARIOS_ARTISTAS).get(PADRAO))
                .build();
    }
}