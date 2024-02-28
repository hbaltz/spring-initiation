package dev.hbaltz.contentcalendar.content.controller;

import dev.hbaltz.contentcalendar.content.exception.ContentNotFoundresponseStatusException;
import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.repository.ContentCollectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manage the api for our content
 */
@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentCollectionRepository contentCollectionRepository;

    public ContentController(
            ContentCollectionRepository contentCollectionRepository
    ) {
        this.contentCollectionRepository = contentCollectionRepository;
    }

    /**
     * @return the list of all the contents
     */
    @GetMapping("")
    public List<Content> findAllContents() {
        return contentCollectionRepository.findAllContents();
    }

    /**
     * @param id the id of the content we want
     * @return the content if exists else null
     */
    @GetMapping("/{id}")
    public Content findContentById(@PathVariable Integer id) {
        return contentCollectionRepository
                .findContentById(id)
                .orElseThrow(
                        () -> new ContentNotFoundresponseStatusException(id)
                );
    }

    /**
     * @param content the content we want to create
     *
     *  FIXME: when creating a content we shouldn't have to pass the id of the creation or updated date
     *                this should be manage server-side and automatically,
     *                if not done in the tutorial, I will see how I can do that
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void createContent(@RequestBody Content content) {
        contentCollectionRepository.saveContent(content);
    }

    /**
     * @param content the modification of the content
     * @param id the id of the content we want to modify
     *
     *  FIXME: we shouldn't have to pass the all content just a partial object to do the object
     *           some attribute can't be modify with this request (id, creation date or modification date,
     *           the last one should be automatically updated)
     *            if not done in the tutorial, I will see how I can do that
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateContent(@RequestBody Content content, @PathVariable Integer id) {
        try {
            contentCollectionRepository.updateContent(content, id);
        } catch (IllegalArgumentException e) {
           throw new ContentNotFoundresponseStatusException(id);
        }
    }

    /**
     * @param id the id of the content we want to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Integer id) {
        try {
            contentCollectionRepository.deleteContentById(id);
        } catch (IllegalArgumentException e) {
            throw new ContentNotFoundresponseStatusException(id);
        }
    }
}
