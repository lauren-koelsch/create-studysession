package cs3500.pa01;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class to write the file at the given output location
 */
public class FileWriter {

  /**
   * Writes the contents in each SummarizedFile on a single file it creates at the specified
   * output location
   */
  public void writeMdFile(ArrayList<SummarizedFile> summarizedFiles,
                        String outputPath) throws IOException {

    StringBuilder builder = new StringBuilder();

    for (SummarizedFile sumFile : summarizedFiles) {
      ArrayList<String> fileContents = sumFile.getContents();
      for (String line : fileContents) {
        builder.append(line + "\n");
      }
    }

    java.io.FileWriter mdFile = new java.io.FileWriter(outputPath);
    mdFile.write(builder.toString());
    mdFile.close();

  }

  /**
   * writes the collected questions onto a study set file
   *
   * @param questions the array list of questions we have made
   * @param outputPath the output path we want the questions to be written to
   * @throws IOException throws an exception if an error occurs
   */
  public void writeSrFile(ArrayList<String> questions,
                        String outputPath) throws IOException {

    StringBuilder builder = new StringBuilder();

    for (String line : questions) {
      builder.append(line + "\n");
    }

    java.io.FileWriter srFile = new java.io.FileWriter(outputPath);
    srFile.write(builder.toString());
    srFile.close();
  }
}