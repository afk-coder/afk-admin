<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 树形视图</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/public/support/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/public/support/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/public/support/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="${ctx}/public/support/css/animate.css" rel="stylesheet">
    <link href="${ctx}/public/support/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <button class="btn btn-primary btn-sm" type="button" id="add">添加</button>
                    <button class="btn btn-success btn-sm" type="button" id="update">修改</button>
                    <button class="btn btn-danger btn-sm" type="button" id="delete">删除</button>
                </div>
                <div class="ibox-content">
                    <div id="jstree"></div>
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
<!-- jsTree plugin javascript -->
<script src="${ctx}/public/support/js/plugins/jsTree/jstree.min.js"></script>
<script src="${ctx}/public/support/js/plugins/layer/layer.min.js"></script>
<style type="text/css">
    .jstree-open > .jstree-anchor > .fa-folder:before {
        content: "\f07c";
    }
    .jstree-default .jstree-icon.none {
        width: 0;
    }
</style>
<script type="text/javascript">
    var layerId;
    $(document).ready(function () {
        $('#jstree').jstree({
            'core': {
                'data':{
                    'url': '${ctx}/auth/permission/list',
                    'type': 'post',
                    'dataType': 'json'
                }
            }
        });

        $('#add').click(function () {
            var nodes = $('#jstree').jstree(true).get_selected(false);
            var parentId;
            if(nodes.length > 0) {
                parentId = nodes[0];
            } else {
                parentId = '';
            }
            layerId = layer.open({
                title: '功能管理新增',
                maxmin: false,
                type: 2,
                content: '${ctx}/auth/permission/add?parentId='+parentId,
                area: ['600px', '700px']
            });
        });

        $('#update').click(function () {
            if(isSelect()) {
                var nodes = $('#jstree').jstree(true).get_selected(false);
                layerId = layer.open({
                    title: '功能管理修改',
                    maxmin: false,
                    type: 2,
                    content: '${ctx}/auth/permission/update?id='+nodes[0],
                    area: ['600px', '700px']
                });
            }
        });

        $('#delete').click(function () {
            if(isSelect()) {
                var nodes = $('#jstree').jstree(true).get_selected(false);
                layer.confirm('确认删除该菜单？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    var lid = layer.msg('正在处理中，请稍后', {icon: 16, shade: 0.4, time: false});
                    $.ajax({
                        type: "POST",
                        url: "${ctx}/auth/permission/delete",
                        dataType:'json',
                        data:{
                            id: nodes[0]
                        },
                        success: function(data){
                            layer.close(lid);
                            if (data.success) {
                                $('#jstree').jstree(true).refresh();
                                layer.msg(data.message, {icon: 1});
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                        }
                    });
                }, function(){
                });
            }
        });

        //判断节点是否选择，且只能选择一个节点
        var isSelect = function () {
            var nodes = $('#jstree').jstree(true).get_selected(false);
            if(nodes.length == 0) {
                layer.msg('请选择一个节点！', {icon: 2});
                return false;
            }
            if(nodes.length > 1) {
                layer.msg('只能选择一个节点！', {icon: 2});
                return false;
            }
            return true;
        }
    });

    function layerClose() {
        layer.close(layerId);
    }
</script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->
</body>

</html>

