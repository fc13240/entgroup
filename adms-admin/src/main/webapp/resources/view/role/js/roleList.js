/**
 * Created by xiaokun on 2017/4/5 15:37
 * Description:角色管理
 */

$(function () {
    $("#titleButton").html("新增角色");
    $("#titleButtonDiv").removeAttr("hidden");

    var role = {};
    var title = "新增角色";

    $("#titleButton").on("click", function () {
        role.id = "roleInsert";
        $("#roleType").removeAttr("disabled");
        $("#roleType").select2("destroy");
        $("[value=0]").remove();
        $("#roleType").select2();
        role.roleLevel = 1;
        showModal();
    })

    //设置标签-加载标签树
    $('.roleEdit').on('click', function (e) {
        role.id = e.target.id;
        $("#roleType").removeAttr("disabled");
        $("#roleType").select2("destroy");
        $("#roleType").append("<option value='0'>超级管理员</option>");
        $("#roleType").select2();
        showModal();
    });

    // 弹窗显示
    function showModal() {
        var title;
        if (role.id != "roleInsert") {
            title = "修改";
            role.id = role.id.split("_")[1];
            $("#editRoleId").val(role.id);
            $("#roleType").attr("disabled", "disabled");
        } else {
            // 显示标题栏按钮
            title = "新增角色";
            delete role.id;
        }
        $("#roleModalTitle").html(title);
        // 查询请求
        $.getJSON(ADMS.ctx + '/role/getAuthorityTree', $.param(role), function (resp) {
            if (resp['success']) {
                zNodes = resp['data']['treeNodes'];
                role = resp['data']['role'];

                $("#roleName").val(role.name);
                $("#roleDescription").val(role.description);

                $("#roleType").select2("val", role.roleLevel);

                $('#zTreeSourceId').val(role.id);
                // 权限树赋值
                $.fn.zTree.init($("#zTreeBody"), setting, zNodes);
                // 标题赋值
                $("#roleModalTitle").text(title);
                // 关闭滚动条显示(兼容1366*768分辨率底部状态栏遮挡处理)
                $("div[class=modal-content]").attr("style", "bottom:100px");
                $("#roleModal").attr("style", "overflow-y:hidden");

                // 显示弹窗
                $('#roleModal').modal('show');
            } else {
                showpromptModal(resp['msg']);
            }
        });
    }


    $("#roleType").on("change", function () {
        var role = {};
        role.roleLevel = $("#roleType").select2("val");
        $.getJSON(ADMS.ctx + '/role/getAuthorityTree', $.param(role), function (resp) {
            if (resp['success']) {
                zNodes = resp['data']['treeNodes'];
                // 权限树赋值
                $.fn.zTree.init($("#zTreeBody"), setting, zNodes);
            } else {
                showpromptModal(resp['msg']);
            }
        });
    });

    //保存设置后的角色权限
    $("#saveRole").on("click", function () {
        var role = {};

        role.id = $("#editRoleId").val();
        role.name = $("#roleName").val();
        role.description = $("#roleDescription").val();
        role.authorityIds = $("#zTreeResults").val();
        role.roleLevel = $("#roleType").select2("val");

        if (role.id == "roleInsert") {;
            delete role.id;
        }

        $.getJSON(ADMS.ctx + "/role/saveRole", $.param(role), function (resp) {
            hideModal('roleModal');
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });
    });

    // 非空校验
    $("#roleName").blur(function () {
        $("#saveRole").removeAttr("disabled");
        if ($("#roleName").val() == "" || $("#roleName").val() == null) {
            $("#saveRole").attr("disabled", "disabled");
        }
    });
    $("#roleDescription").blur(function () {
        $("#saveRole").removeAttr("disabled");
        if ($("#roleDescription").val() == "" || $("#roleDescription").val() == null) {
            $("#saveRole").attr("disabled", "disabled");
        }
    });
})
