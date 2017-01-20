<%--
  Created by IntelliJ IDEA.
  User: Q1O1
  Date: 07-12-2016
  Time: 15:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
    <div class="container-fluid">
        <g:link controller="register" action="index">index</g:link>
        <div class="row">
            <div class="col-sm-12"><h3>${register.id}</h3></div>
        </div>
        <g:render template="registerDetails" bean="${register}"/>
        <g:render template="operations"/>
    </div>
</body>
</html>