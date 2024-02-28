package dev.hbaltz.contentcalendar.content.repository;

import dev.hbaltz.contentcalendar.content.exception.ContentIdIllegalArgumentException;
import dev.hbaltz.contentcalendar.content.model.Content;
import dev.hbaltz.contentcalendar.content.model.Status;
import dev.hbaltz.contentcalendar.content.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Manage content data
 */
@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {
    }

    @PostConstruct
    private void init() {
        contentList.add(
                new Content(
                        1,
                        "How to be a great developer",
                        "Interrogation about how to be a better developer",
                        Status.IDEA,
                        Type.ARTICLE,
                        LocalDateTime.now(),
                        null,
                        ""
                )
        );
    }

    /**
     * @return all the contents
     */
    public List<Content> findAllContents() {
        return contentList;
    }

    /**
     * @param id the id of the content he wants to find
     * @return the content with the id 'id'
     */
    public Optional<Content> findContentById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    /**
     * Save the content in the content list
     *
     * @param content the content we want to save
     *
     *  FIXME: when creating a content we shouldn't have to pass the id of the creation or updated date
     *                this should be manage server-side and automatically,
     */
    public void saveContent(Content content) {
        contentList.add(content);
    }

    /**
     * @param content the new data for the content
     * @param id the id of the content we want to update
     *
     *  FIXME: we shouldn't have to pass the all content just a partial object to do the object
     *           some attribute can't be modify with this request (id, creation date or modification date,
     *           the last one should be automatically updated)
     *
     */
    public void updateContent(Content content, Integer id) {
        Integer index = getIndexById(id);

        if (index == -1) {
            throw new ContentIdIllegalArgumentException(id);
        }

        contentList.set(index, content);
    }

    /**
     * Delete a content from the list if presents
     *
     * @param id the id of the content we want to delete
     */
    public void deleteContentById(Integer id) {
        int index = getIndexById(id);

        if (index == -1) {
            throw new ContentIdIllegalArgumentException(id);
        }

        contentList.remove(index);
    }


    /**
     * @param id the id of the content we want to verify the existence
     * @return the Index in the array of the content with the given id else -1
     */
    private Integer getIndexById(Integer id) {
        int index = -1;

        for (int i = 0; i < contentList.size(); i++) {
            if (Objects.equals(contentList.get(i).id(), id)) {
                index = i;
                break; // Exit loop once the object is found
            }
        }

        return index;
    }
}
