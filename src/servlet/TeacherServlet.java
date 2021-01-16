package servlet;

import bean.Teachers;
import dao.GradeDao;
import dao.TeacherDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TeacherServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        if("delete".equals(method)) {
            deleteTeacher(request,response);
        }
        else if("toUpdate".equals(method)) {
            toUpdateTeacher(request,response);
        }
        else if("update".equals(method)) {
            updateTeacher(request,response);
        }
        else if("insert".equals(method)) {
            insertTeacher(request,response);
        }
    }
    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String path = request.getContextPath();
        TeacherDao tdao = new TeacherDao();
        tdao.deleteTeacher(id);
        response.sendRedirect(path + "/view/teachers.jsp?page=1");
    }
    private void toUpdateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String path = request.getContextPath();
        TeacherDao tdao = new TeacherDao();
        Teachers t = tdao.getTeacher(id);
        request.getSession().setAttribute("teacher",t);
        response.sendRedirect(path + "/view/Uteacher.jsp");
    }
    private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        Teachers t = new Teachers();
        TeacherDao tdao = new TeacherDao();
        GradeDao gdao = new GradeDao();
        Teachers teachers = (Teachers) session.getAttribute("teacher");
        String TeacherNO = String.valueOf(teachers.getTeacherNo());
        t.setTeacherName(transform("name",request));
        String gradename = transform("gradename",request);
        t.setGradeName(gradename);
        t.setGradeNo(Long.parseLong(gdao.getGradeNO(gradename)));
        if(tdao.updateTeacher(TeacherNO,t)){
            response.sendRedirect(path +"/view/teachers.jsp?page=1");
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
    private void insertTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Teachers t = new Teachers();
        TeacherDao tdao = new TeacherDao();
        GradeDao gdao = new GradeDao();
        String path = request.getContextPath();
        t.setTeacherName(transform("name",request));
        String gradename = transform("gradename",request);
        t.setGradeName(gradename);
        t.setGradeNo(Long.parseLong(gdao.getGradeNO(gradename)));
        if(tdao.insertTeacher(t)) {
            response.sendRedirect(path + "/view/teachers.jsp?page=1");
        }
        else{
            request.getSession().setAttribute("message","添加失败");
            response.sendRedirect(path + "/view/error.jsp");
        }
    }
}
