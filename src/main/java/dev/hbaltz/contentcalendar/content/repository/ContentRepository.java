package dev.hbaltz.contentcalendar.content.repository;

import dev.hbaltz.contentcalendar.content.model.Content;
import org.springframework.data.repository.ListCrudRepository;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
}
