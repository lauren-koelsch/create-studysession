package cs3500.pa01;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to handle sorting an array list of SummarizedFile by the time they were last modified
 */
public class SortByLastModified {

  /**
   * Sorts an array list of SummarizedFile by the time they were last modified time
   * from oldest to newest
   *
   * @param summarizedFiles the array list of summarized files that we want to sort
   * @return the sorted array list of summarized files
   */

  public static ArrayList<SummarizedFile> sortByModified(
      ArrayList<SummarizedFile> summarizedFiles) {
    summarizedFiles.sort(Comparator.comparingLong(SummarizedFile::getModifiedTime));
    return summarizedFiles;
  }
}
