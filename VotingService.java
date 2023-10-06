import java.util.*;

public class VotingService {
  private Question question; //To store questions
  private Map<String, Integer> answerCounts; //To track answer counts

  public VotingService(Question question) {
    this.question = question;
    this.answerCounts = new HashMap<>();

    //Initialize answerCounts with candidate answers and set count to 0
    for (String candidateAnswer : question.getCandidateAnswers()) {
      answerCounts.put(candidateAnswer, 0);
    }
  }

  //Method to accept a submission from a student(user)
  public void acceptSubmission(Student student, String answer) {
    //If single-choice: reset previous answer
    if (question.getQuestionType().equals("single-choice")) {
      answerCounts.put(student.getAnswer(), 0);
    }
    
    answerCounts.put(answer, answerCounts.getOrDefault(answer, 0) + 1); //Update answer count
    student.submitAnswer(answer); //Record answers
  }

  //Method to get the count of a chosen answer
  public int getAnswerCount(String answer) {
    return answerCounts.getOrDefault(answer, 0);
  }

  //Method to print voting stats
  public void printStatistics() {
    for (String candidateAnswer : question.getCandidateAnswers()) {
      int count = getAnswerCount(candidateAnswer);
      if (count > 0) {
        System.out.println(candidateAnswer + ": " + count);
      }
    }
  }
}
