package com.manoj.biz;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class HtmlContentRetrieverTest {

    private HtmlContentRetriever contentRetriever;

    @Before
    public void setUp() {
        contentRetriever = new HtmlContentRetriever();
    }

    @Test(expected = NullPointerException.class)
    public void testReadContentWithNull() {
        contentRetriever.readContent(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadContent() {
        contentRetriever.readContent("    ");
    }

    @Test
    public void testReadContentWithDummyInput() {
        String content = contentRetriever.readContent("hjhjhjk");
        assertThat(content, isEmptyOrNullString());
    }

    @Test
    public void testReadContentWithActualUrl() {
        String content = contentRetriever.readContent("http://www.google.com");
        assertThat(content, not(isEmptyOrNullString()));
    }

    @Test
    public void testReadContentWithActualUrlWithoutHeaders() {
        String content = contentRetriever.readContent("www.google.com");
        assertThat(content, not(isEmptyOrNullString()));
    }
}
