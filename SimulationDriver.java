import java.util.*;

public class SimulationDriver {
  //Main method
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //Ask user if they would like to answer a multiple answer question or single answer question
    System.out.println("Choose the question type (Enter 1 or 2):");
    System.out.println("\t1. Multiple Answer"); //1 for multiple answer
    System.out.println("\t2. Single Answer"); //2 for single answer

    int choice = scanner.nextInt();
    scanner.nextLine();

    //If user enters 1: go to multiple answer
    if (choice == 1) {
      multipleAnswer(scanner);
    //If user enters 2: go to single answer
    } else if (choice == 2) {
      singleAnswer(scanner);
    //If user enters anything other than 1 or 2: tell user to try again and enter 1 or 2
    } else {
      System.out.println("Invalid choice. Please choose 1 for Multiple Choice or 2 for Single Choice.");
    }

    scanner.close();
  }

  //Multiple answer method
  private static void multipleAnswer(Scanner scanner) {
    //Possible candidate answers
    List<String> candidateAnswers = Arrays.asList("Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Pink", "Other");
    Question question = new Question("multiple-choice", candidateAnswers);
    VotingService votingService = new VotingService(question);

    //Ask user how many students will be answering the question
    System.out.print("\nEnter the number of students: ");
    int numberOfStudents = scanner.nextInt();
    scanner.nextLine();
  
    Set<String> usedStudentIds = new HashSet<>();

    //For each student, ask user to enter the student IDs
    for (int i = 0; i < numberOfStudents; i++) {
      String studentId;
      while (true) {
        System.out.print("\n・・・・・・・・・・・・・・・・・・・・・・・・・・・・\n\nEnter a unique ID for Student " + (i + 1) + ": ");
        studentId = scanner.nextLine();

        //Check if student ID has been previously entered, if so: ask user to enter a different ID
        if (!usedStudentIds.contains(studentId)) {
          usedStudentIds.add(studentId);
          break;
        } else {
          System.out.println("ID '" + studentId + "' is already used. Please enter a unique ID.");
        }
      }

      Student student = new Student(studentId);

      //Ask student(user) for their favorite colors
      System.out.println("\nQuestion: What are your favorite colors?");
      System.out.println("\n\tOptions: " + String.join(", ", candidateAnswers));

      //While true: ask student(user) for student #'s answers
      while (true) {
        System.out.print("\nEnter the answers for Student " + (i + 1) + " (comma-separated): ");
        String[] answerTokens = scanner.nextLine().split(", ");

        boolean validAnswers = true;
        //Check if student's(user's) answers are valid, if not: ask for a valid answer
        for (String answer : answerTokens) {
          if (!candidateAnswers.contains(answer)) {
            validAnswers = false;
            System.out.println("Invalid answer choice '" + answer + "'. Please choose a valid option.");
            break;
          }
        }

        //Accept all submissions if all of them are valid
        if (validAnswers) {
          for (String answer : answerTokens) {
            votingService.acceptSubmission(student, answer);
          }
          break;
        }
      }
    }
  
    //Printing submission stats for multiple answer
    System.out.println("\n‿︵‿︵‿︵‿︵‿୨✿୧‿︵‿︵‿︵‿︵‿\n\nSubmission Statistics:");

    //Count up all the submissions
    for (String candidateAnswer : candidateAnswers) {
      int count = votingService.getAnswerCount(candidateAnswer);
      if (count > 0) {
        System.out.println(candidateAnswer + ": " + count);
      }
    }
    System.out.println("\n・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
  }

  private static void singleAnswer(Scanner scanner) {
      List<String> candidateAnswers = Arrays.asList("\t1. Yes, I have a single favorite color", "\t2. No, I don't have a single favorite color");
      Question question = new Question("single-choice", candidateAnswers);
      VotingService votingService = new VotingService(question);
  
      int choice1Count = 0;
      int choice2Count = 0;
  
      System.out.print("\nEnter the number of students: ");
      int numberOfStudents = scanner.nextInt();
      scanner.nextLine();
  
      Set<String> usedStudentIds = new HashSet<>();
  
      for (int i = 0; i < numberOfStudents; i++) {
        String studentId;
        while (true) {
          System.out.print("\n・・・・・・・・・・・・・・・・・・・・・・・・・・・・\n\nEnter a unique ID for Student " + (i + 1) + ": ");
          studentId = scanner.nextLine();

          if (!usedStudentIds.contains(studentId)) {
            usedStudentIds.add(studentId);
            break;
          } else {
            System.out.println("ID '" + studentId + "' is already used. Please enter a unique ID.");
          }
        }

        Student student = new Student(studentId);

        System.out.println("\nQuestion: Do you have a single favorite color?\n");
        for (String answer : candidateAnswers) {
          System.out.println(answer);
        }

        int choice;
        while (true) {
          System.out.print("\nEnter your choice (1 or 2): ");
          choice = scanner.nextInt();
          scanner.nextLine();

          if (choice == 1 || choice == 2) {
            break; //Break if the answer choice is either 1 or 2
          } else {
            System.out.println("Invalid choice. Please choose 1 or 2.");
          }
        }

        // Update the counts based on the selected choice
        if (choice == 1) {
          choice1Count++;
        } else if (choice == 2) {
          choice2Count++;
        }

        String selectedAnswer = candidateAnswers.get(choice - 1);
        votingService.acceptSubmission(student, selectedAnswer);
      }
  
      //Prints submission stats for single answer
      System.out.println("\n‿︵‿︵‿︵‿︵‿୨✿୧‿︵‿︵‿︵‿︵‿\n\nSubmission Statistics:");
      System.out.println("1: " + choice1Count);
      System.out.println("2: " + choice2Count);
      System.out.println("\n・・・・・・・・・・・・・・・・・・・・・・・・・・・・");
  }
}