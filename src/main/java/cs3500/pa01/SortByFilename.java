package cs3500.pa01;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to handle sorting an array list of SummarizedFile alphabetically by their filename
 */
public class SortByFilename {

  /**
   * Sorts an array list of SummarizedFile alphabetically by their filename
   *
   * @param fileList the array list of summarized files that we want to sort
   * @return the sorted array list of summarized files
   */
  public static ArrayList<SummarizedFile> sortByFilename(ArrayList<SummarizedFile> fileList) {
    fileList.sort(Comparator.comparing(SummarizedFile::getFilename));
    return fileList;
  }
}
