package cs3500.pa01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A SummarizedFile is used to represent a single markdown file. It holds the basic attributes
 * of the file as fields as well as the contents summarized to only what we want to include on the
 * final study guide (only headings and notes inside the "[[ ]]")
 */
public class SummarizedFile {

  private String filename;

  private long modified;

  private long created;

  private ArrayList<String> fileContents;

  /**
   * Represents a constructor for a SummarizedFile object
   *
   * @param filename represents the filename of the original file as a string
   *
   * @param created represents the time the original file was created
   *
   * @param modified represents the time the original file was last modified
   *
   * @param fileContents represents the contents of the original file only
   *              including the important notes (headings and notes inside the "[[ ]]")
   */
  public SummarizedFile(String filename, long created, long modified,
                        ArrayList<String> fileContents) {
    this.filename = filename;
    this.modified = modified;
    this.created = created;
    this.fileContents = fileContents;
  }

  public String getFilename() {
    return filename;
  }

  public long getModifiedTime() {
    return modified;
  }

  public long getCreatedTime() {
    return created;
  }

  public ArrayList<String> getContents() {
    return fileContents;
  }

  /**
   * Reads the content of a given file and returns the notes on the file as an
   * array list of strings only containing the notes that are inside the "[[ ]]" and
   * lines that are headings and start with a "#"
   */
  public ArrayList<String> rewriteContents(File file) throws IOException {
    ArrayList<String> contents = new ArrayList<>();

    try {
      BufferedReader bufReader = new BufferedReader(new FileReader(file));
      ArrayList<String> listOfLines = new ArrayList<>();
      String line = bufReader.readLine();
      while (line != null) {
        int startIndex = line.indexOf("[[");
        int endIndex = line.indexOf("]]");
        boolean isQuestion = line.indexOf(":::") >= 0;
        int header = line.indexOf("#");
        if (startIndex >= 0 && endIndex >= 0 && !isQuestion) {
          contents.add(line.substring(startIndex + 2, endIndex));
        } else if (startIndex <= 0 && endIndex >= 0 && !isQuestion) {
          contents.add(line.substring(0, endIndex));
        } else if (header >= 0 && !isQuestion) {
          contents.add(line);
        }
        line = bufReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    fileContents = contents;
    return contents;
  }
}
