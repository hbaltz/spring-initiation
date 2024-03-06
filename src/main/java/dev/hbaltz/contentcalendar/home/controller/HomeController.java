package dev.hbaltz.contentcalendar.home.controller;

import dev.hbaltz.contentcalendar.config.ContentCalendarProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Used to controls api for home page
 */
@RestController
public class HomeController {

    private final ContentCalendarProperties properties;

    public HomeController(ContentCalendarProperties properties) {
        this.properties = properties;
    }


    /**
     * @return the home properties
     */
    @GetMapping("/")
    public ContentCalendarProperties home() {
        return properties;
    }
}
