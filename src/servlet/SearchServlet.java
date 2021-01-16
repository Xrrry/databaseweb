package servlet;

import bean.*;
import dao.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int type = Integer.parseInt(request.getParameter("type"));
        System.out.println(type);
        if(type==1) {
            try {
                searchStudent(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(type==2) {
            try {
                searchGrade(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(type==3) {
            try {
                searchTeacher(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(type==4) {
            try {
                searchSubject(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(type==5) {
            try {
                searchChoose(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(type==6) {
            try {
                searchScore(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private String transform(String n, HttpServletRequest request) throws UnsupportedEncodingException {
        String a = request.getParameter(n);
        byte[] source = a.getBytes("ISO8859-1");
        return new String(source, "UTF-8");
    }
    private void searchStudent(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        System.out.println(info);
        String path = request.getContextPath();
        StudentDao sdao = new StudentDao();
        ArrayList<Students> stus = sdao.searchStudent(info);
        request.getSession().setAttribute("sstu",stus);
        response.sendRedirect(path +"/view/Sstudent.jsp");
    }
    private void searchGrade(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        String path = request.getContextPath();
        GradeDao gdao = new GradeDao();
        ArrayList<Grades> gras = gdao.searchGrade(info);
        request.getSession().setAttribute("sgra",gras);
        response.sendRedirect(path +"/view/Sgrade.jsp");
    }
    private void searchTeacher(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        String path = request.getContextPath();
        TeacherDao tdao = new TeacherDao();
        ArrayList<Teachers> stea = tdao.searchTeacher(info);
        request.getSession().setAttribute("stea",stea);
        response.sendRedirect(path +"/view/Steacher.jsp");
    }
    private void searchSubject(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        String path = request.getContextPath();
        SubjectDao sdao = new SubjectDao();
        ArrayList<Subjects> ssub = sdao.searchSubject(info);
        request.getSession().setAttribute("ssub",ssub);
        String from = transform("from",request);
        if("admin".equals(from)) {
            response.sendRedirect(path +"/view/Ssubject.jsp");
        }
        else if ("student".equals(from)){
            response.sendRedirect(path +"/view/PSsubject.jsp");
        }
    }
    private void searchChoose(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        String path = request.getContextPath();
        ChooseDao cdao = new ChooseDao();
        ArrayList<Choose> scho = cdao.searchChoose(info);
        request.getSession().setAttribute("scho",scho);
        response.sendRedirect(path +"/view/Schoose.jsp");
    }
    private void searchScore(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        String info = transform("info",request);
        String path = request.getContextPath();
        ScoreDao sdao = new ScoreDao();
        ArrayList<Scores> ssco = sdao.searchScore(info);
        request.getSession().setAttribute("ssco",ssco);
        response.sendRedirect(path +"/view/Sscore.jsp");
    }
}
