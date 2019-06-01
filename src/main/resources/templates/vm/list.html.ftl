<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head th:replace="common/common_header :: common_header(~{::title},~{},~{})">
    <title>首页</title>
</head>
<body>
<section class="content">

    <div class="row">
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="col-md-12">
            <!-- BEGIN SAMPLE TABLE PORTLET-->
            <div class="box-body" style="padding-bottom:0px;">
                <div class="panel panel-default">
                    <div class="panel-heading">高级检索</div>
                    <div class="panel-body">
                        <form class="form-inline" method="post" id="searchForm" >
                            <div class="form-group">
                                <label for="name">角色名</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="用户名"/>
                                <input id="hiddenText" type="text" style="display:none" /><!-- 隐藏的  控制回车提交表单-->
                            </div>
                            <button shiro:hasPermission="sys:user:search" type="button" id="btn_query" class="btn btn-success"><i class="fa fa-search"></i>&nbsp;查询</button>
                            <button shiro:hasPermission="sys:user:search" type="button" id="btn_reset" class="btn btn-primary"><i class="fa fa-undo"></i>&nbsp;重置</button>
                        </form>
                    </div>
                </div>

                <div id="toolbar" class="btn-group">
                    <button shiro:hasPermission="sys:user:add" id="btn_add" type="button" class="btn btn-default">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                    </button>
                    <button shiro:hasPermission="sys:user:delete" id="btn_delete" type="button" class="btn btn-default" disabled="disabled">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>批量删除
                    </button>
                </div>
                <div class="table-scrollable">
                    <table class="table-striped table-hover table-bordered"  id="empUserList">
                    </table>
                </div>
            </div>
            <!-- END SAMPLE TABLE PORTLET-->

        </div>
    </div>

</section>
</body>
<script th:inline="javascript">
    var permissionButton = [];//权限按钮
</script>
<script th:inline="javascript" shiro:hasPermission="sys:user:update">
    permissionButton.push('<a class="btn btn-icon-only" data-type="edit" href="javascript:void(0)" title="修改"><i class="glyphicon glyphicon-edit"></i></a>');
</script>
<script th:inline="javascript" shiro:hasPermission="sys:user:delete">
    permissionButton.push('<a class="btn btn-icon-only" data-type="delete" href="javascript:void(0)" title="删除"><i class="glyphicon glyphicon-remove"></i></a>');
</script>

<div th:replace="common/common_foot :: foot"></div>
<script th:src="@{/content/plugins/bootstrap-table/bootstrap-table.js}"></script>
<script th:src="@{/content/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js}"></script>
<script th:src="@{/content/common/common.server.js}"></script>
<script th:inline="javascript">#macro(dian).#end #set($bootstrapTable = '$table') #macro(tanh)!#end #set($query = '$query') #set($reset = '$reset') #set($remove = '$remove') #set($add = '$add')

var $bootstrapTable = $('#empUserList'),
        $query = $('#btn_query'),
        $reset = $('#btn_reset'),
        $remove = $('#btn_delete'),
        $add = $('#btn_add'),
        selections = [];//权限按钮
$(function () {
    $(".select2").select2();
    $bootstrapTable#dian()bootstrapTable({
        url: getRootPath()+'/a/${table.entityPath}/get${entity}PageList',
        queryParams : queryParams,
        classes: "table-striped table-hover table-bordered",
        buttonsAlign: "right",  //按钮位置
        toolbar: "#toolbar",// 指定工具栏
        uniqueId: "id", // 每一行的唯一标识
        columns: [
            {checkbox : true},
        <#list table.fields as field>
            <#if $field.keyFlag??>##生成普通字段
                {title: $field.comment, field: $field.propertyName, align: 'center', valign: 'middle', sortable: true},
            <#/if>
        <#/list>
       {
        title: '操作', field: 'id', align: 'center', valign: 'middle',
                formatter: function (value, row, index) {
            return permissionButton.join('');
        },
        events: {
            'click [data-type="edit"]': function (e, value, row) {
                layer_show("修改", getRootPath() + "/a/${table.entityPath}/${table.entityPath}Update?id=" + value, "800", "600");
            },
            'click [data-type="delete"]': function (e, value, row) {
                $.fn.modalConfirm('确定要删除所选数据？', function () {
                    $.ajax({
                        url: getRootPath() + '/a/${table.entityPath}/${table.entityPath}Delete',
                        type: "Post",
                        data: {id: value},
                        dataType: "json",
                        success: function (result) {
                            if (result > 0) {
                                $.fn.modalMsg("操作成功", "success");
                            } else {
                                $.fn.modalMsg("操作失败", "error");
                            }
                            $remove#dian()prop('disabled', true);
                            $bootstrapTable#dian()bootstrapTable('refresh');   //从新加载数据
                        }
                    });
                });
            }
        }
    }
],
    onLoadSuccess: function(){  //加载成功时执行
        //layer.msg("加载成功");
    },
    onLoadError: function(){  //加载失败时执行
        layer.msg("加载数据失败", {time : 1500, icon : 2});
    }
});
    // sometimes footer render error.
    setTimeout(function () {
        $bootstrapTable#dian()bootstrapTable('resetView', {
            height:getHeight()
        });
    }, 300);
    $(window).resize(function () {
        $bootstrapTable#dian()bootstrapTable('resetView', {
            height:getHeight()
        });
    });
    //点击行中的checkbox  和全选的checkbox事件
    $bootstrapTable#dian()on('check.bs.table uncheck.bs.table ' +
            'check-all.bs.table uncheck-all.bs.table', function () {
        $remove#dian()prop('disabled', #tanh()$bootstrapTable#dian()bootstrapTable('getSelections').length);
        selections = getIdSelections();
    });
    $query#dian()click(function () {
        $bootstrapTable#dian()bootstrapTable('refresh');   //从新加载数据
    });
    $reset#dian()click(function () {
        $(".form-inline .form-control").val("");
        $bootstrapTable#dian()bootstrapTable('refresh');   //从新加载数据
    });
    $add#dian()click(function () {
        layer_show("添加", getRootPath()+"/a/${table.entityPath}/${table.entityPath}Add","800","600");
    });
    $remove#dian()click(function () {
        if (selections.length < 1) {
            $.fn.modalAlert('请选择一条或多条数据进行删除！','error');
        } else {
            //询问框
            $.fn.modalConfirm ('确定要删除所选数据？', function () {
                $.ajax({
                    url: getRootPath()+'/a/${table.entityPath}/${table.entityPath}BatchDelete',
                    type: "Post",
                    data:{item:JSON.stringify(selections)},
                    dataType : "json",
                    success:function(result){
                        if(result > 0){
                            $.fn.modalMsg("操作成功","success");
                        }else {
                            $.fn.modalMsg("操作失败","error");
                        }
                        $remove#dian()prop('disabled', true);
                        $bootstrapTable#dian()bootstrapTable('refresh');   //从新加载数据
                    }
                });
            });
        }
    });

    /* input 获取焦点 才能触发 刷新事件*/
    $("input").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            if ($query#dian()length > 0){
                $bootstrapTable#dian()bootstrapTable('refresh');   //从新加载数据
            }
        }
    });
});


/**
 * 返回所有的checked选中的值
 */
function getIdSelections() {
    return $.map($bootstrapTable#dian()bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

/**
 * 查询条件与分页数据
 * @param params
 * @returns {{limit: (*|number), offset, sort, order}}
 */
function queryParams(params) {
    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        limit: params.limit,   //页面大小
        offset: params.offset, //页码
        sort: params.sort,  //排序列名
        order:params.order //排序方式
        //search:params.search,   //搜索框参数
    };
    getSearchFormData($("#searchForm"),temp);
    return temp;
}
</script>
</html>