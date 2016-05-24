package com.manoj;

import com.google.common.collect.Multimap;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class WebCrawlerTest {

    private WebCrawler webCrawler;

    @Before
    public void setUp() {
        webCrawler = new WebCrawler();
    }

    @Test
    public void testReadContent() {
        String url = "http://scm.powerchart.cerner.corp/svn/java/com.cerner.interop/branches/develop/";
        String word = "Manager";
        Multimap<String, String> result = webCrawler.crawlByBfs(url, word);
        System.out.println(result);
    }

}
