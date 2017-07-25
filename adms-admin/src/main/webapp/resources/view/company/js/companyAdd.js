/**
 * Created by xiaokun on 2017/4/1 14:31
 * Description: 客户管理
 */



$(function () {

    $(".imgsShow").css("width", "118px").css("height", "118px");

    $("input [type=radio]").on("click", function () {
        var inputs = $("input [type=radio]");
        for (var i=0;i<inputs.length;i++) {
            if (inputs[i] == this) {
                inputs[i].attr("checked", "checked");
            } else {
                inputs[i].removeAttr("checked");
            }
        }
    });

    var company = {};

    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }else{
            alert('浏览器不支持预览功能！');
        }
        return url ;
    }

    var openAccountPermitPath = $("#openAccountPermitPath").val();
    var organizationCodePath = $("#organizationCodePath").val();

    $("#up_openAccountPermit").on("change", function (e) {
        var objUrl=getObjectURL(this.files[0]);
        openAccountPermitPath = objUrl;
        if(objUrl){
            $("#imgShowOpenAccountPermit").attr("src", objUrl);
        }
    });

    $("#up_organizationCode").on("change", function (e) {
        var objUrl=getObjectURL(this.files[0]);
        organizationCodePath = objUrl;
        if(objUrl){
            $("#imgShowOrganzationCode").attr("src", objUrl);
        }
    });

    // 客户新增/修改
    $("#saveCompany").on("click", function () {
        var companyType;
        var companyTypes = $("input[name=companyType]");
        for (var i=0;i<companyTypes.length;i++) {
            if (companyTypes[i].checked) {
                companyType = $(companyTypes[i]).attr("value");
            }
        }
        var companyId = $("#companyId").val();

        var companyName = $("#editCompanyName").val();
        var shortName = $("#editShortName").val();
        var companyVocationId = $("#companyVocationId").select2("val");
        var address = $("#editCompanyAddress").val();
        var internetAddress = $("#editCompanyInternetAddress").val();
        var phoneNumber = $("#editCompanyPhoneNumber").val();
        var contact = $("#editContact").val();
        var email = $("#editEmail").val();

        var mobile = $("#editPhoneNumber").val();
        var loginName = $("#editUserName").val();
        var password = $("#editPassword").val();

        /*company = {
            "companyType":companyType,
            "companyName":companyName,
            "shortName":shortName,
            "companyVocationId":companyVocationId,
            "address":address,
            "internetAddress":internetAddress,
            "phoneNumber":phoneNumber,
            "contact":contact,
            "email":email
        };
        $.ajaxSettings.async = false;
        $.getJSON(ADMS.ctx + '/company/saveCompany', $.param({
            "companyType":companyType,
            "companyName":companyName,
            "shortName":shortName,
            "companyVocationId":companyVocationId,
            "address":address,
            "internetAddress":internetAddress,
            "phoneNumber":phoneNumber,
            "contact":contact,
            "email":email,

            "mobile":mobile,
            "loginName":loginName,
            "password":password
        },true), function (resp) {
            if (!resp['success']) {
                showpromptModal(resp['msg'], false, 1500, true);
            } else {
                up_openAccountPermit(company);
            }
        });*/

        company = {
            "id":companyId,

            "companyType":companyType,
            "companyName":companyName,
            "shortName":shortName,
            "companyVocationId":companyVocationId,
            "address":address,
            "internetAddress":internetAddress,
            "phoneNumber":phoneNumber,
            "contact":contact,
            "email":email,

            "openAccountPermitPath":openAccountPermitPath,
            "organizationCodePath":organizationCodePath,

            "operatorLoginName":loginName,
            "operatorPassword":password,
            "operatorMobile":mobile
        };

        $.ajaxFileUpload({
            type : "POST",
            async : false,
            contentType : "application/json",
            url : ADMS.ctx + "/company/saveCompany",
            data: company,
            dataType: "json",
            fileElementId : ['up_openAccountPermit', 'up_organizationCode'],
            success:function(resp){
                if (resp['success']) {
                    showpromptModal(resp['msg'], true, 1500, false, function () {
                        window.location.href = ADMS.ctx + "/company/companyList";
                    });
                } else {
                    showpromptModal(resp['msg'], false, 1500, true);
                }
            }
        })

        // $.ajaxSettings.async = false;

        /*$.ajaxFileUpload({
            type: "POST",
            async:false,
            contentType : "application/json",
            url: ADMS.ctx + "/company/test",
            data:{},
            dataType: "json",
            fileElementId : 'openAccountPermitImg',
            success:function(resp){
                showpromptModal(resp['msg'], true, 1500, false,function(){
                    window.location.href = ADMS.ctx + '/company/companyList';
                });
            },
            error:function(resp){
                showpromptModal(resp['msg'], true, 1500, true);
            }
        });*/

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

function uploadImage(){
    $.ajax({
        type:'POST',
        url: 'ajax/uploadimage',
        data: {image: image},
        async: false,
        dataType: 'json',
        success: function(data){
            if(data.success){
                alert('上传成功');
            }else{
                alert('上传失败');
            }
        },
        error: function(err){
            alert('网络故障');
        }
    });
}

function up_openAccountPermit(company) {
    $.ajaxFileUpload({
        type: "POST",
        async:false,
        contentType : "application/json",
        url: ADMS.ctx + "/company/uploadOpenAccountPermit",
        data: company,
        dataType: "json",
        // fileElementId : ['up_openAccountPermit', 'up_organizationCode'],
        fileElementId : 'up_openAccountPermit',
        success:function(resp){
            if (!resp['success']) {
                showpromptModal(resp['msg'], false, 1500, true);
            } else {
                up_organizationCode(company);
            }
        }
    });
}

function up_organizationCode(company) {
    $.ajaxFileUpload({
        type: "POST",
        async:false,
        contentType : "application/json",
        url: ADMS.ctx + "/company/uploadOrganizationCode",
        data: company,
        dataType: "json",
        // fileElementId : ['up_openAccountPermit', 'up_organizationCode'],
        fileElementId : 'up_organizationCode',
        success:function(resp){
            if (resp['success']) {
                showpromptModal(resp['msg'], true, 1500, false,function(){
                    window.location.href = ADMS.ctx + '/company/companyList';
                });
            } else {
                showpromptModal(resp['msg'], false, 1500, true);
            }
        }
    });
}