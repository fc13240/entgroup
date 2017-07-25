$(".export-btn").on("click", function () {
    var exportItem = $(this).attr("id");
    window.location.href = ADMS.ctx+'/adDisplayCount/exportDisplayCount?exportItem='+exportItem;
})