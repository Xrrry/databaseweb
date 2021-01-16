<%@ page import="bean.Students" %>
<%@ page import="dao.GradeDao" %>
<%@ page import="bean.Grades" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css"/>
    <link rel="stylesheet" type="text/css" href="../css/signin.css"/>
    <script>
        var flag = '<%=request.getParameter("flag")%>';
        if (flag == '1') {
            alert("两次密码不相同");
        }
    </script>
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
                    <a href=""><%
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
        <div>
            <form method="post" action="<%=request.getContextPath()%>/StudentServlet?method=change&type=<%=request.getParameter("type")%>">
                <%
                    Students s = (Students) session.getAttribute("student");
                %>
                <h2 class="form-signin-heading"
                    style="max-width: 400px;margin: auto;padding-left: 12px;padding-top: 50px;padding-bottom: 20px;">
                    修改密码
                </h2>

                <div class="input-group form-signin" style="max-width: 400px;">
                    <span class="input-group-addon">密码</span>
                    <input name="p1" type="password" class="form-control" placeholder="密码" required><br>
                </div>

                <div class="input-group form-signin" style="max-width: 400px;">
                    <span class="input-group-addon">确认密码</span>
                    <input name="p2" type="password" class="form-control" placeholder="确认密码" required><br>
                </div>

                <input style="margin-top:15px;max-width: 370px;" id="btn-submit" type="submit"
                       class="btn btn-big btn-block form-signin" value="修改">
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/dropdown.js"></script>
<script type="text/javascript" src="../js/tooltip.js"></script>
<script type="text/javascript" src="../js/popover.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>
