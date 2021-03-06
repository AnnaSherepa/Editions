<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri = "/WEB-INF/tld/custom.tld" prefix = "ct" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<%@ page import="manegers.Path" %>
<%@ page import="manegers.ProjectConstants" %>

<c:set var="page" value="${Path.MAIN_PAGE}" scope="request"/>
<c:set var="showEditions" value="${actualEditions == null ? allEditions : actualEditions}"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
  <meta charset="UTF-8">
  <title>Main</title>
  <link rel="stylesheet" type="text/css" href="<c:url value="../resources/css/style.css"/>">
  <link rel="stylesheet" href="<c:url value="../resources/css/bootstrap.min.css"/>">
  <link rel="stylesheet" type="text/css" href="<c:url value="../resources/css/main.css"/>">

  <link rel="preconnect" href="https://fonts.gstatic.com">
</head>
<body>


<jsp:include page="/WEB-INF/views/header.jsp"/>
<c:if test="${user != null}">
  <jsp:include page="/WEB-INF/views/userSubmenu.jsp"/>
</c:if>
<div class="container main">
  <div class="row">
    <div class="col-3">
      <aside>
        <div class="row">
          <form class="aside-form d-flex flex-row align-content-between" method="post" action="${Path.USER_SEARCH_BY_TITLE_EDITION}">
            <input class="form-control mr-sm-2 col-8" name="searchRequest" type="search"
                   placeholder="<fmt:message key="main.aside.search"/>" aria-label="Search" required>
            <button class="btn btn-outline-success col-4" type="submit"><fmt:message key="main.aside.search"/></button>
          </form>
        </div>
        <hr>

        <div class="row">
            <form action="${Path.USER_SORT_EDITION}" method="post" class="aside-form d-flex flex-row align-content-between">
                <select class="form-select col-8 " name="sortBy" aria-label="Default select example" required>
                    <option selected disabled><fmt:message key="main.aside.sortBy"/></option>
                    <option value="author"><fmt:message key="main.aside.sortByAuthor"/></option>
                    <option value="price"><fmt:message key="main.aside.sortByPrice"/></option>
                    <option value="title"><fmt:message key="main.aside.sortByTitle"/></option>
                </select>
                <button class="btn btn-outline-success col-4" type="submit"><fmt:message key="main.aside.sort"/></button>
            </form>
        </div>
        <hr>
        <div class="genre">
          <h5><fmt:message key="main.aside.genre"/></h5>

          <ul>
              <c:choose>
                  <c:when test="${genres == null}">
                      <li>No genre</li>
                  </c:when>
                  <c:otherwise>
                      <a href="${Path.USER_GROUP_BY_GENRE}"><li><fmt:message key="main.aside.genre.all"/></li></a>
                      <c:forEach items="${genres}" var="genre">
                          <a href="${Path.USER_GROUP_BY_GENRE}&groupBy=${genre.id}">
                              <li><ct:print-genre genre="${genre}" language="${language}"/></li>
                          </a>
                      </c:forEach>
                  </c:otherwise>
              </c:choose>
          </ul>
        </div>
      </aside>
    </div>

    <div class="col-9 articles">
      <div class="row">

        <c:choose>
          <c:when test="${showEditions == null}">
            <div class="col-12 text-center">No editions</div>
          </c:when>
          <c:otherwise>

            <c:forEach items="${showEditions}" var="edition">

              <div class="col-12 col-md-3">
                <div class="article">
                     <div class="img_article">
                         <img src="<ct:print-imgPath edition="${edition}"/>" alt="article_image">
                     </div>
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

                    <div class="price">
                        <fmt:message key="main.price" />
                        <ct:print-priceEdition edition="${edition}" language="${language}"/>

                        <fmt:message key="currency" />
                    </div>

                    <div class="row">
                    <form method="post" action="${Path.USER_ADD_TO_CART}" >
                      <input type="hidden" value="${edition.id}" name="orderedIdEdition">
                        <c:choose>
                            <c:when test="${user != null }">
                                <c:choose>
                                    <c:when test="${cart != null && !cart.isEmpty() && cart.existInCart(edition.id)}">
                                        <button class="btn btn-outline-success col-12" type="button"
                                                title="This edition already in your cart"
                                                disabled><fmt:message key="main.order"/></button>
                                    </c:when>
                                    <c:when test="${user.existInUserEditions(edition.id)}">
                                        <small><fmt:message key="main.orderedEdition"/></small>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-outline-success col-12" type="submit"><fmt:message key="main.order"/></button>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-outline-success col-12" type="button"
                                title="You have to be login"
                                disabled><fmt:message key="main.order"/></button>
                            </c:otherwise>

                        </c:choose>
                    </form>
                  </div>


                  <c:if test="${user.role == 'admin'}">
                    <div class="row admin-buttons">
                      <form action="${Path.ADMIN_EDIT_EDITION}" method="post" >
                        <input type="hidden" name="editedIdEdition" value="${edition.id}">
                        <button class="btn btn-outline-success"><fmt:message key="main.admin.edit"/></button>
                      </form>
                        <form action="${Path.ADMIN_DELETE_EDITION}" method="post">
                            <input type="hidden" name="idEdition" value="${edition.id}">
                            <button class="btn btn-outline-danger"><fmt:message key="main.admin.delete"/></button>
                        </form>
                    </div>
                    </c:if>
                  </div>
                </div>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </div>
    </div>

  </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>