<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/signin.css" rel="stylesheet">
    <script type="text/javascript">
        function refresh() {
            document.getElementById("imgValidate").src = "<%=request.getContextPath()%>/view/code.jsp?now=" + Math.random();
        }
    </script>
    <script>
        var flag = '<%=request.getParameter("flag")%>';
        if (flag === '1') {
            alert("验证码错误");
        } else if (flag === '2') {
            alert("账户名或密码错误");
        } else if (flag ==='3') {
            alert("请登录，勿使用URL访问！");
        }
    </script>
</head>
<body>
<div class="container">
    <form name="loginForm" action="<%=request.getContextPath()%>/LoginServlet" method="post" class="form-signin">
        <h2 class="form-signin-heading">请登录</h2>
        <label class="sr-only">账号</label>
        <input name="account" type="text" class="form-control" placeholder="账号" required autofocus><br>
        <label class="sr-only">密码</label>
        <input name="password" type="password" class="form-control" placeholder="密码" required><br>
        <label class="sr-only">验证码</label>
        <input name="code" type="password" class="form-control" placeholder="验证码" required>
        <img id="imgValidate" src="<%=request.getContextPath()%>/view/code.jsp" onclick="refresh();">
        <a onclick="javascript:refresh();return false;" href="#" style="color:red;">看不清，换一张</a><br><br>
        <label class="radio-inline">
            <input id="radio-2" name="type" type="radio" value="2" checked>学生
        </label>
        <label class="radio-inline">
            <input id="radio-1" name="type" type="radio" value="1">教师
        </label>
        <label class="radio-inline">
            <input id="radio-0" name="type" type="radio" value="0">管理员
        </label>
        <input class="btn btn-lg btn-primary btn-block" style="margin-top: 30px;" type="submit" value="登录">
    </form>
</div>
</body>
</html>