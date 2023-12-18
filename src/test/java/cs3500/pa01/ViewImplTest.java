package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViewImplTest {

  ViewImpl tester;
  StringBuilder sb;

  @BeforeEach
  public void before() {
    sb = new StringBuilder();
    tester = new ViewImpl(sb);
  }

  @Test
  public void testPrint() {
    tester.print("hi");

    assertEquals(sb.toString(), "hi\n");
  }

  @Test
  public void testPrintQuestion() {
    tester.printQuestion("hi");

    assertEquals(sb.toString(), "QUESTION: hi\n [answer]  [exit] \n");
  }

  @Test
  public void testPrintAnswer() {
    tester.printAnswer("hi");

    assertEquals(sb.toString(), "ANSWER: hi\n  [easy]  [hard]  [exit] \n");
  }

  @Test
  public void testPrintStats() {
    ArrayList<Question> mtList = new ArrayList<>();
    ModelImpl testModel = new ModelImpl(mtList);
    tester.printStats(testModel);

    assertEquals(sb.toString(),
        "Total questions answered: 0\n"
            + "Total changed from easy to hard: 0\n"
            + "Total changed from hard to easy: 0\n"
            + "Total number of hard questions in the question bank: 0\n"
            + "Total number of easy questions in the question bank: 0\n");
  }

  @Test
  public void testPrintThrowsException() {
    ViewImpl view = new ViewImpl(MockAppendable.appendable);
    ArrayList<Question> mtList = new ArrayList<>();
    ModelImpl testModel = new ModelImpl(mtList);
    tester.printStats(testModel);

    assertThrows(
        IllegalStateException.class, () -> view.print("Hello"));

    assertThrows(
        IllegalStateException.class, () -> view.printQuestion("Hello"));

    assertThrows(
        IllegalStateException.class, () -> view.printAnswer("Hello"));

    assertThrows(
        IllegalStateException.class, () -> view.printStats(testModel));
  }
}