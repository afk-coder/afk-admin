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
    <link href="${ctx}/public/support/css/animate.css" rel="stylesheet">
    <link href="${ctx}/public/support/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx}/public/support/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    查询条件
                </div>
            </div>
        </div>
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <button class="btn btn-primary btn-sm" type="button" id="add">新增</button>
                    <button class="btn btn-danger btn-sm" type="button" id="delete">删除</button>
                </div>
                <div class="ibox-content">
                    <table id="queryTable"></table>
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
<script src="${ctx}/public/common/js/admin.base.js"></script>
<!-- jsTree plugin javascript -->
<!-- Bootstrap table -->
<script src="${ctx}/public/support/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/public/support/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx}/public/support/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/public/support/js/plugins/layer/layer.min.js"></script>
<script src="${ctx}/public/support/js/jquery-date.js"></script>
<script type="text/javascript">
    var layerId;
    var idx = 1;
    var url = '${ctx}/auth/user/list';
    $(document).ready(function () {
        $('#queryTable').bootstrapTable({
            dataType: 'json',
            locale: 'zh-US',
            url: url,
            striped: true,                  //是否显示行间隔色
            method: 'post',
            cache: false,
            sidePagination: 'server',
            search: false,
            pagination: true,
            sortOrder: "asc",
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 15, 20],
            singleSelect: true,
            clickToSelect: true,
            queryParamsType: 'limit',
            contentType: "application/x-www-form-urlencoded",
            columns: [
                {checkbox: 'true', title: '选择', align: 'center'},
                {
                    field: 'id',
                    title: '序号',
                    align: 'center',
                    formatter: function (value, row, index) {
                        return idx + index + 1;
                    }
                }, {
                    field: 'name',
                    title: '登录名',
                    align: 'center'
                }, {
                    field: 'real_name',
                    title: '真实名',
                    align: 'center'
                }, {
                    field: 'phone',
                    title: '手机号码',
                    align: 'center'
                }, {
                    field: 'is_enabled',
                    title: '是否启用',
                    align: 'center',
                    formatter: function(value, row, index) {
                        if(value == 'Y') {
                            return '启用';
                        }
                        return '停用';
                    }
                }, {
                    field: 'create_time',
                    title: '创建时间',
                    align: 'center',
                    formatter: function(value, row, index) {
                        return date_format(value, 'yyyy-MM-dd HH:mm:ss');
                    }

                }, {
                    field: 'update_time',
                    title: '修改时间',
                    align: 'center',
                    formatter: function(value, row, index) {
                        return date_format(value, 'yyyy-MM-dd HH:mm:ss');
                    }
                }, {
                    field: 'id',
                    title: '操作',
                    width: 250,
                    align: 'left',
                    formatter: function (value, row, index) {
                        var rec = '<button type="button" class="btn btn-success btn-xs" onclick="update(\'' + value + '\', 0)">修改</button>&nbsp;';
                        rec += '<button type="button" class="btn btn-danger btn-xs" onclick="delete(\'' + value + '\', 0)">删除</button>&nbsp;';
                        return rec;
                    }
                }],
            queryParams: function (params) {
                idx = params.offset;
                return searchParams(params);
            },
            onLoadSuccess: function (params) {
            }
        });

        $('#search').click(function () {
            refurbish();
        });

        $('#add').click(function () {
            layer_open('用户新增', '${ctx}/auth/user/add', '600px', '500px');
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

    });

    function update(id) {
        layer_open('用户修改', '${ctx}/auth/user/add?id='+id, '600px', '500px');
    }

    function layerClose() {
        refurbish();
        layer.close(layerId);
    }

    function refurbish() {
        $('#queryTable').bootstrapTable('refresh', {url: url});
    }

</script>
</body>
</html>

