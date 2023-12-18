package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ControllerStudyGuideTest {


  @Test
  public void testRun() throws IOException {
    // Define our input we want to test
    String arg1 = "src/test/resources";
    String arg2 = "filename";
    String arg3 = "src/examples";

    // StringBuilder implements Appendable
    Appendable output = new StringBuilder();

    ArrayList<Question> questionList = new ArrayList<>();

    // pass them into the controller 🥳
    Controller controller = new ControllerStudyGuide(arg1, arg2, arg3);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.run();

    // check that the StringBuilder collected the correct output
    assertEquals("", output.toString());
  }
}