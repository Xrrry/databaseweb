package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Scores;

public class ScoreDao extends BaseDao{
    public Scores getScore(String id){
        String sql = "select * from scores where ScoreID = '" + id + "'";
        Scores score = null;
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                score = new Scores();
                score.setScoreId(resultSet.getInt("ScoreId"));
                score.setStudentNo(resultSet.getInt("StudentNO"));
                score.setSubjectNo(resultSet.getInt("SubjectNo"));
                score.setScore(resultSet.getInt("Score"));
                score.setExamDate(resultSet.getDate("ExamDate"));
                return score;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return score;
    }
    public ArrayList<Scores> getScoreList(int page) throws SQLException {
        ArrayList<Scores> ret = new ArrayList<Scores>();
        String sql = "select * from scores order by ScoreID offset ((" + page + "-1)*10) rows fetch next 10 rows only;";
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Scores s = new Scores();
                s.setScoreId(resultSet.getInt("ScoreId"));
                s.setStudentNo(resultSet.getInt("StudentNO"));
                s.setSubjectNo(resultSet.getInt("SubjectNo"));
                s.setScore(resultSet.getInt("Score"));
                s.setExamDate(resultSet.getDate("ExamDate"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    s.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    s.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) resultSet.close();
        }
        return ret;
    }
    public int getScoreNum() {
        String sql = "select COUNT(*) num from scores";
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                int cnum = resultSet.getInt("num");
                int page = cnum / 10 + (cnum%10==0?0:1);
                return page;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeCon();
        }
        return 0;
    }
    public ArrayList<Scores> searchScore(String info) throws SQLException {
        ArrayList<Scores> ret = new ArrayList<Scores>();
        String value = "";
        if(isNumeric(info)){
            value = "StudentNO='" + info + "' or SubjectNO='" + info + "' or Score='" + info + "' or ExamDate like '%" + info + "%'";
        }
        else{
            value = "SubjectNO='" + info + "' or Score='" + info + "' or ExamDate like '%" + info + "%'";
        }
        String sql = "select * from scores where " + value;
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Scores s = new Scores();
                s.setScoreId(resultSet.getInt("ScoreId"));
                s.setStudentNo(resultSet.getInt("StudentNO"));
                s.setSubjectNo(resultSet.getInt("SubjectNo"));
                s.setScore(resultSet.getInt("Score"));
                s.setExamDate(resultSet.getDate("ExamDate"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    s.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    s.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) resultSet.close();
        }
        return ret;
    }
    public ArrayList<Scores> getTScoreList(String gradeno) throws SQLException {
        ArrayList<Scores> ret = new ArrayList<Scores>();
        String sql = "select * from scores,students where scores.StudentNO = students.StudentNO and students.GradeNO = '" + gradeno + "'";
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Scores s = new Scores();
                s.setScoreId(resultSet.getInt("ScoreId"));
                s.setStudentNo(resultSet.getInt("StudentNO"));
                s.setSubjectNo(resultSet.getInt("SubjectNo"));
                s.setScore(resultSet.getInt("Score"));
                s.setExamDate(resultSet.getDate("ExamDate"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    s.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    s.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) resultSet.close();
        }
        return ret;
    }
    public ArrayList<Scores> getPScoreList(String studentno) throws SQLException {
        ArrayList<Scores> ret = new ArrayList<Scores>();
        String sql = "select * from scores,students where scores.StudentNO = students.StudentNO and students.StudentNO = '" + studentno + "'";
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Scores s = new Scores();
                s.setScoreId(resultSet.getInt("ScoreId"));
                s.setStudentNo(resultSet.getInt("StudentNO"));
                s.setSubjectNo(resultSet.getInt("SubjectNo"));
                s.setScore(resultSet.getInt("Score"));
                s.setExamDate(resultSet.getDate("ExamDate"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    s.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    s.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) resultSet.close();
        }
        return ret;
    }
    public boolean deleteScore(String scoreid) {
        String sql = "delete from scores where ScoreID = '" + scoreid + "'";
        try {
            return update(sql);
        }
        finally {
            closeCon();
            return false;
        }
    }
    public boolean updateScore(String scoreid, Scores s) {
        String sql = "update scores set Score = '" + s.getScore() + "' where ScoreID = '" + scoreid + "'";
        try{
            return update(sql);
        }finally {
            closeCon();
        }
    }
}
