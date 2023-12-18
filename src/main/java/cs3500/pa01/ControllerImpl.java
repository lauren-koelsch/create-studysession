package cs3500.pa01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Controls the actions of the program based on the given input
 */

public class ControllerImpl implements Controller {

  // Fields
  private final Readable input;

  private final ViewImpl view;

  private final ModelImpl model;

  private final Scanner scan;

  /**
   * Constructor for a class that begins the study session
   *
   * @param input the user input
   * @param view the view part of the program
   * @param model the model part of the program
   */
  public ControllerImpl(Readable input, ViewImpl view, ModelImpl model) {
    this.input = Objects.requireNonNull(input);
    this.view = view;
    this.model = model;
    this.scan = new Scanner(this.input);
  }

  /**
   * Runs the program and gives the view the outputs and the model the inputs
   */
  public void run() throws IOException {

    view.print("Welcome!" + "\n" + "Choose an SR Question Bank File: ");

    String path = "";

    if (scan.hasNext()) {
      path = scan.next();
    }

    view.print("How many questions would you like to study? ");

    int numberOfQuestions = 0;

    if (scan.hasNextInt()) {
      numberOfQuestions = scan.nextInt();
      //check hasNext, well-formed and correct (if)
    }

    if (!path.equals("")) {
      makeQuestionBank(path, numberOfQuestions);
    }

    for (int i = 0; i < numberOfQuestions; i++) {
      showQuestions(i);
    }
    model.updateTotalHard();
    model.updateTotalEasy();

    view.printStats(model);

    rewriteFile(path);
  }

  /**
   * Makes the question bank from the specified file
   *
   * @param path path of the file we want to make questions from
   */
  private void makeQuestionBank(String path, int numQuestions) {
    FileReader srFile = new FileReader();
    QuestionBankMaker questionMaker = new QuestionBankMaker();

    try {
      srFile.readFile(path);
      ArrayList<Question> questionList = questionMaker.makeQuestions(srFile.readFile(path));
      model.addStudySetQuestions(questionList);
      model.randomizeQuestions(questionList);
      model.setNumQuestions(numQuestions);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Invalid file at path: " + path);
    }
  }

  /**
   * method to show questions from the question bank and react to user input for each question
   * accordingly
   *
   * @param i the number of the question in the question bank
   */
  private void showQuestions(int i) {
    view.printQuestion(model.getQuestion(i).getQuestion());

    String response = scan.next();

    if (response.equals("answer")) {
      model.updateQuestionsAnswered();
      view.printAnswer(model.getQuestion(i).getAnswer());
    } else if (response.equals("exit")) {
      model.updateTotalHard();
      model.updateTotalEasy();
      view.printStats(model);
      view.exit();
    }

    String secondResponse = scan.next();

    if (secondResponse.equals("easy")) {
      model.updateToEasy(i);
    } else if (secondResponse.equals("hard")) {
      model.updateToHard(i);
    } else if (secondResponse.equals("exit")) {
      model.updateTotalHard();
      model.updateTotalEasy();
      view.printStats(model);
      view.exit();
    }
  }

  /**
   * Rewrites the .sr file after the study session has ended
   *
   * @param path location where the file is written at
   * @throws IOException throws an exception if it cannot write a file at the given path
   */
  private void rewriteFile(String path) {

    FileWriter writer = new FileWriter();
    try {
      writer.writeSrFile(model.questionsToString(), path);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not write file at the specified location: " + e);
    }
  }
}

