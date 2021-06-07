<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="uk"/>
<fmt:setBundle basename="locales.locale"/>
<html>
<head>
    <title>fmt:setBundle Tag</title>
</head>
<body>

    <fmt:message key="header.uk"/><br/>
    <fmt:message key="header.en"/><br/>
    <fmt:message key="header.title.name"/><br/>
</html>