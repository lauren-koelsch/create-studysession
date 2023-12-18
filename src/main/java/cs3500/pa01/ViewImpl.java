package cs3500.pa01;

import java.io.IOException;

/**
 * View class that displays output to the user
 */
public class ViewImpl implements View {

  private final Appendable out;

  public ViewImpl(Appendable out) {
    this.out = out;
  }

  /**
   * Prints input given by the controller class
   *
   * @param input input received from controller class to be printed
   */
  public void print(String input) {
    try {
      out.append(input + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not print message: " + e);
    }
  }

  /**
   * Prints a question given by the controller class
   *
   * @param input input received from controller class to be printed
   */
  public void printQuestion(String input) {
    try {
      out.append("QUESTION: " + input + "\n");
      out.append(" [answer]  [exit] \n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not print message: " + e);
    }
  }

  /**
   * Prints an answer given by the controller class
   *
   * @param input input received from controller class to be printed
   */
  public void printAnswer(String input) {
    try {
      out.append("ANSWER: " + input + "\n");
      out.append("  [easy]  [hard]  [exit] \n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not print message: " + e);
    }
  }

  /**
   * Allows the player to exit the game if they type 0
   */
  public void exit() {
    System.exit(0);
  }

  /**
   * Prints the statistics of the study session
   *
   * @param model the model which houses all of the stats for the session
   */
  public void printStats(ModelImpl model) {
    try {
      out.append("Total questions answered: " + model.getQuestionsAnswered() + "\n");
      out.append("Total changed from easy to hard: " + model.getEasyToHard() + "\n");
      out.append("Total changed from hard to easy: " + model.getHardToEasy() + "\n");
      out.append("Total number of hard questions in the question bank: "
          + model.getTotalHard() + "\n");
      out.append("Total number of easy questions in the question bank: "
          + model.getTotalEasy() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not print message: " + e);
    }
  }
}
