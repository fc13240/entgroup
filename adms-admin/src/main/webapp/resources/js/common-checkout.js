// JavaScript Document
/**
 * Created by mxy on 2017-04-07 10:02.
 * Description
 */
//校验是否全由数字组成

function isDigit(s) {
    var patrn = /^[0-9]{1,20}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验登录名：只能输入5-20个以字母开头、可带数字、“_”、“.”的字串
function isRegisterUserName(s) {
    var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
    if (!patrn.exec(s)) return false
    return true
}

function isRegisterUserName(s) {
    var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
    if (!patrn.exec(s)) return false
    return true
}


//校验用户姓名：只能输入1-30个以字母开头的字串
function isTrueName(s) {
    var patrn = /^[a-zA-Z]{1,30}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验密码：只能输入6-20个字母、数字、下划线
function isPasswd(s) {
    var patrn = /^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_!@#$%^&*]+$).{6,20}$/;
    if (!patrn.exec(s)) return false
    return true
}
//测试密码强度：1-弱2-中3-强
function checkPasswordStrong(s) {
    var modes = 0;
    //正则表达式验证符合要求的
    if (s.length < 6) return modes;
    if (/\d/.test(s)) modes++; //数字
    if (/[a-z]/.test(s)) modes++; //小写
    if (/[A-Z]/.test(s)) modes++; //大写
    if (/[_!@#$%^&*]/.test(s)) modes++; //特殊字符
    //逻辑处理
    switch (modes) {
        case 1:
            return 1;
            break;
        case 2:
            return s.length < 12 ? 1 : 2
            break;
        case 3:
            return s.length < 12 ? 2 : 3
            break;
        case 4:
            return s.length < 12 ? 3 : 4
            break;
    }
}

//校验普通电话、传真号码：可以“+”开头，除数字外，可含有“-”
function isTel(s) {
//var patrn=/^[+]{0,1}(\d){1,3}[ ]?([-]?(\d){1,12})+$/;
    //var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
    var patrn = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验手机号码：必须以数字开头，除数字外，可含有“-”
function isMobil(s) {
    var patrn = /^1[34578]\d{9}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验邮政编码
function isPostalCode(s) {
//var patrn=/^[a-zA-Z0-9]{3,12}$/;
    var patrn = /^[a-zA-Z0-9 ]{3,12}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验搜索关键字
function isSearch(s) {
    var patrn = /^[^`~!@#$%^&*()+=|\\\][\]\{\}:;'\,.<>/?]{1}[^`~!@$%^&()+=|\\\][\]\{\}:;'\,.<>?]{0,19}$/;
    if (!patrn.exec(s)) return false
    return true
}

function isIP(s) //by zergling
{
    var patrn = /^[0-9.]{1,20}$/;
    if (!patrn.exec(s)) return false
    return true
}

//校验邮箱
function isEmail(s) {
    var patrn = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if(!patrn.exec(s)) return false
    return true
}