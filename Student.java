public class Student {
  private String studentId;
  private String answer;

  //To create a student object with a student ID
  public Student(String studentId) {
    this.studentId = studentId;
  }

  //Getter for student ID
  public String getStudentId() {
    return studentId;
  }

  //Getter for student's(user's) answers
  public String getAnswer() {
    return answer;
  }

  //Method to submit an answer
  public void submitAnswer(String answer) {
    this.answer = answer;
  }
}
