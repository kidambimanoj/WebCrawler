package com.manoj.mapper;

import com.manoj.object.UrlContainer;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class UrlMapper {

    public Set<UrlContainer> map(Set<String> links) {
       return links.stream()
                .filter(e -> isValid(e))
                .map(e -> {
                    UrlContainer urlContainer = new UrlContainer();
                    urlContainer.setUrl(e);
                    return urlContainer;
                }).collect(toSet());

    }

    private boolean isValid(String url) {
        return new UrlValidator().isValid(url);
    }
}
