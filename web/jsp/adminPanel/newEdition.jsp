<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="page" value="adminForm" scope="request"/>
<%@page import="manegers.Path" %>
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
                <form action="${Path.ADMIN_SAVE_NEW_EDITION}" class="inputAdmin" method="post">
                    <small class="ui-state-highlight">${editionAddedSuccess}</small>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title_uk" placeholder="Title UKR" required>

                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="title_en" placeholder="Title ENG" required>

                    </div>
                    <div class="form-group">
                        <input type="file" class="form-control" name="img">
                    </div>

                    <div class="form-group">
                        <textarea type="textarea" class="form-control" name="description_uk" placeholder="Description UKR"></textarea>
                    </div>

                    <div class="form-group">
                        <textarea type="textarea" class="form-control" name="description_en" placeholder="Description ENG"></textarea>
                    </div>


                    <div class="form-group d-flex flex-row">
                        <select class="form-select" name="id_genre" aria-label="Default select example" required>
                            <option selected disabled>Genre</option>
                            <option value="0">Undefined</option>
                            <c:forEach items="${adminPanelGenre}" var="genre">
                                <option value="${genre.id}">${genre}</option>
                            </c:forEach>
                        </select>


                        <button class="btn btn-outline-success" id="new_genre" type="button" onclick="check('new_genre', 'form_new_genre', 'none', 'inline-block')">New genre</button>
                    </div>

                    <small class="ui-state-error-text">${idGenreError}</small>

                    <div class="form-group d-flex flex-row">
                        <select class="form-select" name="id_author" aria-label="Default select example" required>
                            <option selected disabled>Author</option>
                            <option value="0">Undefined</option>
                            <c:forEach items="${adminPanelAuthors}" var="author">
                                <option value="${author.id}">${author}</option>
                            </c:forEach>

                        </select>
                        <button class="btn btn-outline-success" id="new_author" type="button" onclick="check('new_author', 'form_new_author', 'none', 'inline-block')">New Author</button>
                    </div>
                    <small class="ui-state-error-text">${idAuthorError}</small>
                    <div class="form-group d-flex flex-row">
                        <input type="text" name="price" placeholder="Price">
                        <select class="form-select" name="measurement" aria-label="Default select example" required>
                            <option selected disabled>Measurement</option>
                            <c:forEach items="${measurements}" var="meas">
                                <option value="${meas}">${meas}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <small class="ui-state-error-text">${priceError}</small>
                    <small class="ui-state-error-text">${addingEditionError}</small>
                    <button type="submit" class="btn btn-outline-success d-inline-block">Save new edition</button>
                </form>
            </div>

            <div class="col-5">
                <div class="row">
                    <div id="form_new_genre" style="display: none" class="col-12" >
                        <form method="post" action="${Path.ADMIN_SAVE_NEW_GENRE}" class="col-12 inputAdmin">
                            <small class="ui-state-highlight">${genreAddedSuccess}</small>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newGenre_uk" placeholder="Genre name UKR" required>
                            </div>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newGenre_en" placeholder="Genre name ENG" required>
                            </div>
                            <small class="ui-state-error-text">${addingGenreError}</small>
                            <small class="ui-state-error-text">${nameEnError}</small>
                            <small class="ui-state-error-text">${nameUkError}</small>
                            <button type="submit" class="btn btn-outline-success d-inline-block">Add genre</button>
                            <button type="button" class="btn btn-outline-success d-inline-block">List of all genre</button>
                            <button type="button" class="btn btn-outline-success d-inline-block" onclick="check('new_genre', 'form_new_genre', 'inline-block', 'none')">X</button>
                        </form>
                    </div>


                    <div id="form_new_author" style="display: none" class="col-12" >
                        <form method="post" action="${Path.ADMIN_SAVE_NEW_AUTHOR}" class="col-12 inputAdmin" >
                            <small class="ui-state-highlight">${authorAddedSuccess}</small>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newAuthor_uk" placeholder="Author name UKR" required>
                            </div>
                            <div class="form-group d-inline-block">
                                <input type="text" class="form-control" name="newAuthor_en" placeholder="Author name ENG" required>
                            </div>
                            <small class="ui-state-error-text">${addingAuthorError}</small>
                            <small class="ui-state-error-text">${nameEnAuthorError}</small>
                            <small class="ui-state-error-text">${nameUkAuthorError}</small>
                            <button type="submit" class="btn btn-outline-success d-inline-block">Add Author</button>
                            <button type="button" class="btn btn-outline-success d-inline-block">List of all authors</button>
                            <button type="button" class="btn btn-outline-success d-inline-block " onclick="check('new_author', 'form_new_author', 'inline-block', 'none')">X</button>
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
