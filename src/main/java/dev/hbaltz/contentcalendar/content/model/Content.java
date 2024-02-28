package dev.hbaltz.contentcalendar.content.model;

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
public record Content(
        Integer id,
        String title,
        String desc,
        Status status,
        Type contentType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {

}
