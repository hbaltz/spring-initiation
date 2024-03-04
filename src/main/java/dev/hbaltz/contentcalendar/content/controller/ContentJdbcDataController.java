package dev.hbaltz.contentcalendar.content.controller;

import dev.hbaltz.contentcalendar.content.exception.ContentNotFoundresponseStatusException;
import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manage the api for our content using jdbc and a db
 */
@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentJdbcDataController {

    private final ContentRepository contentRepository;

    public ContentJdbcDataController(
            ContentRepository contentRepository
    ) {
        this.contentRepository = contentRepository;
    }

    /**
     * @return the list of all the contents
     */
    @GetMapping("")
    public List<Content> findAllContents() {
        return contentRepository.findAll();
    }

    /**
     * @param id the id of the content we want
     * @return the content if exists else null
     */
    @GetMapping("/{id}")
    public Content findContentById(@PathVariable Integer id) {
        return contentRepository
                .findById(id)
                .orElseThrow(
                        () -> new ContentNotFoundresponseStatusException(id)
                );
    }

    /**
     * @param content the content we want to create
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@RequestBody Content content) {
        contentRepository.save(content);
    }

    /**
     * @param content the modification of the content
     * @param id the id of the content we want to modify
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ContentNotFoundresponseStatusException(id);
        }
        contentRepository.save(content);
    }

    /**
     * @param id the id of the content we want to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ContentNotFoundresponseStatusException(id);
        }
        contentRepository.deleteById(id);
    }
}
