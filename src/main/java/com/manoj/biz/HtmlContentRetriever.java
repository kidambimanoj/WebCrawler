package com.manoj.biz;

import com.google.common.base.Joiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Connects to the url and retrieves the content.
 * <p>
 * Created by MK033906 on 5/14/2016.
 */
public class HtmlContentRetriever {

  private static final String NEW_LINE = "\n";

  /**
   * Reads the html content
   *
   * @param url - the url path
   * @return the html content as String.
   */
  public String readContent(String url) {
    checkNotNull(url, "url cannot be null or empty");
    checkArgument(!url.trim().isEmpty(), "url cannot be empty");

    String formattedUrl = formatUrl(url);

    try {
      URL oracle = new URL(formattedUrl);
      BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

      List<String> lines = new ArrayList<>();
      for (String inputLine; (inputLine = in.readLine()) != null; ) {
        lines.add(inputLine);
      }
      in.close();
      return Joiner.on(NEW_LINE).join(lines);
    } catch (IOException e) {
      System.err.println("Error connecting to URL: " + formattedUrl);
    }
    return "";
  }

  private String formatUrl(String url) {
    String newUrl = url.trim();
    if (newUrl.startsWith("http://") || newUrl.startsWith("https://")) {
      return newUrl;
    }
    return "http://" + newUrl;
  }
}
