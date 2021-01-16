package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Choose;

public class ChooseDao extends BaseDao{
    public Choose getChoose(int id) throws SQLException {
        String sql = "select * from chooses where ChooseID = '" + id + "'";
        Choose choose = null;
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                choose = new Choose();
                choose.setStudentNo(resultSet.getInt("StudentNO"));
                choose.setSubjectNo(resultSet.getInt("SubjectNo"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    choose.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    choose.setSubjectName(rs2.getString("SubjectName"));
                }
                return choose;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
            if(resultSet!=null) resultSet.close();
        }
        return choose;
    }
    public ArrayList<Choose> getChooseList(int page) throws SQLException {
        ArrayList<Choose> ret = new ArrayList<Choose>();
        String sql = "select * from chooses order by ChooseID offset ((" + page + "-1)*10) rows fetch next 10 rows only;";
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Choose c = new Choose();
                c.setStudentNo(resultSet.getInt("StudentNO"));
                c.setSubjectNo(resultSet.getInt("SubjectNo"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    c.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    c.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(c);
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
    public int getChooseNum() {
        String sql = "select COUNT(*) num from chooses";
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
    public ArrayList<Choose> searchChoose(String info) throws SQLException {
        ArrayList<Choose> ret = new ArrayList<Choose>();
        String value = "StudentNO='" + info + "' or SubjectNO='" + info + "'";
        String sql = "select * from chooses where " + value;
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Choose c = new Choose();
                c.setStudentNo(resultSet.getInt("StudentNO"));
                c.setSubjectNo(resultSet.getInt("SubjectNo"));
                String sql1 = "select UserName from students where StudentNO = '" + resultSet.getInt("StudentNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    c.setStudentName(rs1.getString("UserName"));
                }
                String sql2 = "select SubjectName from subjects where SubjectNO = '" + resultSet.getInt("SubjectNO") + "'";
                ResultSet rs2 = query(sql2);
                if(rs2.next()) {
                    c.setSubjectName(rs2.getString("SubjectName"));
                }
                ret.add(c);
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
    public boolean updateChoose(Choose c) {
        String value = "('" + c.getStudentNo() + "','" + c.getSubjectNo() + "')";
        String sql = "insert into chooses (StudentNO,SubjectNO) values " +value;
        try {
            return update(sql);
        }finally {
            closeCon();
        }
    }
    public boolean isChoosed(Long studentno,Long subjectno) {
        String sql = "select * from chooses where StudentNO = '" + studentno + "' and SubjectNO='" + subjectno + "'";
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeCon();
        }
        return false;
    }
    public boolean deleteChoose(Choose c) {
        String sql = "delete from chooses where StudentNO = '" + c.getStudentNo() + "' and SubjectNO = '" + c.getSubjectNo() + "'";
        try {
            return update(sql);
        }finally {
            closeCon();
        }
    }
}
