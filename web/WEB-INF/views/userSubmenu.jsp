<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="manegers.Path" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locales.locale" var="lang"/>


<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="subMenu userMenu">
                <ul>
                    <a class="nav-link" href="${Path.USER_EDITION_PAGE}">    <li class="nav-item active"><fmt:message key="user.submenu.myEditions" /></li></a>
                    <a class="nav-link" href="${Path.USER_CART_PAGE}">    <li class="nav-item active"><fmt:message key="user.submenu.myBasket" />
                    </li></a>

                    <a class="nav-link" href="#"  tabindex="-1" aria-disabled="true">
                        <li class="nav-item">
                            <fmt:message key="user.submenu.myBalance" /> ${user.balance}
                        </li>
                    </a>
                    <button class="btn btn-outline-success" type="button" id="add_button"
                            onclick="check('add_button', 'from_add_money','none', 'inline-block')"><fmt:message key="user.submenu.addMoney.button" /></button>
                    <a style = "display: none; "id = "from_add_money"><li>
                        <form action="${Path.USER_UPDATE_BALANCE}" method="post">
                            <input type="text" name="newBalance">
                            <input type="hidden" name="idUser" value="${user.id}">
                            <button class="btn btn-outline-success" type="submit" ><fmt:message key="user.submenu.addMoney.submit" /></button>
                            <button class="btn btn-outline-success" type="button" onclick="check('add_button', 'from_add_money', 'inline-block','none')">X</button>
                        </form>
                    </li></a>
                    <a class="nav-link" href="${Path.MAIN_PAGE}">    <li class="nav-item active">   <fmt:message key="user.submenu.mainPage" /></li></a>
                </ul>
                <c:if test="${user.role == 'admin'}">
                <ul>
                    <a class="nav-link" href="${Path.ADMIN_USER_LIST}&page=1">   <li class="nav-item active"><fmt:message key="admin.submenu.users" /></li></a>
                    <a class="nav-link" href="${Path.ADMIN_NEW_EDITION}">   <li class="nav-item active"><fmt:message key="admin.submenu.newEdition" /></li></a>
                </ul>
                </c:if>
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