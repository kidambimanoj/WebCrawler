package com.manoj.biz;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class UrlFinderTest {

  private static final String ANCHOR_PREFIX = "<a href=\"";
  private static final String ANCHOR_SUFFIX = "\">";
  private UrlFinder urlFinder;

  @Before
  public void setUp() {
    urlFinder = new UrlFinder();
  }

  @Test(expected = NullPointerException.class)
  public void testFindLinkWithNull() {
    urlFinder.find(null);
  }

  @Test
  public void testFindWithEmptyString() {
    final Set<String> strings = urlFinder.find("");
    assertThat(strings, is(empty()));
  }

  @Test
  public void testFindWithoutLink() {
    assertThat(urlFinder.find("some string"), is(empty()));
  }

  @Test
  public void testFindWithLink() {
    String link = "http://www.google.com";
    String anchorUrl = ANCHOR_PREFIX + link + ANCHOR_SUFFIX;
    Set<String> urls = Sets.newHashSet(link);
    assertThat(urlFinder.find("some string " + anchorUrl), is(urls));
  }

  @Test
  public void testFindWithMultipleSameLinks() {
    String link = "http://www.google.com";
    String anchorUrl = ANCHOR_PREFIX + link + ANCHOR_SUFFIX;

    Set<String> urls = Sets.newHashSet(link);
    assertThat(urlFinder.find(anchorUrl + " " + anchorUrl + anchorUrl), is(urls));
  }

  @Test
  public void testFindWithMultipleLinks() {
    String link = "http://www.google.com";
    String anchorUrl = ANCHOR_PREFIX + link + ANCHOR_SUFFIX;

    String link2 = "http://www.google2.com";
    String anchorUrl2 = ANCHOR_PREFIX + link2 + ANCHOR_SUFFIX;

    Set<String> urls = Sets.newHashSet(link, link2);
    assertThat(urlFinder.find(anchorUrl + anchorUrl + anchorUrl2), is(urls));
  }

  @Test
  public void testFindWithMultipleLinksWithRealLink() {
    String link = "http://www.google.com";
    String anchorUrl = ANCHOR_PREFIX + link + ANCHOR_SUFFIX;

    String spacedLink2 = "http://www.g oogf le2.com";
    String anchorUrl2 = ANCHOR_PREFIX + spacedLink2 + ANCHOR_SUFFIX;

    Set<String> urls = Sets.newHashSet(link);
    String text = "<html>\n" +
        "<head>\n" +
        "<title>\n" +
        "Powerchart SCM\n" +
        "</title>\n" +
        "</head>\n" +
        "<body>\n" +
        "<a href=\"http://scm.powerchart.cerner.corp/svn\" >Repository site</a> <br />\n" +
        "<a href=\"http://scm.powerchart.cerner.corp/viewvc\" >ViewVc repository viewer</a> <br />\n" +
        "<a href=\"http://wiki.cerner.com/PowerChartEng/index.php/Java_Framework\" >PowerChart Java Framework Wiki</a>\n" +
        "</body>\n" +
        "</html>";
    Set<String> strings = urlFinder.find(text);
    System.out.println(strings);
  }

}
