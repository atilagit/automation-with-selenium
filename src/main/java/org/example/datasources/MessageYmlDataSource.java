package org.example.datasources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MessageYmlDataSource implements MessagesDataSource {

    @Override
    public Map<String, Map<String, Map<String, List<String>>>> loadMessages() {
        Map<String, Map<String, Map<String, List<String>>>> messages = null;
        String filePath = "C:\\composicao\\automacao\\configs\\messages.yml";
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            messages = objectMapper.readValue(new File(filePath), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
