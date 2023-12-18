package cs3500.pa01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Makes the study guide when the main is given three command line args
 */

public class ControllerStudyGuide implements Controller {

  public String startPath;

  public String flag;

  public String outputPath;

  /**
   * Constructor that contains all necessary information to make a study guide
   *
   * @param startPath  the path for the directory we want to search
   * @param flag       the ordering flag for how to order the files
   * @param outputPath the output path the study guide should be made at
   */
  public ControllerStudyGuide(String startPath, String flag, String outputPath) {
    this.startPath = startPath;
    this.flag = flag;
    this.outputPath = outputPath;
  }

  /**
   * makes a study guide of the desired files in a given directory, orders them correctly, and
   * makes a new file containing only the important contents
   *
   * @throws IOException throws an exception if the given file does not exist
   */
  public void run() throws IOException {

    String startP = (startPath);
    String outputP = (outputPath);

    FileSystemVisitor visitor = new FileSystemVisitor();

    FileWriter fileWriterMd = new FileWriter();
    FileWriter fileWriterSr = new FileWriter();

    try {
      Files.walkFileTree(Path.of(startP), visitor);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    ArrayList<SummarizedFile> studyGuideFiles = visitor.getList();

    ArrayList<SummarizedFile> sortedFiles = visitor.getSortedList(flag, studyGuideFiles);

    ArrayList<String> stringQuestions = visitor.reformat();

    fileWriterMd.writeMdFile(sortedFiles, outputP  + ".md");
    fileWriterSr.writeSrFile(stringQuestions, outputP  + ".sr");
  }
}
