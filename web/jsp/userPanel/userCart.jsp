<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="manegers.Path" %>

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
            Not edition in basket
        </div>
    </c:when>

    <c:otherwise>
        <div>
            Total sum:
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
            <a href="${Path.USER_MAKE_ORDER_CART}" class="btn btn-outline-success col-2 d-inline-block">Pay</a>
            <a href="${Path.USER_CLEAR_CART}" class="btn btn-outline-danger col-2 d-inline-block">Clear cart</a>

        </div>
        <div class="article">
        <c:forEach items="${cart.editions}" var="edition">
            <div class="row">
                <c:choose>
                    <c:when test="${edition.imgPath == null}">
                        <div class="img_article col-1">
                            <img src="/resources/imgs/base_article_img.jpg" alt="article_image">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="img_article col-1">
                            <img src="${edition.imgPath}" alt="article_image">
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="col-11 info">
            <c:choose>
                <c:when test="${language == 'uk'}">
                    <div class="title_article">
                            ${edition.titleUk}
                    </div>
                    <div class="description">
                            ${edition.descriptionUk}
                    </div>
                    <div class="author">
                        <strong><fmt:message key="admin.form.author"/>:</strong> ${edition.author.nameUk}
                    </div>
                    <div class="genre">
                        <strong><fmt:message key="admin.form.genre"/>:</strong> ${edition.genre.nameUk}
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="title_article">
                            ${edition.titleEn}
                    </div>

                    <div class="description">
                            ${edition.descriptionEn}
                    </div>
                    <div class="author">
                        <strong><fmt:message key="admin.form.author"/>:</strong> ${edition.author.nameEn}
                    </div>
                    <div class="genre">
                        <strong><fmt:message key="admin.form.genre"/>:</strong> ${edition.genre.nameEn}
                    </div>
                </c:otherwise>
            </c:choose>
                </div>
                <div class="price">
                    <fmt:message key="main.price" />
                    <c:choose>
                        <c:when test="${language == 'uk'}">
                            <c:choose>
                                <c:when test="${edition.measurement == 'UAH'}">
                                    ${edition.price}
                                </c:when>
                                <c:otherwise>
                                    ${edition.price*30}
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${edition.measurement == 'UAH'}">
                                    ${edition.price/30}
                                </c:when>
                                <c:otherwise>
                                    ${edition.price}
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                    <fmt:message key="currency" />
                </div>

                <a href="${Path.USER_REMOVE_FROM_CART}&idRemEd=${edition.id}&priceEd=${edition.price}&measEd=${edition.measurement}" class="btn btn-outline-danger col-2 d-inline-block">Delete from cart</a>
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
