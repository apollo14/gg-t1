<%--
  Created by IntelliJ IDEA.
  User: Q1O1
  Date: 07-12-2016
  Time: 14:57
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <g:render template="../layouts/head"/>
</head>

<body>
<div class="container-fluid">
    <g:render template="filter"/>
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#gpw">GPW</a></li>
        <li><a data-toggle="tab" href="#tfi">TFI</a></li>
    </ul>
    <div class="tab-content">
        <div id="gpw" class="tab-pane fade in active">
            <g:render template="total"/>
            <table class="table">
                <tbody>
                <g:each in="${registers}" var="register">
                    <tr>
                        <td><g:link controller="register" action="show" id="${register?.id}">${register?.id}</g:link></td>
                        <td><g:render template="registerDetails" bean="${register}"/></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
        <div id="tfi" class="tab-pane fade">
            <h3>TFI ready</h3>
        </div>
    </div>
</div>
</body>
</html>