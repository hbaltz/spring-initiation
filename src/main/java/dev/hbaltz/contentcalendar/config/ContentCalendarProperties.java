package dev.hbaltz.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Defined properties for our application
 * @param welcomeMessage the welcome message of the application
 * @param about an explication of what our application does
 */
@ConfigurationProperties(value="cc")
public record ContentCalendarProperties(String welcomeMessage, String about) {
}
