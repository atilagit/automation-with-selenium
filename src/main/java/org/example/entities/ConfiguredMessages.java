package org.example.entities;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConfiguredMessages {
    private List<String> soloSingerMessages;
    private List<String> noSoloSingerMessages;
    private List<String> responsibleSoloSingerMessages;
    private List<String> responsibleNoSoloSingerMessages;
    private List<String> musicalProducerMessages;
}
