<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="page" value="logIn" scope="request"/>
<%@ page import="manegers.Path" %>
<c:import url="/WEB-INF/views/head.jsp"/>
<jsp:include page="/WEB-INF/views/header.jsp"/>


<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form method="post" action="/Controller?command=logIn">
                    <div class="logo">
                        <img src="/resources/imgs/favicon.png" alt="favicon">
                    </div>
                    <small class="ui-state-error-text">${log_inError}</small>
                    <small class="ui-state-error-text">${blockedUserError}</small>

                    <div class="form-group">
                        <input type="text" class="form-control" name="login" placeholder="<fmt:message key="form.login"/>" required>
                        <small class="ui-state-error-text">${loginError}</small>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="pass" placeholder="<fmt:message key="form.pass"/>" required>
                        <small class="ui-state-error-text">${passInputError}</small>

                    </div>
                    <br>

                    <button type="submit" class="btn btn-button"><fmt:message key="header.logIn"/></button>
                    <a href="${Path.SIGN_UP}"><fmt:message key="form.href.signUp"/></a>

                </form>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/views/footer.jsp"/>