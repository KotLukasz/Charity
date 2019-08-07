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
    <title>Charity</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>

<header class="header--form-page">
    <jsp:include page="../header.jsp"/>
    <div class="slogan container container--90">
        <table border="1" style="font-size: 12px">
            <thead>
            <tr>
                <th>Quantity</th>
                <th>Categories</th>
                <th>Institution</th>
                <th>Street</th>
                <th>City</th>
                <th>Zip Code</th>
                <th>Telephone</th>
                <th>PickUp Date</th>
                <th>PickUp Time</th>
                <th>PickUp Comment</th>
                <th>Is Picked Up?</th>
                <th>PickedUp Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${donations}" var="temp">
                <tr>
                    <td>${temp.quantity}</td>
                    <td>
                        <c:forEach items="${temp.categories}" var="category">
                            ${category.name},
                        </c:forEach>
                    </td>
                    <td>${temp.institution.name}</td>
                    <td>${temp.street}</td>
                    <td>${temp.city}</td>
                    <td>${temp.zipCode}</td>
                    <td>${temp.telephone}</td>
                    <td>${temp.pickUpDate}</td>
                    <td>${temp.pickUpTime}</td>
                    <td>${temp.pickUpComment}</td>
                    <c:if test="${temp.pickedUp == true}">
                        <td>Odebrane</td>
                        <td>${temp.pickedUpDate}</td>
                    </c:if>

                    <c:if test="${temp.pickedUp == false}">
                        <form:form method="post" modelAttribute="donationPickUp"
                                   action="/user/donation/donations/${temp.user.id}/${temp.id}">
                            <td>
                                Paczka nieodebrana </br>
                                Zaznacz w celu zapisania odebrania paczki <form:checkbox path="pickedUp"/>
                            </td>
                            <td>
                                <form:errors path="pickedUpDate"/>
                                <form:input id="datePickUp" type="date" path="pickedUpDate"/>
                            </td>
                            <td>
                                <button id="btnDelivery" type="submit">Zapisz datę odbioru</button>
                            </td>
                        </form:form>
                    </c:if>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</header>

<jsp:include page="../footer.jsp"/>

<script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>
