package org.example.entities;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist {
    private long id;
    private String artistName;
    private String whatsappName;
    private String informalName;
    private String phoneNumber;
    private Boolean soloSinger;

    private String responsiblePhoneNumber;
    private String responsibleName;
}
