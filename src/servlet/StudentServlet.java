package servlet;

import bean.Grades;
import bean.Students;
import dao.GradeDao;
import dao.StudentDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        if("delete".equals(method)) {
            deleteStudent(request,response);
        }
        else if("toUpdate".equals(method)) {
            toUpdateStudent(request,response);
        }
        else if("update".equals(method)) {
            updateStudent(request,response);
        }
        else if("insert".equals(method)){
            insertStudent(request,response);
        }
        else if("change".equals(method)){
            changePassword(request,response);
        }
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String path = request.getContextPath();
        StudentDao sdao = new StudentDao();
        if(sdao.deleteStudent(id)){
            response.sendRedirect(path + "/view/students.jsp?page=1");
        }
        else{
            request.getSession().setAttribute("message","删除失败,存在外键");
            response.sendRedirect(path + "/view/error.jsp");
        }
    }
    private void toUpdateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String path = request.getContextPath();
        StudentDao sdao = new StudentDao();
        Students s = sdao.getStudent(id);
        request.getSession().setAttribute("student",s);
        response.sendRedirect(path + "/view/Ustudent.jsp");
    }
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        Students s = new Students();
        StudentDao sdao = new StudentDao();
        GradeDao gdao = new GradeDao();
        Students students = (Students) session.getAttribute("student");
        String StudentNO = String.valueOf(students.getStudentNo());
        s.setUserName(transform("name",request));
        s.setEmail(request.getParameter("email"));
        s.setPhone(request.getParameter("phone"));
        s.setIdCardNo(request.getParameter("idno"));
        s.setAddress(transform("address",request));
        String gradename = transform("gradename",request);
        s.setGradeName(gradename);
        s.setGradeNo(Long.parseLong(gdao.getGradeNO(gradename)));
        String gender = transform("gender",request);
        if(gender.equals("男")){
            s.setGender(0);
        }
        else{
            s.setGender(1);
        }
        if(sdao.updateStudent(StudentNO,s)){
            response.sendRedirect(path +"/view/students.jsp?page=1");
        }
        else{
            request.getSession().setAttribute("message","更新失败");
            response.sendRedirect(path + "/view/error.jsp");
        }
    }
    private String transform(String n, HttpServletRequest request) throws UnsupportedEncodingException {
        String a = request.getParameter(n);
        byte[] source = a.getBytes("ISO8859-1");
        return new String(source, "UTF-8");
    }
    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Students s = new Students();
        StudentDao sdao = new StudentDao();
        GradeDao gdao = new GradeDao();
        String path = request.getContextPath();
        s.setUserName(transform("name",request));
        s.setEmail(request.getParameter("email"));
        s.setPhone(request.getParameter("phone"));
        s.setIdCardNo(request.getParameter("idno"));
        s.setAddress(transform("address",request));
        String gradename = transform("gradename",request);
        s.setGradeName(gradename);
        s.setGradeNo(Long.parseLong(gdao.getGradeNO(gradename)));
        String gender = transform("gender",request);
        if(gender.equals("男")){
            s.setGender(0);
        }
        else{
            s.setGender(1);
        }
        if(sdao.insertStudent(s)) {
            response.sendRedirect(path + "/view/students.jsp?page=1");
        }
        else {
            request.getSession().setAttribute("message","添加失败");
            response.sendRedirect(path + "/view/error.jsp");
        }
    }
    private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        StudentDao sdao = new StudentDao();
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");
        String type = request.getParameter("type");
        if(p1.equals(p2)){
            if(sdao.changePassword(session.getAttribute("account").toString(),p1)) {
                if("admin".equals(type)){
                    response.sendRedirect(path + "/view/students.jsp?page=1&flag=1");
                }else if("teacher".equals(type)){
                    response.sendRedirect(path + "/view/Tstudents.jsp?flag=1");
                }else if("student".equals(type)){
                    response.sendRedirect(path + "/view/Psubjects.jsp?flag=1");
                }
            }
        }
        else {
            response.sendRedirect(path + "/view/password.jsp?flag=1&type=" + type);
        }
    }
}
