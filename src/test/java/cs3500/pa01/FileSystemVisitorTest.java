package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the FileSystemVisitor class
 */
class FileSystemVisitorTest {
  static final String SAMPLE_INPUTS_DIRECTORY = "src/test/resources";
  FileSystemVisitor mfv = new FileSystemVisitor();
  FileSystemVisitor mfv2 = new FileSystemVisitor();

  @BeforeEach
  public void setup() throws IOException {
    Files.walkFileTree(Path.of(SAMPLE_INPUTS_DIRECTORY), mfv);
    Files.walkFileTree(Path.of("examples/test.md"), mfv2);
  }

  @Test
  public void testGetSortedList() throws IOException {

    ArrayList<SummarizedFile> summarizedFiles = new ArrayList<SummarizedFile>();

    ArrayList<String> exContent = new ArrayList<String>();
    SummarizedFile array = new SummarizedFile("array.md", 4, 9, exContent);
    SummarizedFile vector = new SummarizedFile("vector.md", 2, 6, exContent);
    SummarizedFile test = new SummarizedFile("test.md", 6, 3, exContent);

    summarizedFiles.add(array);
    summarizedFiles.add(vector);
    summarizedFiles.add(test);

    assertEquals(SortByFilename.sortByFilename(summarizedFiles),
        mfv.getSortedList("filename", summarizedFiles));
    assertEquals(SortByDateCreated.sortByCreated(summarizedFiles),
        mfv.getSortedList("created", summarizedFiles));
    assertEquals(SortByLastModified.sortByModified(summarizedFiles),
        mfv.getSortedList("modified", summarizedFiles));
  }

  /**
   * Tests to make sure an exception is thrown if an illegal name is given
   */
  @Test
  public void testInvalidOrderingFlag() {

    ArrayList<SummarizedFile> summarizedFiles = new ArrayList<SummarizedFile>();

    assertThrows(
        IllegalArgumentException.class, () -> mfv.getSortedList("hello", summarizedFiles));
  }

  @Test
  public void testReformat() throws IOException {
    ArrayList<String> list = new ArrayList<String>();

    assertEquals(mfv2.reformat(), list);
  }
}