package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModelImplTest {

  ArrayList<Question> questionList;
  Question exQuestionHard;
  Question exQuestionEasy;
  ModelImpl testModel;
  ArrayList<String> stringList;
  String stringQuestion1;
  String stringQuestion2;

  @BeforeEach
  public void before() {
    questionList = new ArrayList<>();
    exQuestionEasy = new Question("Am I going to get an A on this pa?", "Yes!",
        Difficulty.EASY);
    exQuestionHard = new Question("Am I so awesome at coding?", "Duh!",
        Difficulty.HARD);
    questionList.add(exQuestionEasy);
    questionList.add(exQuestionHard);

    testModel = new ModelImpl(questionList);

    stringList = testModel.questionsToString();
    stringQuestion1 = stringList.get(0);
    stringQuestion2 = stringList.get(1);
  }

  @Test
  public void testQuestionToString() {

    assertEquals("Am I going to get an A on this pa?:::Yes![EASY]", stringQuestion1);
    assertEquals("Am I so awesome at coding?:::Duh![HARD]", stringQuestion2);
  }

  @Test
  public void testGetAndUpdateQuestionsAnswered() {
    testModel.updateQuestionsAnswered();

    assertEquals(1, testModel.getQuestionsAnswered());
  }

  @Test
  public void testGetAndUpdateTotalHard() {
    testModel.updateTotalHard();

    assertEquals(1, testModel.getTotalHard());
  }

  @Test
  public void testGetAndUpdateTotalEasy() {
    testModel.updateTotalEasy();

    assertEquals(1, testModel.getTotalEasy());
  }

  @Test
  public void testGetAndUpdateEasyToHard() {
    testModel.updateToHard(0);
    testModel.updateToHard(1);

    assertEquals(1, testModel.getEasyToHard());
    assertEquals(1, testModel.getEasyToHard());
  }

  @Test
  public void testGetAndUpdateHardToEasy() {
    testModel.updateToEasy(0);
    testModel.updateToEasy(1);

    assertEquals(1, testModel.getHardToEasy());
    assertEquals(1, testModel.getHardToEasy());
  }
}