<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="dao.StudentDao" %>
<%@ page import="bean.Students" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>学生列表</title>
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/dashboard.css"/>
</head>
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">确认</h4>
            </div>
            <div class="modal-body">
                确认删除?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-danger" onclick="go()">删除</button>
            </div>
        </div>
    </div>
</div>
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
                <li class="active"><a href="students.jsp?page=1">学生列表<span
                        class="sr-only">(current)</span></a></li>
                <li><a href="grades.jsp?page=1">院系列表</a></li>
                <li><a href="teachers.jsp?page=1">教师列表</a></li>
                <li><a href="subjects.jsp?page=1">课程列表</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="choose.jsp?page=1">选课列表</a></li>
                <li><a href="scores.jsp?page=1">成绩列表</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <div class="table-responsive" style="max-width: 1200px;margin: auto">
        <table align="center" class="table table-striped">
            <caption>所有学生信息</caption>
            <tr>
                <th>学生编号</th>
                <th>姓名</th>
                <th>学号</th>
                <th>院系</th>
                <th>性别</th>
                <th>邮箱</th>
                <th>电话</th>
                <th>地址</th>
                <th>操作</th>
            </tr>
            <%  ArrayList<Students> stus = (ArrayList<Students>)session.getAttribute("sstu");
                for (int i = 0; i < stus.size(); i++) {
                    Students stu = (Students) stus.get(i);
            %>
            <tr style="height: 52px;">
                <td style="padding-top: 17px;"><%=stu.getStudentNo() %>
                </td>
                <td style="padding-top: 17px;"><%=stu.getUserName() %>
                </td>
                <td style="padding-top: 17px;"><%=stu.getIdCardNo() %>
                </td>
                <td style="padding-top: 17px;"><%=stu.getGradeName() %>
                </td>
                <td style="padding-top: 17px;"><% if (stu.getGender() == 0) {
                    out.print('男');
                } else {
                    out.print('女');
                }%>
                </td>
                <td style="padding-top: 17px;"><%=stu.getEmail() %>
                </td>
                <td style="padding-top: 17px;"><%=stu.getPhone() %>
                </td>
                <td style="padding-top: 17px;"><%=stu.getAddress() %>
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/StudentServlet?method=toUpdate&id=<%=stu.getStudentNo()%>" >
                        <button type="button"
                                class="btn btn-primary">修改
                        </button>
                    </a>
                    <button id="todelete" type="button" class="btn btn-danger" data-toggle="modal"
                            data-target="#myModal" onclick="aaa(<%=stu.getStudentNo()%>)" value="<%=stu.getStudentNo()%>">
                        删除
                    </button>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
<script type="text/javascript" src="../js/dropdown.js" ></script>
<script type="text/javascript" src="../js/tooltip.js"></script>
<script type="text/javascript" src="../js/popover.js"></script>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    var id = 0;
    function aaa(e) {
        console.log(e);
        id = e;
    }
    function go() {
        window.location.href = '<%=request.getContextPath()%>/StudentServlet?method=delete&id=' + id;
    }
</script>
</body>
</html>
