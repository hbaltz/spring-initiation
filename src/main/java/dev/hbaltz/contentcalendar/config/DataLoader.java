package dev.hbaltz.contentcalendar.config;

import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.model.Status;
import dev.hbaltz.contentcalendar.content.model.Type;
import dev.hbaltz.contentcalendar.content.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Used to load initial data in our application
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository contentRepository;

    public DataLoader(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (contentRepository.findAll().isEmpty()) {
            contentRepository.save(
                new Content(
                    null,
                    "Test",
                    "test desc",
                    Status.IN_PROGRESS,
                    Type.ARTICLE,
                    LocalDateTime.now(),
                    null,
                    null
                )
            );
        }
    }
}