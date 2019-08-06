<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Charity</title>
    <link rel="stylesheet"  type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>

<header class="header--form-page">
    <jsp:include page="../header.jsp" />

    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza</br> Na maila prześlemy wszelkie
            informacje o odbiorze.
        </h2>
    </div>
</header>

<jsp:include page="../footer.jsp" />

    <script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>
