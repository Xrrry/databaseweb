<%@ page language="java" contentType="text/html; charset=UTF-8"  isErrorPage="true"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>错误提示</title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="<%=request.getContextPath()%>/css/cover.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">

            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand"></h3>
                </div>
            </div>

            <div class="inner cover">
                <p class="lead" style="font-size: 40px;"><%=session.getAttribute("message")%></p>
                <p class="lead">
                    <a href="javascript:history.go(-1);" class="btn btn-lg btn-default">返回上一页</a>
                </p>
            </div>

        </div>

    </div>

</div>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>