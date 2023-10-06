import java.util.List;

public class Question {
  private String questionType; //To store question type: single answer or multiple answer
  private List<String> candidateAnswers; //To store a list of candidate answers

  //Initialize with question type and student's(user's) answers
  public Question(String questionType, List<String> candidateAnswers) {
    this.questionType = questionType;
    this.candidateAnswers = candidateAnswers;
  }

  //Getter for question type
  public String getQuestionType() {
    return questionType;
  }

  //Getter for list of answers from student(user)
  public List<String> getCandidateAnswers() {
    return candidateAnswers;
  }
}
