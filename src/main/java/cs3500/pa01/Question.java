package cs3500.pa01;

/**
 * Class to describe a question that has a question, answer, and difficulty level (easy or hard)
 */
public class Question {

  private final String question;

  private final String answer;

  private Difficulty label;

  /**
   * Represents a question in a question bank
   *
   * @param question the question to be shown to the user
   * @param answer the correct answer
   * @param label the difficulty of the question
   */
  public Question(String question, String answer, Difficulty label) {
    this.question = question;
    this.answer = answer;
    this.label = label;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  public Difficulty getLabel() {
    return label;
  }

  public void updateLabel(Difficulty newLabel) {
    label = newLabel;
  }
}
