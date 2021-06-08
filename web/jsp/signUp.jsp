<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ page import="manegers.Path" %>
<c:set var="page" value="${Path.SIGN_UP}" scope="request"/>
<c:import url="/WEB-INF/views/head.jsp"/>
<jsp:include page="/WEB-INF/views/header.jsp"/>


<div class="main">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <form method="post" action="/Controller?command=signUp">
                    <div class="logo">
                        <img src="${pageContext.request.contextPath}/resources/imgs/favicon.png" alt="favicon">
                    </div>
                    <input type="hidden" name="command" value="signUp">
                    <div class="form-group d-inline-flex">
                        <input type="text" class="form-control between" name="name" placeholder="<fmt:message key="form.name"/>" required>
                        <small class="ui-state-error-text">${nameError}</small>

                        <input type="text" class="form-control" name="surname" placeholder="<fmt:message key="form.surname"/>" required>
                        <small class="ui-state-error-text">${surnameError}</small>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="<fmt:message key="form.email"/>" required>
                        <small class="error">${emailError}</small>
                    </div>
                    <div class="form-group" >
                        <input type="text" class="form-control" name="login" placeholder="<fmt:message key="form.login"/>" required>
                        <small class="error">${loginError}</small>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="pass" placeholder="<fmt:message key="form.pass"/>" required>
                        <small class="error">${passInputError}</small>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="pass_rep" placeholder="<fmt:message key="form.repPass"/>" required>
                        <small class="error">${passEqualError}</small>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-button"><fmt:message key="header.signUp"/></button>
                    <a href="${Path.LOG_IN}"><fmt:message key="form.href.logIn"/></a>
                </form>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"/>