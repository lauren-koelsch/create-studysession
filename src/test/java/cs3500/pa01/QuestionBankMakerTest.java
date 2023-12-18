package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class QuestionBankMakerTest {

  @Test
  public void testMakeQuestions() {

    ArrayList<String> stringList = new ArrayList<>();
    String exQuestionString = ("Am I going to get an A on this pa?::: Yes!");
    String exQuestionStringEasy = ("Am I so good at coding?::: Duh![EASY]");
    String exQuestionStringHard = ("Could I be ruler of the coding universe::: Absolutely![HARD]");
    stringList.add(exQuestionString);
    stringList.add(exQuestionStringEasy);
    stringList.add(exQuestionStringHard);

    QuestionBankMaker quesMaker = new QuestionBankMaker();

    ArrayList<Question> questionList = quesMaker.makeQuestions(stringList);
    Question exQuestion1 = questionList.get(0);

    assertEquals(exQuestion1.getQuestion(), "Am I going to get an A on this pa?");
    assertEquals(exQuestion1.getAnswer(), " Yes!");
    assertEquals(exQuestion1.getLabel(), Difficulty.HARD);

    Question exQuestion2 = questionList.get(1);

    assertEquals(exQuestion2.getQuestion(), "Am I so good at coding?");
    assertEquals(exQuestion2.getAnswer(), " Duh!");
    assertEquals(exQuestion2.getLabel(), Difficulty.EASY);

    Question exQuestion3 = questionList.get(2);

    assertEquals(exQuestion3.getQuestion(), "Could I be ruler of the coding universe");
    assertEquals(exQuestion3.getAnswer(), " Absolutely!");
    assertEquals(exQuestion3.getLabel(), Difficulty.HARD);
  }
}