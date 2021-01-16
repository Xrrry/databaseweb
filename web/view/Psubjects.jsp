<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Subjects" %>
<%@ page import="dao.SubjectDao" %>
<%@ page import="bean.Students" %>
<%@ page import="dao.ChooseDao" %>
<html>
<head>
    <title>课程列表</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css"/>
    <script>
        var flag = '<%=request.getParameter("flag")%>';
        if (flag == '1') {
            alert("密码修改成功");
        }
    </script>
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
            <form method="post" class="navbar-form navbar-left" style="margin-left: 580px" action="<%=request.getContextPath()%>/SearchServlet?from=student">
                <div class="input-group form-signin" style="max-width: 200px;">
                    <span class="input-group-addon">搜索对象</span>
                    <select name="type" class="form-control">
                        <option value="4">课程</option>
                    </select>
                </div>
                <input name="info" type="text" placeholder="输入要搜索的内容" class="form-control">
                <button type="submit" class="btn btn-success">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <%
                    Students sall = (Students) session.getAttribute("sall");
                %>
                <li><a href=""><%=sall.getGradeName()%>
                </a></li>
                <li><a href=""><%=sall.getIdCardNo()%>
                </a></li>
                <li><a href="<%=request.getContextPath()%>/view/password.jsp?flag=0&type=student"><%=session.getAttribute("name").toString()%>
                </a></li>
                <li><a href="<%=request.getContextPath()%>/view/login.jsp">退出</a></li>
            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-sm-3 col-md-2">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="Psubjects.jsp">选课<span
                        class="sr-only">(current)</span></a></li>
                <li><a href="Pscores.jsp">我的成绩</a></li>
            </ul>

        </div>
    </div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="table-responsive" style="max-width: 1200px;margin: auto">
        <table align="center" class="table table-striped">
            <caption>院系课程信息</caption>
            <tr>
                <th>课程编号</th>
                <th>课程名</th>
                <th>学时</th>
                <th>所属院系</th>
                <th>操作</th>
            </tr>
            <% SubjectDao sdao = new SubjectDao();
                ChooseDao choosedao = new ChooseDao();
                ArrayList<Subjects> subs = sdao.getTSubjectList(session.getAttribute("GradeNO").toString());
                Long studentno = sall.getStudentNo();
                for (int i = 0; i < subs.size(); i++) {
                    Subjects sub = (Subjects) subs.get(i);
            %>
            <tr style="height: 52px;">
                <td style="padding-top: 17px;"><%=sub.getSubjectNo() %>
                </td>
                <td style="padding-top: 17px;"><%=sub.getSubjectName() %>
                </td>
                <td style="padding-top: 17px;"><%=sub.getClassHour() %>
                </td>
                <td style="padding-top: 17px;"><%=sub.getGradeName() %>
                </td>
                <td>
                    <%
                        if(!choosedao.isChoosed(studentno,sub.getSubjectNo()))
                        {
                    %>
                    <a href="<%=request.getContextPath()%>/SubjectServlet?method=choose&id=<%=sub.getSubjectNo()%>">
                        <button type="button"
                                class="btn btn-success">选课
                        </button>
                    </a>
                    <%} else {%>
                    <a href="<%=request.getContextPath()%>/SubjectServlet?method=unchoose&id=<%=sub.getSubjectNo()%>">
                        <button type="button"
                                class="btn btn-primary">退课
                        </button>
                    </a>
                    <%}%>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
