package org.example.entities;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ObjectToSend {
    private String phoneNumber;
    private String whatsappName;
    private List<String> messagesToSend;
}
