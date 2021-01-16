package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Students;
import bean.Teachers;

public class TeacherDao extends BaseDao{
    public Teachers getTeacher(String id){
        String sql = "select * from teachers where TeacherNO = '" + id + "'";
        Teachers teachers = null;
        ResultSet resultSet = query(sql);
        try {
            if(resultSet.next()){
                teachers = new Teachers();
                teachers.setTeacherNo(resultSet.getInt("TeacherNO"));
                teachers.setTeacherName(resultSet.getString("TeacherName"));
                teachers.setGradeNo(resultSet.getInt("GradeNO"));
                String sql1 = "select GradeName from grades where GradeNO = '" + resultSet.getInt("GradeNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    teachers.setGradeName(rs1.getString("GradeName"));
                }
                return teachers;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            closeCon();
        }
        return teachers;
    }
    public ArrayList<Teachers> getTeacherList(int page) throws SQLException {
        ArrayList<Teachers> ret = new ArrayList<Teachers>();
        String sql = "select * from teachers order by TeacherNO offset ((" + page + "-1)*10) rows fetch next 10 rows only;";
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Teachers t = new Teachers();
                t.setTeacherNo(resultSet.getInt("TeacherNO"));
                t.setTeacherName(resultSet.getString("TeacherName"));
                t.setGradeNo(resultSet.getInt("GradeNO"));
                String sql1 = "select GradeName from grades where GradeNO = '" + resultSet.getInt("GradeNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    t.setGradeName(rs1.getString("GradeName"));
                }
                ret.add(t);
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
    public int getTeacherNum() {
        String sql = "select COUNT(*) num from teachers";
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
    public ArrayList<Teachers> searchTeacher(String info) throws SQLException {
        ArrayList<Teachers> ret = new ArrayList<Teachers>();
        String value = "";
        if(isNumeric(info)) {
            value = "TeacherNO='" + info + "' or TeacherName like '%" + info + "%' or GradeNO like '%" + info + "%'";
        }
        else{
            value = "TeacherName like '%" + info + "%' or GradeNO like '%" + info + "%'";
        }
        String sql = "select * from teachers where "+ value;
        ResultSet resultSet = query(sql);
        try {
            while(resultSet.next()){
                Teachers t = new Teachers();
                t.setTeacherNo(resultSet.getInt("TeacherNO"));
                t.setTeacherName(resultSet.getString("TeacherName"));
                t.setGradeNo(resultSet.getInt("GradeNO"));
                String sql1 = "select GradeName from grades where GradeNO = '" + resultSet.getInt("GradeNO") + "'";
                ResultSet rs1 = query(sql1);
                if(rs1.next()) {
                    t.setGradeName(rs1.getString("GradeName"));
                }
                ret.add(t);
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
    public boolean deleteTeacher(String teacherno) {
        String sql = "delete from teachers where TeacherNO = '" + teacherno + "'";
        try {
            return update(sql);
        }
        finally {
            closeCon();
        }
    }
    public boolean updateTeacher(String teacherno, Teachers s) {
        String sql = "update teachers set TeacherName = '" + s.getTeacherName() + "',GradeNO='" +
                s.getGradeNo() + "' where TeacherNO='" +teacherno + "'";
        try{
            return update(sql);
        }finally {
            closeCon();
        }
    }
    public boolean insertTeacher(Teachers t) {
        String value = "('" + t.getTeacherName() + "','" + t.getGradeNo() + "')";
        String sql = "insert into teachers (TeacherName,GradeNO) values " + value;
        try {
            return update(sql);
        }finally {
            closeCon();
        }
    }
}
