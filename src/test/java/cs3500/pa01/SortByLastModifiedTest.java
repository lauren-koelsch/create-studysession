package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the SortByLastModified class
 */
class SortByLastModifiedTest {


  /**
   * Tests sortByModified method that sorts the list of SummarizedFile by their last modified time
   */
  @Test
  public void testSortByModified() {
    ArrayList<String> exContent = new ArrayList<String>();
    SummarizedFile alpha = new SummarizedFile("alpha.md", 9, 6, exContent);
    SummarizedFile beta = new SummarizedFile("beta.md", 10, 11, exContent);
    SummarizedFile test = new SummarizedFile("test.md", 3, 4, exContent);
    ArrayList<SummarizedFile> unsortedList = new ArrayList<SummarizedFile>();

    unsortedList.add(test);
    unsortedList.add(beta);
    unsortedList.add(alpha);

    SortByLastModified sort = new SortByLastModified();

    ArrayList<SummarizedFile> sortedList = sort.sortByModified(unsortedList);

    assertEquals(4, sortedList.get(0).getModifiedTime());
    assertEquals(6, sortedList.get(1).getModifiedTime());
    assertEquals(11, sortedList.get(2).getModifiedTime());
  }
}