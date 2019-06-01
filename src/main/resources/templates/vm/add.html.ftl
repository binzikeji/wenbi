<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head th:replace="common/common_header :: common_header(~{::title},~{},~{})">
    <title>添加首页</title>
</head>
<body style="height: 100%">
<section class="content">
    <div class="row">
        <form class="form-horizontal" id="form-admin-add" onsubmit="return false;">
            <div class="col-md-12">
                <#list table.fields as field>
                    <#if field.keyFlag??>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">${field.comment}</label>
                            <div class="col-sm-10">
                                <input id="${field.propertyName}" name="${field.propertyName}" value="" class="form-control"/>
                            </div>
                        </div>
                    </#if>
                </#list>

                <div class="form-group">
                    <label class="col-sm-2 control-label"></label>
                    <div class="col-sm-10">
                        <button class="btn btn-primary" id="submit" type="submit"><i class="fa fa-save"></i>&nbsp;保存
                        </button>
                        <button type="button" class="btn btn-danger" onclick="layer_close();"><i
                                class="fa fa-close"></i>&nbsp;关闭
                        </button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>
</body>
<div th:replace="common/common_foot :: foot"></div>
<script th:src="@{/content/plugins/jquery.validation/jquery.validate.js}"></script>
<script th:src="@{/content/plugins/jquery.validation/validate-methods.js}"></script>
<script th:src="@{/content/common/validation/common.validation.js}"></script>
<script th:inline="javascript"><#macro dian></#macro> #set($bootstrapTable = '$table')

$(function () {
    $(".select2").select2({
        placeholder: "请选择",
        width: "100%" //设置下拉框的宽度
    });
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
    $("#form-admin-add").validate({
        rules: {
            <#list table.fields as field>
                <#if field.keyFlag??>
                    ${field.propertyName}: {
                    required: true
                     },
                </#if>
            </#list>
},
    onkeyup: false,
            submitHandler: function (form) {
        $.ajax({
            url: getRootPath()+"/a/${table.entityPath}/${table.entityPath}Save",
            type: "Post",
            dataType: "json",
            data: $(form).serialize(),
            success: function (result) {
                if (result > 0) {
                    opaler();
                } else {
                    opalerNO();
                }
                //刷新父级页面
                parent.$bootstrapTable#dian()bootstrapTable('refresh'); //再刷新DT
            }
        });
    }
});
});
</script>
</html>
