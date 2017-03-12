<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎来到图书借阅系统</title>
  </head>
  
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/login.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath }/resource/css/common.css" />
        
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath }/LoginTransferServlet" id="loginForm" method="post">
        <div id="loginDiv">
            
            <span class="loginFont">图书借阅系统</span><br/>
            
            <div id="usernameDiv">
                <div id="upFont">用户名:</div>
                <input type="text" name="username" id="username"/>
            </div>
            
            <div id="passwordDiv">
                <div id="upFont" style="padding-left: 5px; width: 55px;">密  码:</div>
                <input type="password" name="password" />
            </div>
            
            <div id="modifyPass">
                <span style="color: blueviolet; font-size: 14px;">忘记密码？</span>
                
            </div>
            
            <div id="login">
                <input type="button" value="登录" onclick="submitLogin()" id="" class="loginButton"/>
            </div>
            
        </div>
        </form>
    </body>
    
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/Ajax.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/jquery.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resource/js/login_js.js" ></script>
</html>
