package dev.hbaltz.contentcalendar.home.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Used to controls api for home page
 */
@RestController
public class HomeController {

    @Value("${cc.welcome-message: Default Welcome Message}")
    private String welcomeMessage;

    @Value("${cc.about: Default About}")
    private String about;

    /**
     * @return the welcome message
     */
    @GetMapping("/")
    public Map<String,String> displayWelcomeMessage() {
        return Map.of(
                "welcomeMessage", welcomeMessage,
                "about", about
        );
    }
}
