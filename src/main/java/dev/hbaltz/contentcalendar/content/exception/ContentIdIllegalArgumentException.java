package dev.hbaltz.contentcalendar.content.exception;

public class ContentIdIllegalArgumentException extends IllegalArgumentException{
    public ContentIdIllegalArgumentException(Integer id) {
        super("Content " + id + " not found");
    }
}
