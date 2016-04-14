package com.edu.chdtu.conference.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ImageValidator {

    private Pattern pattern;

    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public ImageValidator(){
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    public boolean validate(final String image){
        Matcher matcher = pattern.matcher(image);
        return matcher.matches();
    }
}