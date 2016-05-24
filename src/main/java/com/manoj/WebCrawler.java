package com.manoj;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class WebCrawler {

  private static final int MAX = 20;
  private Multimap<String, String> result = ArrayListMultimap.create();
  private Map<String, Boolean> map = Maps.newHashMap();

  public Multimap<String, String> crawlByBfs(String url, String word) {
    Queue<String> urlsQueue = new LinkedList<>();
    urlsQueue.add(url);

    while (!urlsQueue.isEmpty()) {
      String poll = urlsQueue.poll();

      if (map.getOrDefault(poll, false)) {
        continue;
      }

      // load the map
      map.put(poll, true);

      //retrieve content
      HtmlContentRetriever contentRetriever = new HtmlContentRetriever();
      String content = contentRetriever.readContent(poll);

      // gather the words
      SentenceFinder finder = new SentenceFinder();
      List<String> sentences = finder.find(content, word);

      if (!sentences.isEmpty()) {
        System.out.println("url: " + poll);
        System.out.println("Words: " + sentences);
        result.putAll(poll, sentences);
      }

      if (result.size() > MAX) {
        return result;
      }

      UrlFinder urlFinder = new UrlFinder();
      Set<String> urls = urlFinder.find(content, Optional.of(poll));

      urlsQueue.addAll(urls);
    }
    return result;
  }

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the url: ");
    String url = scanner.nextLine();
    if (url == null || url.isEmpty()) {
      System.err.println("URL is not provided");
      return;
    }

    String word = scanner.nextLine();
    if (word == null || word.isEmpty()) {
      System.err.println("Word is not provided");
      return;
    }

    WebCrawler crawler = new WebCrawler();
    Multimap<String, String> result = crawler.crawlByBfs(url, word);
    System.out.println(result);
  }
}
