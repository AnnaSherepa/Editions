<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="manegers.Path" %>
<c:set var="page" value="${Path.ADMIN_ALL_USER_PAGE}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
</head>
<body>

<c:import url="/WEB-INF/views/header.jsp"/>
<c:import url="/WEB-INF/views/userSubmenu.jsp"/>

<div class="container main">
        <div class="row">
            <div class="col-12">
                <c:choose>
                    <c:when test="${allUsers == null}">
                        <div>No users in system</div>
                    </c:when>
                    <c:otherwise>
                        <table class="table" bordered = "1">
                            <tr class="row main_head" style="margin:0;">
                                <td class="d-none d-md-block col-md-3 " ><fmt:message key="admin.list.surname" /></td>
                                <td class="d-none d-md-block col-md-3 " ><fmt:message key="admin.list.name" /></td>
                                <td class="d-none d-md-block col-md-3 " ><fmt:message key="admin.list.login" /></td>
                                <td class="d-none d-md-block col-md-3 " ><fmt:message key="admin.list.Status" /></td>
                            </tr>
                            <c:forEach items="${allUsers}" var="userElement">
                                <tr class="rowTable row" >
                                    <td class="col-6 col-md-3" style="font-size: 14px;">${userElement.surname}</td>
                                    <td class="col-6 col-md-3" style="font-size: 14px;">${userElement.name}</td>
                                    <td class="col-12 col-md-3" style="font-size: 14px;">${userElement.login}</td>

                                    <td class="col-6 col-md-3">
                                        <form method="post" action="/Controller?command=switchUserStatus">
                                            <input type="hidden" name="userId" value="${userElement.id}">
                                            <input type="hidden" name="userStatus" value="${userElement.status}">
                                            <c:if test="${userElement.status == 1}">
                                                <button type="submit" class="btn btn-outline-danger"><fmt:message key="admin.list.Blocked" /></button>
                                            </c:if>
                                            <c:if test="${userElement.status == 0}">
                                                <button type="submit" class="btn btn-outline-success"><fmt:message key="admin.list.Active" /></button>
                                            </c:if>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    <div class="row">
        <div class="col-12">
            <ul style="list-style: none; text-decoration: none" class="d-inline-block">
            <c:forEach var="page" end="${numOfPages}" step="1" begin="1">
                <a href="${Path.ADMIN_USER_LIST}&page=${page}">
                    <li class="d-inline">${page}</li>
                </a>
            </c:forEach>
            </ul>
        </div>
    </div>
    </div>
<c:import url="/WEB-INF/views/footer.jsp"/>
