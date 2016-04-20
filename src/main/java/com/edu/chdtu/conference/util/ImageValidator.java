package com.edu.chdtu.conference.util;

import java.util.regex.Pattern;

public class ImageValidator {

    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public static boolean validate(final String image){
        Pattern pattern = Pattern.compile(IMAGE_PATTERN);
        return pattern.matcher(image).matches();
    }
}