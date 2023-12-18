package cs3500.pa01;

import java.util.ArrayList;

/**
 * Makes the questions for the question bank from a given array list of strings
 */
public class QuestionBankMaker {

  /**
   * Makes the strings read from each file unto question objects
   *
   * @param questionSheet an array lis of strings of the question data on the original files
   * @return an array list of questions
   */
  public ArrayList<Question> makeQuestions(ArrayList<String> questionSheet) {
    ArrayList<Question> questionList = new ArrayList<>();

    for (String questionString : questionSheet) {
      int colonIndex = questionString.indexOf(":");
      int endIndex = questionString.length();
      int labelIndex = questionString.indexOf("[");
      char labelIndexNext = questionString.charAt(labelIndex + 1);
      String question = questionString.substring(0, colonIndex);
      String answer;
      if (labelIndex >= 0) {
        answer = questionString.substring(colonIndex + 3, labelIndex);
      } else {
        answer = questionString.substring(colonIndex + 3, endIndex);
      }
      Difficulty newLabel = Difficulty.HARD;
      if (labelIndex >= 0 && Character.toString(labelIndexNext).equals("E")) {
        newLabel = Difficulty.EASY;
      } else if (labelIndex >= 0 && Character.toString(labelIndexNext).equals("H")) {
        newLabel = Difficulty.HARD;
      }
      Question newQuestion = new Question(question, answer, newLabel);
      questionList.add(newQuestion);
    }
    return questionList;
  }
}
