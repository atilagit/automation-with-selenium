package org.example.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private long id;
    private String artistName;
    private String artistPhoneNumber;
    private Boolean soloSinger;

    private String responsiblePhoneNumber;
    private String responsibleName;
}
