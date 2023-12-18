package cs3500.pa01;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds and keeps track of all the study set data the program should keep track of
 */
public class ModelImpl implements Model {

  private ArrayList<Question> studySetQuestions;

  private int questionsAnswered;

  private int easyToHard;

  private int hardToEasy;

  private int totalHard;

  private int totalEasy;

  public int numQuestions;

  /**
   * The model holds the question bank in its constructor
   *
   * @param studySetQuestions represents the questions of the study set
   */
  public ModelImpl(ArrayList<Question> studySetQuestions) {
    this.studySetQuestions = studySetQuestions;
    questionsAnswered = 0;
    easyToHard = 0;
    hardToEasy = 0;
    totalHard = 0;
    totalEasy = 0;
    numQuestions = studySetQuestions.size();
  }

  public int getQuestionsAnswered() {
    return questionsAnswered;
  }

  public int getEasyToHard() {
    return easyToHard;
  }

  public int getHardToEasy() {
    return hardToEasy;
  }

  public int getTotalHard() {
    return totalHard;
  }

  public int getTotalEasy() {
    return totalEasy;
  }

  public void addStudySetQuestions(ArrayList<Question> allQuestions) {
    studySetQuestions = allQuestions;
  }

  public void setNumQuestions(int givenNum) {
    this.numQuestions = givenNum;
  }

  /**
   * Makes a question bank containing the inputted amount of questions
   *
   * @param allQuestions the list of all the questions on the given file
   */
  public void randomizeQuestions(ArrayList<Question> allQuestions) {
    ArrayList<Question> newQuestionList = new ArrayList<>();

    int n = allQuestions.size();

    Random rand = new Random();

    for (int i = 0; i < n; i++) {
      int index = rand.nextInt(allQuestions.size());

      newQuestionList.add(allQuestions.get(index));
      allQuestions.remove(allQuestions.get(index));
    }
    studySetQuestions = newQuestionList;
  }

  public Question getQuestion(int i) {
    return studySetQuestions.get(i);
  }

  /**
   * Updates a question when its difficulty label is changed from easy to hard and updates the
   * count as well
   *
   * @param i the number of the question in the question bank
   */
  public void updateToHard(int i) {
    if (studySetQuestions.get(i).getLabel() == Difficulty.EASY) {
      easyToHard += 1;
    }
    studySetQuestions.get(i).updateLabel(Difficulty.HARD);
  }

  /**
   * Updates a question when its difficulty label is changed from hard to easy and updates the
   * count as well
   *
   * @param i the number of the question in the question bank
   */
  public void updateToEasy(int i) {
    if (studySetQuestions.get(i).getLabel() == Difficulty.HARD) {
      hardToEasy += 1;
    }
    studySetQuestions.get(i).updateLabel(Difficulty.EASY);
  }

  /**
   * Counts the total number of hard questions in the question bank and updates the field
   */
  public void updateTotalHard() {
    int counter = 0;

    for (Question question : studySetQuestions) {
      if (question.getLabel() == Difficulty.HARD) {
        counter += 1;
      }
    }
    totalHard = counter;
  }

  /**
   * Counts the total number of easy questions in the question bank and updates the field
   */
  public void updateTotalEasy() {
    int counter = 0;

    for (Question question : studySetQuestions) {
      if (question.getLabel() == Difficulty.EASY) {
        counter += 1;
      }
    }
    totalEasy = counter;
  }

  public void updateQuestionsAnswered() {
    questionsAnswered += 1;
  }

  /**
   * Makes all the questions in an array list of questions into strings, so they are easier to
   * write to a file
   *
   * @return an array list of strings each representing a question in string form
   */
  public ArrayList<String> questionsToString() {
    ArrayList<String> stringQuestionList = new ArrayList<>();
    for (Question question : studySetQuestions) {
      String questionAsString = question.getQuestion()
          + ":::" + question.getAnswer()
          + "[" + question.getLabel().toString() + "]";
      stringQuestionList.add(questionAsString);
    }
    return stringQuestionList;
  }
}
