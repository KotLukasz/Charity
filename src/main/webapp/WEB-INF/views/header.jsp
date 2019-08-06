<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize access="isAuthenticated()">
    <nav class="container container--70">
    <ul class="nav--actions">
        <li class="logged-user">
            Witaj ${customUser.firstName}!
            <ul class="dropdown">
                <li><a href="<c:url value="/user/edit/${customUser.id}"/>">Edycja danych</a></li>
                <li><a href="<c:url value="/user/donation/addDonation/${customUser.id}"/>">Dodaj zbórkę</a></li>
                <li><a href="<c:url value="/user/donation/donations/${customUser.id}"/>">Moje zbiórki</a></li>
                <li>
                    <form action="<c:url value="/logout"/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input class="fa fa-id-badge" type="submit" value="Wyloguj">
                    </form>
                </li>
            </ul>
        </li>
    </ul>
    </nav>
</sec:authorize>

<nav class="container container--70">
    <sec:authorize access="isAnonymous()">
    <ul class="nav--actions">
        <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>
</sec:authorize>
    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
        <li><a href="<c:url value="/"/>#stepsScroll" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="<c:url value="/"/>#aboutUsScroll" class="btn btn--without-border">O nas</a></li>
        <li><a href="<c:url value="/"/>#institutionsScroll" class="btn btn--without-border">Fundacje i organizacje</a>
        </li>
        <li><a href="#contactScroll" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>

