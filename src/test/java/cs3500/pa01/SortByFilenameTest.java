package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Tests the SortByFilename class
 */
class SortByFilenameTest {

  /**
   * Tests sortByFilename method that sorts the list of SummarizedFile by their filename
   */
  @Test
  public void testSortByFilename() {
    ArrayList<String> exContent = new ArrayList<String>();
    SummarizedFile alpha = new SummarizedFile("alpha.md", 10, 10, exContent);
    SummarizedFile beta = new SummarizedFile("beta.md", 10, 10, exContent);
    SummarizedFile test = new SummarizedFile("test.md", 10, 10, exContent);
    ArrayList<SummarizedFile> unsortedList = new ArrayList<SummarizedFile>();

    unsortedList.add(test);
    unsortedList.add(beta);
    unsortedList.add(alpha);

    SortByFilename sort = new SortByFilename();

    ArrayList<SummarizedFile> sortedList = sort.sortByFilename(unsortedList);

    assertEquals("alpha.md", sortedList.get(0).getFilename());
    assertEquals("beta.md", sortedList.get(1).getFilename());
    assertEquals("test.md", sortedList.get(2).getFilename());
  }
}