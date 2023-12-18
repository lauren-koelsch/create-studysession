package cs3500.pa01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Tests the SummarizedFile class
 */
class SummarizedFileTest {

  /**
   * Tests the rewriteFiles() method that makes a given file into a SummarizedFile
   */
  @Test
  public void testRewriteFiles() throws IOException {
    SummarizedFile sf = new SummarizedFile("name", 4, 14,
        readFromFile(new File("src/test/resources/arrays.md")));
    ArrayList<String> contents = sf.rewriteContents(new File("src/test/resources/arrays.md"));
    System.out.println(contents.get(0));
  }

  public ArrayList<String> readFromFile(File file) {

    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    ArrayList<String> array = new ArrayList<String>();
    while (sc.hasNextLine()) {
      array.add(sc.nextLine());
    }
    return array;
  }
}