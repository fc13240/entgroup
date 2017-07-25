// JavaScript Document
/**
 * Created by mxy on 2017-03-29 11:28.
 * Description 弹窗通用js
 */
//提示框
function showpromptModal(content, succeed, showTime, reload, callback) {
    content = content ? content : "失败";//默认失败
    succeed = succeed ? true : false;//默认失败
    showTime = showTime ? showTime : 1500;//默认1500ms
    reload = reload==false ? false : true;//默认刷新页面
    callback = callback == undefined ? function(){} : callback;//无操作
    $("#promptModalImg").attr('src',"../resources/images/blue-"+(succeed?"right":"wrong")+".png");
    $("#promptModalBody").html(content);
    $('#promptModal').modal('show');
    //定时隐藏提示框
    window.setTimeout(function(){ hideModal('promptModal');},showTime);
    if(reload) {
        $('#promptModal').on('hidden.bs.modal', function () {
            location.reload();
        })
    }else {
        $('#promptModal').on('hidden.bs.modal', function () {
            callback();
            callback = function(){};
        })
    }
}
//隐藏提示栏
function hideModal(id) {
    $('#'+id).modal('hide');
}

//成功刷新页面
function showSuccessModal(content) {
    showpromptModal(content,true);
}

//确认框
function showConfirmModal(content, callback) {
    $("#confirmModalTitle").text("确定?");
    $("#confirmModalBody").html(content);
    $("#confirmModal").find(".confirmButton").one("click", function () {
        $('#confirmModal').modal('hide');
        $('#confirmModal').on('hidden.bs.modal', function () {
            callback();
        })
    });
    $('#confirmModal').modal('show');
}

//小确认框
function showSmallConfirmModal(content, callback) {
    $("#smallConfirmModalTitle").text("确定?");
    $("#smallConfirmModalBody").html(content);
    $("#smallConfirmModal").find(".confirmButton").one("click", function () {
        $('#smallConfirmModal').modal('hide');
        $('#smallConfirmModal').on('hidden.bs.modal', function () {
            callback();
        })
    });
    $('#smallConfirmModal').modal('show');
}

function inputModal(dataName, callback) {
    $('#data').val('');
    $("#inputModalTitle").text("请输入" + dataName);
    $("label[for='data']").html(dataName);
    $("#confirmInputButton").one("click", function () {
        $('#inputModal').modal('hide');
        callback($('#data').val());
    });
    $('#inputModal').modal('show');
}
function tableModal(data, title) {
    $("#tableModalTitle").text(title);
    var html = "";
    $.each(data, function (k, v) {
        html += '<tr>\
            <th scope="row">' + k + '</th>\
            <td>' + v + '</td>\
        </tr>';
    });
    $("#tableModalBody").html(html);
    $('#tableModal').modal('show');
}
function rpcAndShowData(url, pram) {
    rpc(url, pram, function (data) {
        if (data.success) {
            showSuccessModal("成功", data.result != undefined ? data.result : data.resultList);
        } else {
            showModal("失败", "请重试" + data.errorMsg);
        }
    })
}


















