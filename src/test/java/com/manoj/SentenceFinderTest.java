package com.manoj;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by MK033906 on 5/14/2016.
 */
public class SentenceFinderTest {

  private SentenceFinder finder;

  @Before
  public void setUp() {
    finder = new SentenceFinder();
  }

  @Test
  public void testFinder() {
    String sampleText = "some content here"
        + " some more content here too"
        + " here as well"
        + "  why not here"
        + " here you go, this is another text"
        + " some more here";

    List<String> result = finder.find(sampleText, "here");
    assertThat(result, not(empty()));
  }
}
