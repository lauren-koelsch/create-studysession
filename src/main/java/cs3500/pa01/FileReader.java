package cs3500.pa01;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A FileReader reads a file and returns it as an array list of strings
 */
public class FileReader {

  /**
   * Reads a given file and returns it as an ArrayList of Strings
   *
   * @param path the path of the file to be read
   * @return an array list of strings where each element represents a line of the file
   * @throws IOException throws an exception if the given file doesn't exist
   */
  public ArrayList<String> readFile(String path) throws IOException {
    ArrayList<String> fileArr = new ArrayList<>();
    try {
      java.io.FileReader fr = new java.io.FileReader(path);
      BufferedReader br = new BufferedReader(fr);
      String c;
      while ((c = br.readLine()) != null) {
        if (!c.equals("")) {
          fileArr.add(c);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("The file at the given path doesn't exist: " + e);
    }
    return fileArr;
  }
}