package dev.hbaltz.contentcalendar.content.repository;

import dev.hbaltz.contentcalendar.content.model.Content;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {

    List<Content> findAllByTitleContains(String keyword);
}
