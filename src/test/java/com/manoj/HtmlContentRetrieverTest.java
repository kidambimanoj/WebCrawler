package com.manoj;

import com.googlecode.catchexception.CatchException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
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
        CatchException.catchException(contentRetriever).readContent("hjhjhjk");
        assertThat(CatchException.<RuntimeException>caughtException().getCause(), is(instanceOf(UnknownHostException.class)));
    }

    @Test
    public void testReadContentWithActualUrl() {
        String content = contentRetriever.readContent("http://scm.powerchart.cerner.corp/");
        System.out.println(content);
    }

    @Test
    public void testReadContentWithActualUrlWithoutHeaders() {
        String content = contentRetriever.readContent("scm.powerchart.cerner.corp/");
        System.out.println(content);
    }
}
