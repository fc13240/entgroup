<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

    function pageClick(pageNum) {
        $("#queryForm").append('<input type="hidden" name="pageNum" value="' + pageNum + '" />');
        $("#queryForm").append('<input type="hidden" name="navigatePages" value="${page.navigatePages}" />');
        $("#queryForm").append('<input type="hidden" name="total" value="${page.total}" />');
        $("#queryForm").submit();
    }
</script>
<c:if test="${(! empty (page.list))&&(fn:length(page.list)>0&&page.pages>1)}">
    <div class="col-lg-12 main-box">
        <nav aria-label="Page navigation" class="pagination-border col-lg-12">
            <ul class="pagination">
                <c:if test="${page.isFirstPage}">
                <li><a class="first-page unable-page" href="javascript:void(0)" aria-label="first">首页</a></li>
                </c:if>
                <c:if test="${!page.isFirstPage}">
                <li><a class="first-page" href="javascript:pageClick(1)" aria-label="first">首页</a></li>
                </c:if>

                <c:if test="${!page.hasPreviousPage}">
                <li><a class="previous-page unable-page" href="javascript:void(0)" aria-label="previous">上一页</a></li>
                </c:if>
                <c:if test="${page.hasPreviousPage}">
                <li><a class="previous-page" href="javascript:pageClick(${page.prePage})" aria-label="previous">上一页</a></li>
                </c:if>

                <c:forEach items="${page.navigatepageNums}" var="nav">
                <c:if test="${nav == page.pageNum}">
                <li><a class="unable-page active">${nav}</a></li>
                </c:if>
                <c:if test="${nav != page.pageNum}">
                <li><a href="javascript:pageClick(${nav})">${nav}</a></li>
                </c:if>
                </c:forEach>

                <c:if test="${!page.hasNextPage}">
                <li><a class="next-page unable-page" href="javascript:void(0)" aria-label="next">下一页</a></li>
                </c:if>
                <c:if test="${page.hasNextPage}">
                <li><a class="next-page" href="javascript:pageClick(${page.nextPage})" aria-label="next">下一页</a></li>
                </c:if>

                <c:if test="${page.isLastPage}">
                <li><a class="last-page  unable-page" href="javascript:void(0)" aria-label="last">末页</a></li>
                </c:if>
                <c:if test="${!page.isLastPage}">
                <li><a class="last-page" href="javascript:pageClick(${page.pages})" aria-label="last">末页</a></li>
                </c:if>
        </nav>
    </div>
</c:if>