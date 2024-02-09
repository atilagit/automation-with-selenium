package org.example.datasources;

import java.util.List;
import java.util.Map;

public interface MessagesDataSource {
    Map<String, Map<String, Map<String, List<String>>>> loadMessages();
}
