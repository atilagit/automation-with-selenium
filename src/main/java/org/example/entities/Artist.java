package org.example.entities;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist {
    private long id;
    private String phoneNumber;
    private String whatsappName;
    private String artistName;
    private String informalArtistName;
    private Boolean soloSinger;
    private Boolean responsibleContact;
    private String informalResponsibleName;
    private Boolean producerForSeveralArtists;
    private String informalNameOfProducer;
}
