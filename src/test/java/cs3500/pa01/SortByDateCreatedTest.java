package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests sortByDateCreated method that sorts the list of SummarizedFile by their create-date
 */
class SortByDateCreatedTest {

  /**
   * Tests sortByCreated method that sorts the list of SummarizedFile by the date they were created
   */
  @Test
  public void testSortByCreated() {
    ArrayList<String> exContent = new ArrayList<String>();
    SummarizedFile alpha = new SummarizedFile("alpha.md", 9, 6, exContent);
    SummarizedFile beta = new SummarizedFile("beta.md", 10, 11, exContent);
    SummarizedFile test = new SummarizedFile("test.md", 3, 4, exContent);
    ArrayList<SummarizedFile> unsortedList = new ArrayList<SummarizedFile>();

    unsortedList.add(test);
    unsortedList.add(beta);
    unsortedList.add(alpha);

    SortByDateCreated sort = new SortByDateCreated();

    ArrayList<SummarizedFile> sortedList = sort.sortByCreated(unsortedList);

    assertEquals(3, sortedList.get(0).getCreatedTime());
    assertEquals(9, sortedList.get(1).getCreatedTime());
    assertEquals(10, sortedList.get(2).getCreatedTime());
  }
}