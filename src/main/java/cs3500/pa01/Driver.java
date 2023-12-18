package cs3500.pa01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Represents the driver and main class
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws IOException {

    if (args.length != 0) {

      ControllerStudyGuide sg = new ControllerStudyGuide(args[0], args[1], args[2]);
      sg.run();

    } else {

      Readable input = new InputStreamReader(System.in);
      ViewImpl view = new ViewImpl(System.out);
      ArrayList<Question> arr = new ArrayList<>();
      ModelImpl model = new ModelImpl(arr);

      ControllerImpl ci = new ControllerImpl(input, view, model);

      ci.run();
    }
  }
}