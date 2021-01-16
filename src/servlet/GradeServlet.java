package servlet;

import bean.Grades;
import dao.GradeDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class GradeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        if("delete".equals(method)) {
            deleteGrade(request,response);
        }
        else if("toUpdate".equals(method)) {
            toUpdateGrade(request,response);
        }
        else if("update".equals(method)) {
            updateGrade(request,response);
        }
        else if("insert".equals(method)) {
            insertGrade(request,response);
        }
    }
    private void deleteGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String path = request.getContextPath();
        GradeDao gdao = new GradeDao();
        gdao.deleteGrade(id);
        response.sendRedirect(path + "/view/grades.jsp?page=1");
    }
    private void toUpdateGrade(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String path = request.getContextPath();
        GradeDao gdao = new GradeDao();
        Grades g = gdao.getGrade(id);
        request.getSession().setAttribute("grade",g);
        response.sendRedirect(path + "/view/Ugrade.jsp");
    }
    private void updateGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        Grades g = new Grades();
        GradeDao gdao = new GradeDao();
        Grades grades = (Grades) session.getAttribute("grade");
        String GradeNO = String.valueOf(grades.getGradeNo());
        g.setGradeName(transform("name",request));
        if(gdao.updateGrade(GradeNO,g)){
            response.sendRedirect(path +"/view/grades.jsp?page=1");
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
    private void insertGrade(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Grades g = new Grades();
        GradeDao gdao = new GradeDao();
        String path = request.getContextPath();
        g.setGradeName(transform("name",request));
        if(gdao.insertGrade(g)) {
            response.sendRedirect(path + "/view/grades.jsp?page=1");
        }
        else{
            request.getSession().setAttribute("message","添加失败");
            response.sendRedirect(path + "/view/error.jsp");
        }
    }
}
