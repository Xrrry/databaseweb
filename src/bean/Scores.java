package bean;


import java.sql.Time;

public class Scores {

  private long scoreId;
  private long studentNo;
  private long subjectNo;
  private long score;
  private java.util.Date examDate;
  private String StudentName;
  private String SubjectName;


  public long getScoreId() {
    return scoreId;
  }

  public void setScoreId(long scoreId) {
    this.scoreId = scoreId;
  }


  public long getStudentNo() {
    return studentNo;
  }

  public void setStudentNo(long studentNo) {
    this.studentNo = studentNo;
  }


  public long getSubjectNo() {
    return subjectNo;
  }

  public void setSubjectNo(long subjectNo) {
    this.subjectNo = subjectNo;
  }


  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }


  public java.util.Date getExamDate() {
    return examDate;
  }

  public void setExamDate(java.util.Date examDate) {
    this.examDate = examDate;
  }

  public String getStudentName() {
    return StudentName;
  }
  public void setStudentName(String s) {
    this.StudentName = s;
  }
  public String getSubjectName() {
    return SubjectName;
  }
  public void setSubjectName(String s) {
    this.SubjectName = s;
  }
}
