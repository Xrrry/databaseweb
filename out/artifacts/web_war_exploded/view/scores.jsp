<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.ScoreDao" %>
<%@ page import="bean.Scores" %>
<html>
<head>
    <title>成绩列表</title>
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
                <li><a href="<%=request.getContextPath()%>/view/login.jsp">退出</a></li>            </ul>
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
                <li><a href="subjects.jsp?page=1">课程列表</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="choose.jsp?page=1">选课列表</a></li>
                <li class="active"><a href="scores.jsp?page=1">成绩列表<span
                        class="sr-only">(current)</span></a></li>
            </ul>

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
                <th>操作</th>
            </tr>
            <%  ScoreDao sdao = new ScoreDao();
                int pa = Integer.parseInt(request.getParameter("page"));
                int pagenum = sdao.getScoreNum();
                ArrayList<Scores> scos = sdao.getScoreList(pa);
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
                <td>
                    <a href="<%=request.getContextPath()%>/ScoreServlet?method=toUpdate&id=<%=sco.getScoreId()%>" >
                        <button type="button"
                                class="btn btn-primary">修改
                        </button>
                    </a>
                    <button id="todelete" type="button" class="btn btn-danger" data-toggle="modal"
                            data-target="#myModal" onclick="aaa(<%=sco.getScoreId()%>)" value="<%=sco.getScoreId()%>">
                        删除
                    </button>
                </td>
            </tr>
            <% } %>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <%if(pa!=1) {%>
                <li>
                    <a href="<%=request.getContextPath()%>/view/scores.jsp?page=<%=pa-1%>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <%}else{%>
                <li class="disabled">
                    <a href="" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <%}
                    for(int i=1;i<=pagenum;i++){
                        if (pa==i){
                %>
                <li class="active"><a href="<%=request.getContextPath()%>/view/scores.jsp?page=<%=i%>"><%=i%></a></li>
                <%}else{%>
                <li><a href="<%=request.getContextPath()%>/view/scores.jsp?page=<%=i%>"><%=i%></a></li>
                <%}}%>
                <%if(pa!=pagenum){%>
                <li>
                    <a href="<%=request.getContextPath()%>/view/scores.jsp?page=<%=pa+1%>" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <%}else{%>
                <li class="disabled">
                    <a href="" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <%}%>
            </ul>
        </nav>
    </div>
</div>
<script src="../js/jquery-3.4.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
    var id = 0;
    function aaa(e) {
        console.log(e);
        id = e;
    }
    function go() {
        window.location.href = '<%=request.getContextPath()%>/ScoreServlet?method=delete&id=' + id;
    }
</script>
</body>
</html>
