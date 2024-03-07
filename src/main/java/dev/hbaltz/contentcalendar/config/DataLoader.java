package dev.hbaltz.contentcalendar.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

/**
 * Used to load initial data in our application
 *
 * Only for dev profile
 */
@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository contentRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(
            ContentRepository contentRepository,
            ObjectMapper objectMapper
    ) {
        this.contentRepository = contentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (contentRepository.findAll().isEmpty()) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
                List<Content> contentDataList = objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){});
                contentRepository.saveAll(contentDataList);
            }
        }
    }
}