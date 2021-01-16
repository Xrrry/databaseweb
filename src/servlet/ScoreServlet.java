package servlet;

import bean.Scores;
import dao.ScoreDao;
import dao.TeacherDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ScoreServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String method = request.getParameter("method");
        if("delete".equals(method)) {
            deleteStudent(request,response);
        }
        else if("toUpdate".equals(method)) {
            toUpdateScore(request,response);
        }
        else if("update".equals(method)) {
            updateScore(request,response);
        }
    }
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String path = request.getContextPath();
        ScoreDao sdao = new ScoreDao();
        sdao.deleteScore(id);
        response.sendRedirect(path + "/view/scores.jsp?page=1");
    }
    private void toUpdateScore(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String id = request.getParameter("id");
        String path = request.getContextPath();
        ScoreDao sdao = new ScoreDao();
        Scores s = sdao.getScore(id);
        request.getSession().setAttribute("score",s);
        response.sendRedirect(path + "/view/Uscore.jsp");
    }
    private void updateScore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getContextPath();
        HttpSession session = request.getSession();
        Scores s = new Scores();
        ScoreDao sdao = new ScoreDao();
        Scores scores = (Scores) session.getAttribute("score");
        String ScoreId = String.valueOf(scores.getScoreId());
        s.setScore(Integer.parseInt(transform("score",request)));
        if(sdao.updateScore(ScoreId,s)){
            response.sendRedirect(path +"/view/scores.jsp?page=1");
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
}
