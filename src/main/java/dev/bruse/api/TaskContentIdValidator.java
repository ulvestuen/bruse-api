package dev.bruse.api;

import java.util.regex.Pattern;

public class TaskContentIdValidator {

    public static boolean isValid(final String contentId) {
        return Pattern.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
                               contentId);
    }

}
