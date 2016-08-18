package com.manoj.ui;

import com.google.common.collect.Multimap;
import com.manoj.biz.WebCrawler;

/**
 * Created by MK033906 on 5/26/2016.
 */
public class Driver {

  public static void main(String[] args) {

    if (args.length != 2) {
      throw new AssertionError("Args should be two");
    }

    WebCrawler crawler = new WebCrawler();
    final Multimap<String, String> result = crawler.crawlByBfs(args[0], args[1]);
    System.out.println(result);
  }
}
