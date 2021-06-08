<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri = "/WEB-INF/tld/custom.tld" prefix = "ct" %>
<%@ page import="manegers.Path" %>
<c:set var="page" value="${Path.USER_CART_PAGE}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My basket</title>
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
    <c:when test="${cart == null || cart.isEmpty()}">
        <div class="col-12 text-center">
            <fmt:message key="user.empty.basket" />
        </div>
    </c:when>

    <c:otherwise>
        <div>
            <fmt:message key="user.basket.summary" />
            <c:choose>

                <c:when test="${language == 'uk'}">
                    ${cart.totalSum*30}
                </c:when>
                <c:otherwise>
                    ${cart.totalSum}
                </c:otherwise>

            </c:choose>
            <fmt:message key="currency" />

        </div>
        <div>
            <a href="${Path.USER_MAKE_ORDER_CART}" class="btn btn-outline-success col-2 d-inline-block">
                <fmt:message key="user.basket.confirm" /></a>
            <a href="${Path.USER_CLEAR_CART}" class="btn btn-outline-danger col-2 d-inline-block">
                <fmt:message key="user.basket.clear" />
            </a>

        </div>
        <div class="article">
        <c:forEach items="${cart.editions}" var="edition">
            <div class="row">
                <div class="img_article col-1">
                    <img src="<ct:print-imgPath edition="${edition}"/>" alt="article_image">
                </div>
                 
                <div class="col-11 info">
                    <div class="title_article">
                        <ct:print-title edition="${edition}" language="${language}"/>
                    </div>
                    <div class="description">
                        <ct:print-description edition="${edition}" language="${language}"/>
                    </div>
                    <div class="author">
                        <strong><fmt:message key="admin.form.author"/>:</strong>
                        <ct:print-author author="${edition.author}" language="${language}"/>
                    </div>
                    <div class="genre">
                        <strong><fmt:message key="admin.form.genre"/>:</strong>
                        <ct:print-genre genre="${edition.genre}" language="${language}"/>
                    </div>
                </div>
                <div class="price">
                    <fmt:message key="main.price" />
                    <ct:print-priceEdition edition="${edition}" language="${language}"/>
                    <fmt:message key="currency" />
                </div>
                <a href="${Path.USER_REMOVE_FROM_CART}&idRemEd=${edition.id}&priceEd=${edition.price}&measEd=${edition.measurement}" class="btn btn-outline-danger col-2 d-inline-block">
                    <fmt:message key="user.basket.clearPosition" /></a>
            </div>
            <hr>
        </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>

</body>
</html>
