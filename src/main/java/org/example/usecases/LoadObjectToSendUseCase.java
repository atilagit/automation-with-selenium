package org.example.usecases;

import org.example.entities.Artist;
import org.example.entities.ConfiguredMessages;
import org.example.entities.ObjectToSend;

import java.util.ArrayList;
import java.util.List;

public class LoadObjectToSendUseCase {

    public static final String NOME_INFORMAL_DO_ARTISTA = "{nome_informal_do_artista}";
    public static final String NOME_INFORMAL_DO_RESPONSAVEL = "{nome_informal_do_responsavel}";
    public static final String NOME_INFORMAL_DO_PRODUTOR = "{nome_informal_do_produtor}";

    public ObjectToSend execute(Artist artist, ConfiguredMessages configuredMessages) {
        return ObjectToSend.builder()
                .phoneNumber(artist.getPhoneNumber())
                .whatsappName(artist.getWhatsappName())
                .messagesToSend(handledMessages(artist, configuredMessages))
                .build();
    }

    private  List<String> handledMessages(Artist artist, ConfiguredMessages configuredMessages) {
        List<String> handledMessages = new ArrayList<>();
        List<String> unhandledMessages = getMessages(artist, configuredMessages);
        unhandledMessages.forEach(x -> handledMessages.add(stringReplacer(x, artist)));
        return handledMessages;
    }

    private List<String> getMessages(Artist artist, ConfiguredMessages configuredMessages) {
        if (Boolean.TRUE.equals(artist.getProducerForSeveralArtists())) {
            return configuredMessages.getMusicalProducerMessages();
        }
        if (Boolean.TRUE.equals(artist.getResponsibleContact()) && Boolean.TRUE.equals(artist.getSoloSinger())) {
            return configuredMessages.getResponsibleSoloSingerMessages();
        }
        if (Boolean.TRUE.equals(artist.getResponsibleContact()) && Boolean.FALSE.equals(artist.getSoloSinger())) {
            return configuredMessages.getResponsibleNoSoloSingerMessages();
        }
        if (Boolean.FALSE.equals(artist.getResponsibleContact()) && Boolean.TRUE.equals(artist.getSoloSinger())) {
            return configuredMessages.getSoloSingerMessages();
        }
        if (Boolean.FALSE.equals(artist.getResponsibleContact()) && Boolean.FALSE.equals(artist.getSoloSinger())) {
            return configuredMessages.getNoSoloSingerMessages();
        }
        return new ArrayList<>();
    }

    private String stringReplacer(String message, Artist artist) {
        return message
                .replace(NOME_INFORMAL_DO_ARTISTA, artist.getInformalArtistName())
                .replace(NOME_INFORMAL_DO_RESPONSAVEL, artist.getInformalResponsibleName())
                .replace(NOME_INFORMAL_DO_PRODUTOR, artist.getInformalNameOfProducer());
    }
}