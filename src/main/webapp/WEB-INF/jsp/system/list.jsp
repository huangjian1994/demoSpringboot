<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/system/taglibs.jsp"%>
<c:if test="${empty list}">
    <tr>
        <td colspan="10" style="text-align: center">暂无数据</td>

    </tr>
</c:if>
<c:if test="${not empty list}">
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.username}</td>
            <td>${item.name}</td>
            <td>${item.age}</td>
            <td>${item.password}</td>

            <td>
                <a href="${basepath}/user/update/?id=${item.id}" class="layui-btn layui-btn-sm">修改</a>
                <a href="javaScript:void (0);" onclick="delUser('${item.id}')" class="layui-btn layui-btn-sm layui-btn-danger">删除</a>
            </td>
        </tr>
    </c:forEach>
</c:if>