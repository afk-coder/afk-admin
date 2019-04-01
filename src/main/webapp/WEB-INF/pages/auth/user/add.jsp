<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 基本表单</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/public/support/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/public/support/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/public/support/css/animate.css" rel="stylesheet">
    <link href="${ctx}/public/support/css/style.css?v=4.1.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-md-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="myform">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录名：</label>
                            <div class="col-sm-8">
                                <input type="hidden" name="id" value="${user.id}" />
                                <input id="name" name="name" class="form-control" type="text" value="${user.name}">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 登录用户名</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">真实姓名：</label>
                            <div class="col-sm-8">
                                <input id="realName" name="realName" class="form-control" type="text" value="${user.realName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">电话号码：</label>
                            <div class="col-sm-8">
                                <input id="phone" name="phone" class="form-control" type="text" value="${user.phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <button class="btn btn-default" type="button" onclick="parent.layer_close()">取消</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="${ctx}/public/support/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/public/support/js/bootstrap.min.js?v=3.3.6"></script>
<!-- 自定义js -->
<script src="${ctx}/public/support/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script src="${ctx}/public/support/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/public/support/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx}/public/support/js/plugins/layer/layer.min.js"></script>
<script src="${ctx}/public/common/js/form.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#myform").validate({
            rules: {
                name: "required",
                realName: "required"
            },
            messages: {
                name: icon + "请输入登录用户名",
                realName: icon + "请输入真实姓名"
            },
            submitHandler: function() {
                var lid = layer.msg('正在处理中，请稍后', {icon: 16, shade: 0.4, time: false});
                $.ajax({
                    type: 'post',
                    url: '${ctx}/auth/user/add',
                    data: $('#myform').serialize(),
                    dataType: "json",
                    success: function (data) {
                        layer.close(lid);
                        if (data.success) {
                            parent.layer.msg(data.message, {icon: 1});
                            parent.layer_close();
                        } else {
                            layer.msg(data.message, {icon: 5});
                        }
                    }
                });
                return false;
            }
        });
    });
</script>
</body>

</html>
