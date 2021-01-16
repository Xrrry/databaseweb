<%@ page import="bean.Subjects" %>
<%@ page import="dao.GradeDao" %>
<%@ page import="bean.Grades" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改课程信息</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css"/>
    <link rel="stylesheet" type="text/css" href="../css/signin.css"/>

</head>
<body style="background-color:#fff">
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
                <li>
                    <a href="<%=request.getContextPath()%>/view/password.jsp?flag=0&type=admin"><%
                        try {
                            String name = session.getAttribute("name").toString();
                            out.print(name);
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect(request.getContextPath() + "/view/login.jsp?flag=3");
                        }
                    %></a>
                </li>
                <li><a href="<%=request.getContextPath()%>/view/login.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-sm-3 col-md-2">
            <ul class="nav nav-sidebar">
                <li><a href="students.jsp?page=1">学生列表</a></li>
                <li><a href="grades.jsp?page=1">院系列表</a></li>
                <li><a href="teachers.jsp?page=1">教师列表</a></li>
                <li class="active"><a href="subjects.jsp?page=1">课程列表<span
                        class="sr-only">(current)</span></a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="choose.jsp?page=1">选课列表</a></li>
                <li><a href="scores.jsp?page=1">成绩列表</a></li>
            </ul>
        </div>
        <div>
            <form method="post" action="<%=request.getContextPath()%>/SubjectServlet?method=update">
                <%
                    Subjects s = (Subjects) session.getAttribute("subject");
                %>
                <h2 class="form-signin-heading"
                    style="max-width: 400px;margin: auto;padding-left: 12px;padding-top: 50px;padding-bottom: 20px;">
                    修改课程信息
                </h2>
                <div class="input-group form-signin" style="max-width: 400px;">
                    <span class="input-group-addon">课程名称</span>
                    <input name="name" type="text" class="form-control" value="<%=s.getSubjectName()%>" required><br>
                </div>
                <div class="input-group form-signin" style="max-width: 400px;">
                    <span class="input-group-addon">课时</span>
                    <input name="hour" type="text" class="form-control" value="<%=s.getClassHour()%>" required><br>
                </div>

                <div class="input-group form-signin" style="max-width: 400px;">
                    <span class="input-group-addon">院系</span>
                    <select name="gradename" class="form-control">
                        <%
                            GradeDao gdao = new GradeDao();
                            ArrayList<Grades> gras = gdao.getGradeListAll();
                            for (int i = 0; i < gras.size(); i++) {
                                Grades gra = (Grades) gras.get(i);
                        %>
                        <option <%
                            if (s.getGradeName().equals(gra.getGradeName())) {
                        %>
                                selected="selected"
                                <%}%>><%=gra.getGradeName()%>
                        </option>
                        <% } %>
                    </select>
                </div>

                <input style="margin-top:15px;max-width: 370px;" id="btn-submit" type="submit"
                       class="btn btn-big btn-block form-signin" value="修改">
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/dropdown.js" ></script>
<script type="text/javascript" src="../js/tooltip.js"></script>
<script type="text/javascript" src="../js/popover.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
