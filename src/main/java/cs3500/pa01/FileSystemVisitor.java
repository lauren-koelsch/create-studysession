package cs3500.pa01;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Represents a file system visitor object that can visit different files in a file system
 */
public class FileSystemVisitor extends SimpleFileVisitor<Path> {

  private ArrayList<SummarizedFile> list = new ArrayList<SummarizedFile>();

  private ArrayList<File> fileList = new ArrayList<File>();

  public ArrayList<SummarizedFile> getList() {
    return list;
  }

  public ArrayList<File> getFileList() {
    return fileList;
  }

  /**
   * called everytime the file system walker encounters a file
   *
   * @param file a reference to the file
   * @param attr the file's basic attributes
   * @return directive on how to process current item's siblings and children.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {

    File f = file.toFile();
    System.out.println(f.getName());
    if (f.getName().endsWith(".md")) {
      fileList.add(file.toFile());
      ArrayList<String> contentList = new ArrayList<String>();
      String filename = file.getFileName().toString();
      long modified = attr.creationTime().toMillis();
      long created = attr.lastModifiedTime().toMillis();
      SummarizedFile fileSummary = new SummarizedFile(filename, modified, created, contentList);
      try {
        fileSummary.rewriteContents(f);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      list.add(fileSummary);
    }
    return CONTINUE;
  }


  /**
   * What to do after processing all items in a directory
   *
   * @param dir  a reference to the directory
   * @param exec {@code null} if the iteration of the directory completes without
   *             an error; otherwise the I/O exception that caused the iteration
   *             of the directory to complete prematurely
   * @return in all cases, continue processing the sibling and child items of current item
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exec) {
    System.out.format("Finishing Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * What to do at the beginning of processing a directory
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes*
   * @return a directive to continue processing siblings and children of current item.
   * @throws IOException if there is an error
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir,
                                           BasicFileAttributes attrs) throws IOException {
    System.out.format("Starting Directory: %s%n", dir);
    return CONTINUE;
  }

  /**
   * calls the different sort methods based on the given ordering flag
   *
   * @param flag            the ordering flag to order the files by
   * @param summarizedFiles the array list of SummarizedFile to be sorted
   * @return an array list of SummarizedFile organized into the correct order based on the flag
   */
  public ArrayList<SummarizedFile> getSortedList(String flag,
                                                 ArrayList<SummarizedFile> summarizedFiles) {
    if (flag.compareTo("filename") == 0) {
      return SortByFilename.sortByFilename(summarizedFiles);
    } else if (flag.compareTo("created") == 0) {
      return SortByDateCreated.sortByCreated(summarizedFiles);
    } else if (flag.compareTo("modified") == 0) {
      return SortByLastModified.sortByModified(summarizedFiles);
    } else {
      throw new IllegalArgumentException("Invalid ordering flag");
    }
  }

  /**
   * Collects the questions from the given file and turns them into an array list of questions
   *
   * @return makeQuestions called on stringList
   * @throws IOException throws exception if an error occurs
   */

  public ArrayList<String> readFileList() throws IOException {
    ArrayList<String> collection = new ArrayList<String>();
    for (File f : fileList) {
      FileReader fr = new FileReader(f);
      BufferedReader br = new BufferedReader(fr);
      String c = "";
      StringBuilder builder = new StringBuilder();
      while ((c = br.readLine()) != null) {
        if (!c.equals("")) {
          collection.add(c);
        }
      }
    }
    return collection;
  }

  /**
   * Reformats the given array list of strings into a file that is easier to read and interpret
   *
   * @return and array list of strings
   * @throws IOException throws an exception if the given file doesn't exist
   */
  public ArrayList<String> reformat() throws IOException {
    ArrayList<String> stringList = new ArrayList<>();
    ArrayList<String> content = readFileList();
    for (int i = 0; i < content.size(); i++) {
      String line = content.get(i);
      int startIndex = line.indexOf("[[");
      int endIndex = line.indexOf("]]");
      boolean isQuestion = line.indexOf(":::") >= 0;
      if (startIndex >= 0 && endIndex >= 0 && isQuestion) {
        stringList.add(line.substring(startIndex + 2, endIndex));
      } else if (startIndex <= 0 && endIndex >= 0 && isQuestion) {
        String previousLine = content.get(i - 1);
        int tempStartIndex = previousLine.indexOf("[[");
        String newLinePrev = previousLine.substring(tempStartIndex + 2, previousLine.length());
        stringList.add(newLinePrev + line.substring(0, endIndex));
      } else if (startIndex >= 0 && endIndex <= 0 && isQuestion) {
        String nextLine = content.get(i + 1);
        int tempEndIndex = nextLine.indexOf("]]");
        String newLineNext = nextLine.substring(0, tempEndIndex);
        stringList.add(line.substring(startIndex + 2, line.length()) + newLineNext);
      } else if (startIndex <= 0 && endIndex <= 0 && isQuestion) {
        String previousLine = content.get(i - 1);
        int tempStartIndex = previousLine.indexOf("[[");
        String newLinePrev = previousLine.substring(tempStartIndex + 2, previousLine.length());
        String nextLine = content.get(i + 1);
        int tempEndIndex = nextLine.indexOf("]]");
        String newLineNext = nextLine.substring(0, tempEndIndex);
        stringList.add(newLinePrev + line.substring(0, line.length()) + newLineNext);
      }
    }
    return stringList;
  }
}
