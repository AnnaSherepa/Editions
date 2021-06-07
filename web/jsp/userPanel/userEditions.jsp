<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="manegers.Path" %>

<!DOCTYPE html>
<html lang="${language}">
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
<c:if test="${user != null}">
    <jsp:include page="/WEB-INF/views/userSubmenu.jsp"/>
</c:if>

<div class="container main">
    <div class="row">

    <c:choose>
        <c:when test="${user.editions == null || user.editions.isEmpty()}">
            <div class="col-12 text-center">
                Not editions
            </div>
        </c:when>
        <c:otherwise>
        <div class="col-12 articles">
            <div class="row">
                <c:forEach items="${user.editions}" var="edition">
                    <div class="col-12 col-md-2">
                        <div class="article">
                            <c:choose>
                                <c:when test="${edition.imgPath == null}">
                                    <div class="img_article"><img src="../resources/imgs/base_article_img.jpg" alt="article_image"></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="img_article"><img src="${edition.imgPath}" alt="article_image"></div>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${language == 'uk'}">
                                    <div class="title_article">${edition.titleUk}</div>
                                    <div class="description">${edition.descriptionUk}</div>
                                    <div class="author"><strong><fmt:message key="admin.form.author"/>:</strong> ${edition.author.nameUk}</div>
                                    <div class="genre"><strong><fmt:message key="admin.form.genre"/>:</strong> ${edition.genre.nameUk}</div>
                                </c:when>
                                <c:otherwise>
                                    <div class="title_article">${edition.titleEn}</div>
                                    <div class="description">${edition.descriptionEn}</div>
                                    <div class="author"><strong><fmt:message key="admin.form.author"/>:</strong> ${edition.author.nameEn}</div>
                                    <div class="genre"><strong><fmt:message key="admin.form.genre"/>:</strong> ${edition.genre.nameEn}</div>
                                </c:otherwise>
                            </c:choose>
                            <form action="${Path.USER_DELETE_EDITION_BY_ID}" method="post">
                                <input type="hidden" name="idEdition" value="${edition.id}">
                                <button class="btn btn-outline-danger"><fmt:message key="main.admin.delete"/></button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        </c:otherwise>
    </c:choose>

    </div>
</div>

<c:import url="/WEB-INF/views/footer.jsp"/>

