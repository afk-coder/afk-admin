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
                            <label class="col-sm-3 control-label">功能名称：</label>
                            <div class="col-sm-8">
                                <input type="hidden" name="id" value="${permission.id}" />
                                <input type="hidden" name="parentId" value="${permission.parentId}" />
                                <input id="name" name="name" class="form-control" type="text" value="${permission.name}">
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 菜单名称</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限代码：</label>
                            <div class="col-sm-8">
                                <input id="code" name="code" class="form-control" type="text" value="${permission.code}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">功能地址：</label>
                            <div class="col-sm-8">
                                <input id="url" name="url" class="form-control" type="text" value="${permission.url}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否菜单：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" id="isMenu" name="isMenu">
                                    <option value="0" <c:if test="${permission.isMenu == '0'}">selected</c:if> >否</option>
                                    <option value="1" <c:if test="${permission.isMenu == '1'}">selected</c:if>>是</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排序：</label>
                            <div class="col-sm-8">
                                <input id="sorting" name="sorting" class="form-control" type="text" value="${permission.sorting}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button class="btn btn-primary" type="submit">提交</button>
                                <button class="btn btn-default" type="button" onclick="parent.layerClose();">取消</button>
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
                code: "required",
                url: "required"
            },
            messages: {
                name: icon + "请输入功能名称",
                code: icon + "请输入权限代码",
                url: icon + "请输入功能地址"
            },
            submitHandler: function() {
                var lid = layer.msg('正在处理中，请稍后', {icon: 16, shade: 0.4, time: false});
                $.ajax({
                    type: 'post',
                    url: '${ctx}/auth/permission/update',
                    data: $('#myform').serialize(),
                    dataType: "json",
                    success: function (data) {
                        layer.close(lid);
                        if (data.success) {
                            parent.layer.msg(data.message, {icon: 1});
                            parent.layerClose();
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
