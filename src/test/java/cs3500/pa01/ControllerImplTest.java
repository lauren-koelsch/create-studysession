package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class ControllerImplTest {

  @Test
  public void testRunMarkHard() throws IOException {
    // Define our input we want to test
    String string = "examples/testQuestionSet.sr 1 answer hard";

    // StringReader implements Readable
    Readable input = new StringReader(string);

    // StringBuilder implements Appendable
    Appendable output = new StringBuilder();

    ArrayList<Question> questionList = new ArrayList<>();

    ModelImpl testModel = new ModelImpl(questionList);

    ViewImpl testView = new ViewImpl(output);

    // pass them into the controller 🥳
    Controller controller = new ControllerImpl(input, testView, testModel);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.run();

    // check that the StringBuilder collected the correct output
    assertEquals("Welcome!\n"
        + "Choose an SR Question Bank File: \n"
        + "How many questions would you like to study? \n"
        + "QUESTION: What is the capital of Massachusetts?\n"
        + " [answer]  [exit] \n"
        + "ANSWER: The capital is Boston.\n"
        + "  [easy]  [hard]  [exit] \n"
        + "Total questions answered: 1\n"
        + "Total changed from easy to hard: 1\n"
        + "Total changed from hard to easy: 0\n"
        + "Total number of hard questions in the question bank: 1\n"
        + "Total number of easy questions in the question bank: 0\n", output.toString());
  }

  @Test
  public void testRunMarkEasy() throws IOException {
    // Define our input we want to test
    String string = "examples/testQuestionSet.sr 1 answer easy";

    // StringReader implements Readable
    Readable input = new StringReader(string);

    // StringBuilder implements Appendable
    Appendable output = new StringBuilder();

    ArrayList<Question> questionList = new ArrayList<>();

    ModelImpl testModel = new ModelImpl(questionList);

    ViewImpl testView = new ViewImpl(output);

    // pass them into the controller 🥳
    Controller controller = new ControllerImpl(input, testView, testModel);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // run the workflow
    controller.run();

    // check that the StringBuilder collected the correct output
    assertEquals("Welcome!\n"
        + "Choose an SR Question Bank File: \n"
        + "How many questions would you like to study? \n"
        + "QUESTION: What is the capital of Massachusetts?\n"
        + " [answer]  [exit] \n"
        + "ANSWER: The capital is Boston.\n"
        + "  [easy]  [hard]  [exit] \n"
        + "Total questions answered: 1\n"
        + "Total changed from easy to hard: 0\n"
        + "Total changed from hard to easy: 1\n"
        + "Total number of hard questions in the question bank: 0\n"
        + "Total number of easy questions in the question bank: 1\n", output.toString());
  }

  @Test
  public void testRunException() throws IOException {
    // Define our input we want to test
    String string = "examples/file.sr 1 answer easy";

    // StringReader implements Readable
    Readable input = new StringReader(string);

    // StringBuilder implements Appendable
    Appendable output = new StringBuilder();

    ArrayList<Question> questionList = new ArrayList<>();

    ModelImpl testModel = new ModelImpl(questionList);

    ViewImpl testView = new ViewImpl(output);

    // pass them into the controller 🥳
    Controller controller = new ControllerImpl(input, testView, testModel);

    // check that the StringBuilder is empty to start
    assertEquals("", output.toString());

    // check that the StringBuilder collected the correct output
    assertThrows(
        IllegalArgumentException.class, () -> controller.run());
  }
}