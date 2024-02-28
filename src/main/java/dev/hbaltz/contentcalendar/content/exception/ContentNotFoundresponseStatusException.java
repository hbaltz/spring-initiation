package dev.hbaltz.contentcalendar.content.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ContentNotFoundresponseStatusException extends ResponseStatusException {

    public ContentNotFoundresponseStatusException(Integer id) {
        super(HttpStatus.NOT_FOUND, "Content " + id + " not found");
    }
}
