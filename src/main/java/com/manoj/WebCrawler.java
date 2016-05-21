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

    public void crawlByBfs(String url, String word) {
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
                return;
            }

            UrlFinder urlFinder = new UrlFinder();
            Set<String> urls = urlFinder.find(content, Optional.of(poll));

            urlsQueue.addAll(urls);
        }
//        UrlMapper urlMapper = new UrlMapper();
//        Set<UrlContainer> urlContainerSet = urlMapper.map(urls);
    }

    public Multimap<String, String> getResult() {
        return result;
    }
}
