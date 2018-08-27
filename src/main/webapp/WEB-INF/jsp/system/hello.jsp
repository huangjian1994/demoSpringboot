<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-1-23
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/system/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basepath}/static/layui/css/layui.css">
    <script src="${basepath}/static/layui/layui.js"></script>
    <script src="${basepath}/static/js/jquery-3.3.1.min.js"></script>

</head>

<body>
    <div class="layui-row" style="height: 120px; line-height: 120px; background-color: #eee; padding-left: 10px; margin: 35px 2px 0;">
        <div class="layui-inline" style="margin-left: 20px">
            <div class="layui-input-inline" style="width: 240px;">
                <input type="text" name="search" id="search" placeholder="姓名" autocomplete="off" class="layui-input">
            </div>
            <i class="layui-icon layui-btn" onclick="search()">&#xe615;</i>
        </div>
    </div>
    <table class="layui-table layui-form">
        <%--<colgroup>

            <col width="100">
            <col width="100">
            <col width="100">
            <col width="100">
            <col width="150">
            <col width="100">
            <col>

        </colgroup>--%>
        <thead>
        <tr>

            <th>用户名</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>密码</th>
            <th>操作</th>

        </tr>
        </thead>
        <tbody id="dataList">

        <c:if test="${empty userList}">
            <tr>
                <td colspan="10" style="text-align: center">暂无数据</td>

            </tr>
        </c:if>
        <c:if test="${not empty userList}">
            <c:forEach items="${userList}" var="item">
                <tr>
                    <td>${item.username}</td>
                    <td>${item.name}</td>
                    <td>${item.age}</td>
                    <td>${item.password}</td>

                    <td>
                        <a href="${basepath}/user/edit/?id=${item.id}" class="layui-btn layui-btn-sm">修改</a>
                        <a href="javaScript:void (0);" onclick="delUser('${item.id}')" class="layui-btn layui-btn-sm layui-btn-danger">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        </tbody>
    </table>

    <div id="tableList"></div>
    <h2 >
        <a style="align-content: center" href="${basepath}/user/edit" class="layui-btn layui-btn-sm">新增</a>
    </h2>

<script>

    var layer = "";
    layui.use('layer', function(){
        layer = layui.layer;
    });
    function delUser(id) {
        var index = layer.confirm("确认删除该用户？", function() {
            var postData = {};
            postData.id = id;

            $.ajax({
                url:"${basepath}/user/deluser",
                type: "POST",
                data: postData,
                success: function(res) {
                    console.log(res.code);
                    if(res.code === 0) {
                        layer.close(index);
                        setTimeout(function() {
                            location.href = "${basepath}/user/users";
                        }, 1000);
                    }
                }
            })
        })
    }

    function search() {
        var postData = {};
        postData.search = $.trim($("#search").val());
        $.ajax({
            url:"${basepath}/user/searchusers",
            type:"GET",
            data:postData,
            success:function (res) {
                $("#dataList").html(res)
            }
    })

    }
</script>

</body>
</html>
