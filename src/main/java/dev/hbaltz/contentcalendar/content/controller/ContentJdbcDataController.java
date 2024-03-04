package dev.hbaltz.contentcalendar.content.controller;

import dev.hbaltz.contentcalendar.content.exception.ContentNotFoundresponseStatusException;
import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.model.Status;
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
        // I do not like of this way of working. For me, we should have a method to save the content and one to update it
        // Having a method that create a new content in the database if the id is not present in the database and that also
        // Update a content if existing in the database is not that predictable when reading the code
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

    /**
     * @param keyword the keyword to filter using the title (Warning it is case sensitive)
     * @return all contents with the title containing the keyword
     *
     * I wouldn't do that like that to filter my content,
     * I would prefer to pass get parameters to have different filters applied at the same time
     * At moment I would continue to follow the tutorial
     */
    @GetMapping("/filter/title/{keyword}")
    public List<Content> findContentListByTitle(@PathVariable String keyword) {
        return contentRepository.findAllByTitleContains(keyword);
    }

    /**
     * @param status the status of the content
     * @return all contents with the title containing the keyword
     *
     * I wouldn't do that like that to filter my content,
     * I would prefer to pass get parameters to have different filters applied at the same time
     * At moment I would continue to follow the tutorial
     */
    @GetMapping("/filter/status/{status}")
    public List<Content> findContentListByTitle(@PathVariable Status status) {
        return contentRepository.findAllByStatus(status);
    }
}
