<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="manegers.Path" %>
<%@ page isELIgnored="false"%>

<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locales.locale" scope="session"/>


<header>
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="subMenu helperMenu">
                    <ul class="language">
                        <a href="/Controller?command=switchLang&lang=en&page=${page}"><li class="lang"><fmt:message key="header.en" /></li></a>
                        <a href="/Controller?command=switchLang&lang=uk&page=${page}"><li class="lang"><fmt:message key="header.uk" /></li></a>
                    </ul>
                    <ul class="right language">
                        <c:if test="${user == null}">
                            <a href="${Path.LOG_IN}">
                                <li class="lang lang_active"> <fmt:message key="header.logIn" />/<fmt:message key="header.signUp" /></li>
                            </a>
                        </c:if>
                        <c:if test="${user != null}">
                            <a href="/Controller?command=logOut">
                                <li class="lang lang_active"><fmt:message key="header.logOut" /></li>
                            </a>
                        </c:if>
                    </ul>
                </div>
                <div class="title">
                    <a href="${Path.MAIN_PAGE}">
                        <div class="logo">
                            <img src="../../resources/imgs/favicon.png" alt="favicon">
                        </div>
                        <fmt:message key="header.title.name" />
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>

<script type="text/javascript">
    function changeActive(active, nonActive) {
        console.log("inside function");
        console.log(active);
        console.log(nonActive);

        var element = document.getElementById(active);

        element.classList.add("land_active");

        element = document.getElementById(nonActive);
        element.classList.remove("land_active");
    }
</script>