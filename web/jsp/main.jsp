<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="../WEB-INF/resources/css/style.css">
    <link rel="stylesheet" href="../WEB-INF/resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../WEB-INF/resources/css/main.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
</head>
<body>

<c:import url="/WEB-INF/views/header.jsp"/>
<div class="container main">
    <div class="row">
        <div class="col-3">
            <aside>
                <div >Filter</div>
                <hr>
                <div class="genre">
                    <h5>Genre</h5>
                    <ul>
                        <li>All</li>
                        <li class="active">Fantasy</li>
                        <li>Roman</li>
                        <li>Drama</li>
                    </ul>
                </div>
            </aside>
        </div>

        <div class="col-9 articles">

            <div class="row">
                <c:forEach var="i" begin="1" end="7" step="1">
                    <div class="col-12 col-md-3">
                        <div class="article">
                            <div class="img_article"><img src="../WEB-INF/resources/imgs/base_article_img.jpg" alt="article_image"></div>

                            <div class="title_article">
                                Article 1
                            </div>
                            <div class="desctiption">
                                Show more
                            </div>
                            <div class="d-inline-flex">
                                <button
                                        <c:if test="${user != null}">
                                            type="submit"
                                        </c:if>

                                        <c:if test="${user == null}">
                                            type="button"
                                            title="You have to be login"
                                            disabled
                                        </c:if>

                                >Order</button>
                                <div class="price">150.5 UAH</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>