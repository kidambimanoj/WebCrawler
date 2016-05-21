package com.manoj;

import com.google.common.collect.Sets;

import java.net.URL;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class UrlFinder {

    private static final String ANCHOR_PREFIX = "<a href=";
    private static final String ANCHOR_SUFFIX = ">";
    private static final String ANY_CHAR = ".";
    private static final String URL_REGEX = ANY_CHAR + "+?";


    public Set<String> find(String text) {
        return find(text, Optional.<String>empty());
    }

    public Set<String> find(String text, Optional<String> url) {
        checkNotNull(text, "text cannot be null");

        if (text.isEmpty()) {
            return Sets.newHashSet();
        }

        Pattern pattern = Pattern.compile("(" + ANCHOR_PREFIX + URL_REGEX + ANCHOR_SUFFIX + ")");
        Matcher matcher = pattern.matcher(text);

        Set<String> urls = Sets.newHashSet();
        while (matcher.find()) {
            String link = extractLinkFromAnchor(matcher.group(1));
            if (link.contains("subversion")) {
                continue;
            }
            if (!link.startsWith("http://") && !link.equals("../") && !link.contains("xml")) {
                if (url.isPresent()) {
                    link = url.get() + link;
                }
            }
            if (isValid(link)) {
                urls.add(link);
            }
        }
        return urls;
    }

    public boolean isValid(String link) {
        try {
            URL oracle = new URL(link);
            return true;
            //new UrlValidator().isValid(link);
        } catch (Exception ex) {
            return false;
        }
    }

    private String extractLinkFromAnchor(String anchorTag) {
        return anchorTag
                .trim()
                .replaceAll("\"", "")
                .replaceAll(ANCHOR_PREFIX, "")
                .replaceAll(ANCHOR_SUFFIX, "")
                .trim();
    }
}
