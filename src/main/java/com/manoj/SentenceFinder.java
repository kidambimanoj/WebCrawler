package com.manoj;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class SentenceFinder {

  public List<String> findSentence(String content, String word) {
    Preconditions.checkNotNull(content, "content cannot be null");
    Preconditions.checkNotNull(word, "word cannot be null");
    Preconditions.checkArgument(!word.trim().isEmpty(), "word cannot be empty");

    if (content.isEmpty()) {
      return Lists.newArrayList();
    }

    List<String> sentences = Lists.newArrayList();

    return sentences;
  }

  public List<String> find(String content, String word) {
    Preconditions.checkNotNull(content, "content cannot be null");
    Preconditions.checkNotNull(word, "word cannot be null");
    Preconditions.checkArgument(!word.trim().isEmpty(), "word cannot be empty");

    if (content.isEmpty()) {
      return Lists.newArrayList();
    }

    List<String> sentences = Lists.newArrayList();

    Pattern pattern = Pattern.compile("(" + word + ") (.+?) ");
    Matcher matcher = pattern.matcher(content);

    while (matcher.find()) {
      sentences.add(matcher.group(1) + " " + matcher.group(2));
    }
    return sentences;
  }
}
