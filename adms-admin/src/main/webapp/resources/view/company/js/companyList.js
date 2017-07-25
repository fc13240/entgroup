/**
 * Created by xiaokun on 2017/4/1 14:31
 * Description: 客户管理
 */
$(function () {
    var companyId;
    var companyVocationId;
    var company;
    var flag = true;
    // 客户新增/修改
    /*$('.companyEdit').on('click', function (e) {
        // 初始化表单 清空校验结果及按钮锁定
        $("#editCompanyNameInfo").addClass("hide");
        $("#editShortNameInfo").addClass("hide");
        $("#editContactInfo").addClass("hide");
        $("#editPhoneNumberInfo").addClass("hide");
        $("#editEmailInfo").addClass("hide");

        $("#saveCompany").removeAttr("disabled");

        // 定义默认标题名
        var companyLabel = "修改";
        // 获取标签id校验操作类型
        var btnId = e.target.id;
        if (btnId == "companyInsert") {
            // 修改标题名
            companyLabel = "新增客户";
            // 还原表单默认值
            $("input").val(null);
            // 还原下拉框默认值
            $("#companyVocationId").select2("val", "1");
            // 设置新增/修改区分标识符
            $("#companyId").val("insertCompany");
            flag = true;
        } else {
            // 根据标签id获取companyId
            companyId = btnId.split('_')[1];
            // flag = getCompanyDetail();
            
        }
        // 弹出框标题名赋值
        // $("#companyLabel").text(companyLabel);
        // 显示弹出框
        /!*if (flag == true) {
            $('#companyModal').modal('show');
        } else {
            showpromptModal('获取客户详情失败');
        }*!/
    });*/

    // 客户详情获取
    function getCompanyDetail() {
        // 发送查询请求
        $.getJSON(ADMS.ctx + '/company/companyDetail', $.param({'companyId':companyId}), function (resp) {
            if (resp['success']) {
                // 获取请求参数
                company = resp['data']['company'];
                // 下拉框赋值
                $("#companyVocationId").select2("val", company.companyVocationId);
                // 表单赋值
                $("#editCompanyName").val(company.companyName);
                $("#editShortName").val(company.shortName);
                $("#editContact").val(company.contact);
                $("#editPhoneNumber").val(company.phoneNumber);
                $("#editEmail").val(company.email);
                // 设置新增/修改区分标识符
                $("#companyId").val(company.id);
                flag = true;
            } else {
                flag = false;
            }
        });
        return flag;
    }

    // 保存/新增
    $("#companyModal").find(".confirmButton").one("click", function () {
        // 获取修改/添加后的属性值
        var company = {};
        company.companyName = $("#editCompanyName").val();
        company.shortName = $("#editShortName").val();
        company.companyVocationId = $("#companyVocationId").select2("val");
        company.contact = $("#editContact").val();
        company.phoneNumber = $("#editPhoneNumber").val();
        company.email = $("#editEmail").val();
        // 获取新增/修改标识位
        company.id = $("#companyId").val();

        // 设置默认请求路径
        var path = ADMS.ctx + '/company/saveCompany';
        // 根据标识位更改请求路径
        if (company.id == 'insertCompany') {
            delete company.id;
        }
        // 发送请求 新增/修改
        $.getJSON(path, $.param(company), function (resp) {
            hideModal('companyModal');
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });
    });

    // 前端校验
    // 客户全称校验
    $("#editCompanyName").blur(function () {
        if (!$("#editCompanyName").val()) {
            $("#editCompanyNameInfo").html("*请填写客户全称");
            $("#editCompanyNameInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editCompanyNameInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    // 客户简称校验
    $("#editShortName").blur(function () {
        if (!$("#editShortName").val()) {
            $("#editShortNameInfo").html("*请填写客户简称");
            $("#editShortNameInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editShortNameInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    // 联系人校验
    $("#editContact").blur(function () {
        if (!$("#editContact").val()) {
            $("#editContactInfo").html("*请填写联系人");
            $("#editContactInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editContactInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    // 联系电话校验
    $("#editPhoneNumber").blur(function () {
        if (!$("#editPhoneNumber").val()) {
            $("#editPhoneNumberInfo").html("*请填写联系电话");
            $("#editPhoneNumberInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else if (!isTel($("#editPhoneNumber").val())) {
            $("#editPhoneNumberInfo").html("*请输入正确的固定电话");
            $("#editPhoneNumberInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editPhoneNumberInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    // 邮箱校验
    $("#editEmail").blur(function () {
        if (!$("#editEmail").val()) {
            $("#editEmailInfo").html("*请填写联系人邮箱");
            $("#editEmailInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else if (!isEmail($("#editEmail").val())) {
            $("#editEmailInfo").html("*请输入正确的邮箱账号");
            $("#editEmailInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editEmailInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    //登录账号校验
    $("#editUserName").blur(function () {
        if (!$("#editUserName").val()) {
            $("#editUserNameInfo").html("*请填写登录账号");
            $("#editUserNameInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else if (!isTrueName($("#editUserName").val())) {
            $("#editUserNameInfo").html("*请输入正确的账号");
            $("#editUserNameInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editUserNameInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });

    //登录密码校验
    $("#editPassword").blur(function () {
        if (!$("#editPassword").val()) {
            $("#editPasswordInfo").html("*请填写登录密码");
            $("#editPasswordInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else if (!isPasswd($("#editPassword").val())) {
            $("#editPasswordInfo").html("*请输入正确的密码格式");
            $("#editPasswordInfo").removeClass("hide");
            $("#saveCompany").attr("disabled", "disabled");
        } else {
            $("#editPasswordInfo").addClass("hide");
            $("#saveCompany").removeAttr("disabled");
        }
    });
})
