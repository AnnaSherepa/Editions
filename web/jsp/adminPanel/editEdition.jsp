<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="manegers.Path" %>
<%@page import="models.enums.Money" %>
<c:set var="page" value="${Path.ADMIN_UPDATE_EDITION_PAGE}" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
    <link rel="stylesheet" href="../../resources/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/newEdition.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
</head>
<body>

<c:import url="/WEB-INF/views/header.jsp"/>
<c:import url="/WEB-INF/views/userSubmenu.jsp"/>


<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-7">
                <form action="${Path.ADMIN_UPDATE_EDITION}" class="inputAdmin" method="post">
                    <small class="ui-state-highlight">${editionAddedSuccess}</small>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title_uk" value="${editedEdition.titleUk}"
                               placeholder="<fmt:message key="admin.form.title.uk" />" required>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title_en" value="${editedEdition.titleEn}"
                               placeholder="<fmt:message key="admin.form.title.en" />" required>

                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control" name="img">
                    </div>

                    <div class="form-group">
                        <textarea type="textarea" class="form-control" value="${editedEdition.descriptionUk}"
                                  name="description_uk" placeholder="<fmt:message key="admin.form.description.uk" />"></textarea>
                    </div>

                    <div class="form-group">
                        <textarea type="textarea" class="form-control"  value="${editedEdition.descriptionEn}"
                                  name="description_en" placeholder="<fmt:message key="admin.form.description.en" />"></textarea>
                    </div>


                    <div class="form-group d-flex flex-row">
                        <select class="form-select" name="id_genre" aria-label="Default select example" required>
                            <option selected disabled><fmt:message key="admin.form.genre" /></option>
                            <option value="0">Undefined[Невизначено]</option>
                            <c:forEach items="${adminPanelGenre}" var="genre">
                                <option value="${genre.id}" <c:if test="${genre.id == editedEdition.idGenre}">selected</c:if>> ${genre}</option>
                            </c:forEach>
                        </select>


                        <button class="btn btn-outline-success" id="new_genre" type="button" onclick="check('new_genre', 'form_new_genre', 'none', 'inline-block')">
                            <fmt:message key="admin.form.newGenre" /></button>
                    </div>

                    <small class="ui-state-error-text">${idGenreError}</small>

                    <div class="form-group d-flex flex-row">
                        <select class="form-select" name="id_author" aria-label="Default select example" required>
                            <option selected disabled><fmt:message key="admin.form.author" /></option>
                            <option value="0">Undefined[Невизначено]</option>
                            <c:forEach items="${adminPanelAuthors}" var="author">
                                <option value="${author.id}"
                                        <c:if test="${author.id == editedEdition.idAuthor}">selected</c:if>>
                                        ${author}
                                </option>
                            </c:forEach>

                        </select>
                        <button class="btn btn-outline-success" id="new_author" type="button"
                                onclick="check('new_author', 'form_new_author', 'none', 'inline-block')">
                            <fmt:message key="admin.form.newAuthor" />
                        </button>
                    </div>
                    <small class="ui-state-error-text">${idAuthorError}</small>
                    <div class="form-group d-flex flex-row">
                        <input type="text" name="price" value="${editedEdition.price}"
                               placeholder="<fmt:message key="admin.form.price" />">
                        <select class="form-select" name="measurement" aria-label="Default select example" required>
                            <option selected disabled><fmt:message key="admin.form.measurement" /></option>
                            <c:forEach items="${Money.values()}" var="meas">
                                <option value="${meas}"
                                        <c:if test="${meas == editedEdition.measurement}">selected</c:if>>
                                        ${meas}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <small class="ui-state-error-text">${priceError}</small>
                    <small class="ui-state-error-text">${addingEditionError}</small>
                    <input type="hidden" name="id" value="${editedEdition.id}">
                    <button type="submit" class="btn btn-outline-success d-inline-block">
                        <fmt:message key="admin.form.saveChanges" /></button>
                </form>
            </div>

            <div class="col-5">
                <div class="row">
                    <div id="form_new_genre" style="display: none" class="col-12" >
                        <form method="post" action="${Path.ADMIN_SAVE_NEW_GENRE}" class="col-12 inputAdmin">
                            <small class="ui-state-highlight">${genreAddedSuccess}</small>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newGenre_uk"
                                       placeholder="<fmt:message key="admin.form.newGenre.uk" />" required>
                            </div>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newGenre_en"
                                       placeholder="<fmt:message key="admin.form.newGenre.en" />" required>
                            </div>
                            <small class="ui-state-error-text">${addingGenreError}</small>
                            <small class="ui-state-error-text">${nameEnError}</small>
                            <small class="ui-state-error-text">${nameUkError}</small>
                            <button type="submit" class="btn btn-outline-success d-inline-block">
                                <fmt:message key="admin.form.saveNewGenre" />
                            </button>
                            <button type="button" class="btn btn-outline-success d-inline-block"
                                    onclick="check('new_genre', 'form_new_genre', 'inline-block', 'none')">X</button>
                        </form>
                    </div>


                    <div id="form_new_author" style="display: none" class="col-12" >
                        <form method="post" action="${Path.ADMIN_SAVE_NEW_AUTHOR}" class="col-12 inputAdmin" >
                            <small class="ui-state-highlight">${authorAddedSuccess}</small>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newAuthor_uk"
                                       placeholder="<fmt:message key="admin.form.newAuthor.uk" />" required>
                            </div>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newAuthor_en"
                                       placeholder="<fmt:message key="admin.form.newAuthor.en" />" required>
                            </div>
                            <small class="ui-state-error-text">${addingAuthorError}</small>
                            <small class="ui-state-error-text">${nameEnAuthorError}</small>
                            <small class="ui-state-error-text">${nameUkAuthorError}</small>
                            <button type="submit" class="btn btn-outline-success d-inline-block">
                                <fmt:message key="admin.form.saveNewAuthor" />
                            </button>
                            <button type="button" class="btn btn-outline-success d-inline-block "
                                    onclick="check('new_author', 'form_new_author', 'inline-block', 'none')">X</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function check(actual, change, actual_val, change_val){
        document.getElementById(actual).style.display = actual_val;
        document.getElementById(change).style.display = change_val;
    }
</script>
</body>
</html>
