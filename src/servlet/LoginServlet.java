package servlet;

import bean.Students;
import bean.Teachers;
import dao.LoginDao;
import dao.StudentDao;
import dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("servlet初始化");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        int type = Integer.parseInt(request.getParameter("type"));
        session.setAttribute("account",account);
        String randStr = (String)session.getAttribute("randStr");
        response.setCharacterEncoding("UTF-8");
        LoginDao ldao = new LoginDao();
        if(!code.equals(randStr)) {
            System.out.println('4');
            response.sendRedirect(path + "/view/login.jsp?flag=1");
            return;
        }
        session.setAttribute("account",account);
        if(ldao.Validate(account,password,request,String.valueOf(type)) == 1) {
            switch (type) {
                case 0:
                    System.out.println('0');
                    session.setAttribute("name",account);
                    response.sendRedirect(path + "/view/students.jsp?page=1");
                    break;
                case 1:
                    System.out.println('1');
                    TeacherDao tdao = new TeacherDao();
                    Teachers t = tdao.getTeacher(session.getAttribute("KeyNO").toString());
                    session.setAttribute("name",t.getTeacherName());
                    session.setAttribute("GradeNO",t.getGradeNo());
                    session.setAttribute("tall",t);
                    response.sendRedirect(path + "/view/Tstudents.jsp");
                    break;
                case 2:
                    System.out.println('2');
                    StudentDao sdao = new StudentDao();
                    Students s = sdao.getStudent(session.getAttribute("KeyNO").toString());
                    session.setAttribute("name",s.getUserName());
                    session.setAttribute("StudentNO",s.getStudentNo());
                    session.setAttribute("GradeNO",s.getGradeNo());
                    session.setAttribute("sall",s);
                    response.sendRedirect(path + "/view/Psubjects.jsp");
                    break;
            }
        }
        else {
            System.out.println('3');
            response.sendRedirect(path + "/view/login.jsp?flag=2");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request,response);
    }

    public void destroy() {
        super.destroy();
    }
}
