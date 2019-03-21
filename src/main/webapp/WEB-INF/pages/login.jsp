<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <%--<link rel="shortcut icon" href="/favicon.ico">--%>
    <link href="${ctx}/public/support/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/public/support/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/public/support/css/animate.css" rel="stylesheet">
    <link href="${ctx}/public/support/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>
<body class="gray-bg" onload="DocForm.SignatureControl.ShowSignature('ER789');" onunload="DocForm.SignatureControl.DeleteSignature();">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">H+</h1>
        </div>
        <h3>欢迎使用 H+</h3>
        <form class="m-t" role="form" action="${ctx}/login" method="post" id="DocForm">
            <OBJECT id="SignatureControl"
                    classid="clsid:D85C89BE-263C-472D-9B6B-5264CD85B36E"
                    codebase="iHTMLSignature.cab#version=5.0.2.6"
                    width=0
                    height=0
                    hspace=0
                    vspace=0
            >
                <param name="ServiceUrl" value="http://218.85.65.43:8089">
            </OBJECT>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" required="" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" required="" name="password">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            <p class="text-muted text-center">
                <a href="login.html#">
                    <small>忘记密码了？</small>
                </a> | <a href="register.html">注册一个新账号</a>
            </p>
            <p class="text-muted text-center" style="color: red;">
                ${msg}
            </p>
            <input type=button value=" 盖章 " onclick="DoSignature();">
        </form>
    </div>
</div>
<!-- 全局js -->
<script src="${ctx}/public/support/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/public/support/js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript">
    // SignatureControl.Position(260,180);
    // SignatureControl.RunHandWrite();
    // SignatureControl.RunSignature(true);
    // console.log(SignatureControl.WebGetKeySN());
    // console.log(SignatureControl.GetSignatureInfo());

    function DoSignature() {
        DocForm.SignatureControl.FieldsList="XYBH=协议编号;BMJH=协议编号;JF=甲方签章;YF=乙方签章;HZNR=合作内容;QLZR=权利责任;CPMC=产品名称;DGSL=订购数量;DGRQ=订购日期";
        DocForm.SignatureControl.Position(260,180);
        DocForm.SignatureControl.RunSignature(true);
    }
</script>
</body>
</html>

