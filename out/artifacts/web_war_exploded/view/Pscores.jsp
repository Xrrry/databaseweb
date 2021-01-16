<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Subjects" %>
<%@ page import="dao.SubjectDao" %>
<%@ page import="dao.ScoreDao" %>
<%@ page import="bean.Scores" %>
<%@ page import="bean.Students" %>
<html>
<head>
    <title>课程列表</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css"/>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">学生管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <%
                    Students sall = (Students)session.getAttribute("sall");
                %>
                <li><a href=""><%=sall.getGradeName()%></a></li>
                <li><a href=""><%=sall.getIdCardNo()%></a></li>
                <li><a href="<%=request.getContextPath()%>/view/password.jsp?flag=0&type=student"><%=session.getAttribute("name").toString()%></a></li>
                <li><a href="<%=request.getContextPath()%>/view/login.jsp">退出</a></li>            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-sm-3 col-md-2">
            <ul class="nav nav-sidebar">
                <li><a href="Psubjects.jsp">选课</a></li>
                <li class="active"><a href="Pscores.jsp">我的成绩<span
                        class="sr-only">(current)</span></a></li>
            </ul>
        </div>
    </div>
</div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="table-responsive" style="max-width: 1200px;margin: auto">
        <table align="center" class="table table-striped">
            <caption>所有成绩信息</caption>
            <tr>
                <th>姓名</th>
                <th>课程</th>
                <th>成绩</th>
                <th>考试时间</th>
            </tr>
            <%  ScoreDao sdao = new ScoreDao();
                ArrayList<Scores> scos = sdao.getPScoreList(session.getAttribute("StudentNO").toString());
                for (int i = 0; i < scos.size(); i++) {
                    Scores sco = (Scores) scos.get(i);
            %>
            <tr style="height: 52px;">
                <td style="padding-top: 17px;"><%=sco.getStudentName() %>
                </td>
                <td style="padding-top: 17px;"><%=sco.getSubjectName() %>
                </td>
                <td style="padding-top: 17px;"><%=sco.getScore() %>
                </td>
                <td style="padding-top: 17px;"><%=sco.getExamDate() %>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
