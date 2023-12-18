package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Tests the FileHandler class
 */
class FileWriterTest {

  /**
   * Tests the writeStudyGuide() method that reads the contents of each SummarizedFile and writes
   * it on the file at the given output path
   */
  @Test
  public void testWriteMdFile() throws IOException {

    ArrayList<SummarizedFile> summarizedFiles = new ArrayList<SummarizedFile>();

    ArrayList<String> exContent = new ArrayList<String>();
    SummarizedFile array = new SummarizedFile("alpha.md", 4, 9, exContent);
    SummarizedFile vector = new SummarizedFile("beta.md", 2, 6, exContent);

    summarizedFiles.add(array);
    summarizedFiles.add(vector);

    String outputPath = ("examples/test.md");

    FileWriter fh = new FileWriter();

    fh.writeMdFile(summarizedFiles, outputPath);

    File f = new File(outputPath);

    Scanner sc;
    try { // The file may not exist, in which case we need to handle that error (hence try-catch)
      sc = new Scanner(new FileInputStream(f));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    if (sc.hasNextLine()) {
      assertEquals("# Hello", sc.nextLine());
    }
  }

  @Test
  public void testWriteSrFile() throws IOException {

    ArrayList<String> list = new ArrayList<String>();
    list.add("Hello");
    list.add("World");

    String outputPath = ("examples/test.md");

    FileWriter fh = new FileWriter();

    fh.writeSrFile(list, outputPath);

    File f = new File(outputPath);

    Scanner sc;
    try { // The file may not exist, in which case we need to handle that error (hence try-catch)
      sc = new Scanner(new FileInputStream(f));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    if (sc.hasNextLine()) {
      assertEquals("Hello", sc.nextLine());
    }
  }
}