/**
 * Created by xiaokun on 2017/4/2 16:29
 * Description: 用户管理
 */
$(function () {
    // 显示标题栏按钮
    if ($("#insertUser").length == 0) {
        $("#titleButton").html("新增用户");
        $("#titleButtonDiv").removeAttr("hidden");
    }

    // 调控用户界面用户管理页面高度
    if ($("#companyId").length == 0) {
        $("#userTable").addClass("main-box-b");
    }

    $("#titleButton").on("click", function (e) {
        showModal(e);
    });

    $("#insertUser").on("click", function (e) {
        showModal(e);
    });

    // 修改
    $('.userEdit').on('click', function (e) {
        showModal(e);
    });

    function showModal(e) {
        $('#editRoleId').removeAttr("disabled");
        $("#roleEditDiv").removeClass("hidden");
        // 去除前端校验内容和锁定
        $("#editNameInfo").addClass("hide");
        $("#editPasswordInfo").addClass("hide");
        $("#editMobileInfo").addClass("hide");
        $("#eidtUserEmailInfo").addClass("hide");

        $("#saveUser").removeAttr("disabled");

        // 获取标签id校验操作类型
        var btnId = e.target.id;
        if (btnId == "titleButton" || btnId == "insertUser") {
            // 修改标题名
            userLabel = "新增用户";
            // 还原表单默认值
            $("input").val(null);
            // 角色
            initEditRoleId();
            // 启用密码修改
            $("#passwordLine").removeAttr("style");
            $("#editPassword").val("123456");
            $("#editPassword").attr("type", "text");
            // 设置新增/修改区分标识符
            $("#userId").val("insertUser");
            $('#userModal').modal('show');
        } else {
            // 根据标签id获取companyId
            getUserDetail(btnId.split('_')[1]);

            // 禁用密码修改——不显示不赋值
            $("#passwordLine").attr("style", "display:none");
        }
    }

    // 用户详情获取
    function getUserDetail(userId) {
        // 发送查询请求
        $.getJSON(ADMS.ctx + '/user/getUserDetail', $.param({"id":userId}), function (resp) {
            if (resp['success']) {
                // 获取请求参数
                user = resp['data']['user'];

                // 所属企业
                if ($("#userCompanyId").length > 0) {
                    $("#userCompanyId").select2("val", user.companyId);
                }
                // 初始化角色列表
                var roleId;
                if (user.role) {
                    roleId = user.role.id;
                } else {
                    roleId = null;
                }
                initEditRoleId(roleId);
                // 表单赋值
                $("#editName").val(user.loginName);
                $("#editMobile").val(user.mobile);
                $("#editUserEmail").val(user.email);
                $("#editRemark").val(user.remark);
                // 设置新增/修改区分标识符
                $("#userId").val(user.id);
                $('#userModal').modal('show');
            } else {
                showpromptModal(resp['msg']);
            }
        });
    }

    // 保存/新增
    $("#saveUser").on("click", function () {
        // 获取修改/添加后的属性值
        var user = {};
        if ($("#userCompanyId").length > 0) {
            user.companyId = $("#userCompanyId").select2("val");
            user.roleId = $("#editRoleId").val();
        }
        user.loginName = $("#editName").val();
        user.password = $("#editPassword").val();
        user.mobile = $("#editMobile").val();
        user.email = $("#editUserEmail").val();
        user.remark = $("#editRemark").val();

        // 获取新增/修改标识位
        user.id = $("#userId").val();

        // 设置默认请求路径
        var path = ADMS.ctx + '/user/saveUser';
        // 根据标识位更改请求路径
        if (user.id == 'insertUser') {
            // 新增时id为标识位
            delete user.id;
        } else {
            // 修改时password无权变更
            delete user.password;
        }
        // 发送请求 新增/修改
        $.getJSON(path, $.param(user), function (resp) {
            hideModal('userModal');
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });
    });

    // 公司与角色下拉二级联动
    /*$('#userCompanyId').on("change", function(){
        initEditRoleId();
    });*/
});

function initEditRoleId(roleId) {
    var companyId;

    /*if ($("#userCompanyId").length > 0) {
        companyId = $('#userCompanyId').select2("val");
    }*/
    $('#editRoleId').select2("destroy");
    $('#editRoleId').empty();
    $.getJSON(ADMS.ctx + '/user/getRoleListByRoleLevel', function (resp) {
        if (resp['success']) {
            var roleList = resp['data']['roleList'];

            if(roleList.length == 0){
                $('#editRoleId').append("<option value=''>- 该客户尚未定义角色 -</option>");
            }

            $.each(roleList, function (i, role) {
                $('#editRoleId').append("<option value="+role.id+">"+role.name+"</option>");
            });

            $('#editRoleId').select2();
            if (roleId){
                $('#editRoleId').select2('val',roleId);
            }
        } else {
            $("#roleEditDiv").addClass("hidden");
            showpromptModal(resp['msg']);
        }
    });

    // 前端校验
    // 用户名校验
    $("#editName").blur(function () {
        if (!$("#editName").val()) {
            $("#editNameInfo").html("*请输入用户名");
            $("#editNameInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else if (!isTrueName($("#editName").val())) {
            $("#editNameInfo").html("*用户名必须为英文");
            $("#editNameInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else {
            $("#editNameInfo").addClass("hide");
            $("#saveUser").removeAttr("disabled");
        }
    });

    // 密码校验
    $("#editPassword").blur(function () {
        if (!$("#editPassword").val()) {
            $("#editPasswordInfo").html("*请输入密码");
            $("#editPasswordInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else if (!isPasswd($("#editPassword").val())) {
            $("#editPasswordInfo").html("*密码长度6-20位，不可有中文字符");
            $("#editPasswordInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else {
            $("#editPasswordInfo").addClass("hide");
            $("#saveUser").removeAttr("disabled");
        }
    });

    // 手机号校验
    $("#editMobile").blur(function () {
        if (!$("#editMobile").val()) {
            /*$("#editMobileInfo").html("*请输入手机号");
            $("#editMobileInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");*/
        } else if (!isMobil($("#editMobile").val())) {
            $("#editMobileInfo").html("*请输入正确的手机号");
            $("#editMobileInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else {
            $("#editMobileInfo").addClass("hide");
            $("#saveUser").removeAttr("disabled");
        }
    });

    // 邮箱校验
    $("#editUserEmail").blur(function () {
        if (!$("#editUserEmail").val()) {
            /*$("#editUserEmailInfo").html("*请输入联系人邮箱");
            $("#editUserEmailInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");*/
        } else if (!isEmail($("#editUserEmail").val())) {
            $("#editUserEmailInfo").html("*请输入正确的邮箱账号");
            $("#editUserEmailInfo").removeClass("hide");
            $("#saveUser").attr("disabled", "disabled");
        } else {
            $("#editUserEmailInfo").addClass("hide");
            $("#saveUser").removeAttr("disabled");
        }
    });
}

