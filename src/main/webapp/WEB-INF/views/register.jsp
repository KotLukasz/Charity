<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header>

    <%@ include file="header.jspf" %>

</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form modelAttribute="ViewMode" >
        <div class="form-group">
            <form:input  name="firstName" placeholder="Imię" path="firstName"/>
        </div>
        <div class="form-group">
            <form:input  name="lastName" placeholder="Nazwisko" path="lastName"/>
        </div>
        <div class="form-group">
            <form:input  name="email" placeholder="Email" path="email"/>
        </div>
        <div class="form-group">
            <form:password  name="password" placeholder="Hasło" path="password"/>
        </div>
        <div class="form-group">
            <input name="password2" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>"  class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="footer.jspf" %>