package dev.hbaltz.contentcalendar.content.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * A content manage in your calendar
 *
 * @param id
 * @param title
 * @param desc the description
 * @param status
 * @param contentType
 * @param dateCreated
 * @param dateUpdated
 * @param url
 */
@Table(value = "content")
public record Content(
        @Id
        Integer id,
        @NotBlank
        String title,
        @Column("description")
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {

}
