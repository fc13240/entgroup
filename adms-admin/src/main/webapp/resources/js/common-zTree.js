// JavaScript Document
/**
 * Created by mxy on 2017-03-31 16:58.
 * Description zTree通用js
 */
var setting = {
    view: {
        showIcon: false,
        showLine: false
    },
    check: {
        enable: true,
        chkboxType: {"Y": "ps", "N": "ps"}
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onCheck: onCheck
    }
};
var zNodes = [
    {id: 1, pId: 0, name: "随意勾选 1", open: true},
    {id: 11, pId: 1, name: "随意勾选 1-1", open: true},
    {id: 12, pId: 1, name: "随意勾选 1-2", open: true},
    {id: 2, pId: 0, name: "随意勾选 2", checked: true, open: true},
    {id: 21, pId: 2, name: "随意勾选 2-1"},
    {id: 22, pId: 2, name: "随意勾选 2-2", open: true},
    {id: 23, pId: 2, name: "随意勾选 2-3"}
];
//zTree begin
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("zTreeBody"),
        nodes = zTree.getCheckedNodes(true),
        id = "";
    nodes.sort(function compare(a, b) {
        return a.id - b.id;
    });
    for (var i = 0, l = nodes.length; i < l; i++) {
        if (!nodes[i].isParent) {
            id += nodes[i].id + ",";
        }
    }
    if (id.length > 0) id = id.substring(0, id.length - 1);
    $('#zTreeResults').val(id);
}
function openZTreeModalSelect(id,name) {
    $('label[for='+id+']').html(name);
    $('#'+id).parent().parent().removeClass("line-form-blank");
}
