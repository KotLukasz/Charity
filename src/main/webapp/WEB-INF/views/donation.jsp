<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Charity</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

<header class="header--form-page">
    <jsp:include page="header.jsp" />

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<body>
<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>


        <!-- STEP 1: class .active is switching steps -->
        <form:form modelAttribute="donation" method="post" id="form">
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>
                <div>
                    <form:errors path="categories"/>
                    <label>
                        <form:checkboxes path="categories" class="form-group form-group--checkbox"
                                         items="${listCategory}" itemValue="id" itemLabel="name" id="categoriesForm"/>
                    </label>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step" class="buttonStepForm">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <form:errors path="quantity"/>
                    <label>
                        <form:input path="quantity" id="quantityForm" name="quantityForm"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" id="buttonStepForm2">Dalej</button>
                </div>
            </div>


            <!-- STEP 4 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <form:errors path="institution"/>
                <c:forEach items="${listInstitutions}" var="temp">
                <span class="description">
                  <div class="title">${temp.name}</div>
                  </span>
                    <form:radiobutton class="form-group form-group--checkbox" path="institution" id="institutionForm" name="institutionFormName" itemValue="id" value="${temp}"/>
                </c:forEach>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" class="buttonStepForm">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <form:errors path="street"/>
                        <div class="form-group form-group--inline">
                            <label> Ulica <form:input path="street" id="streetForm"/> </label>
                        </div>
                        <form:errors path="city"/>
                        <div class="form-group form-group--inline">
                            <label> Miasto <form:input path="city" id="cityForm"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:errors path="zipCode"/>
                            <label>
                                Kod pocztowy <form:input path="zipCode" id="zipCodeForm"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <form:errors path="telephone"/>
                            <label>
                                Numer telefonu <form:input path="telephone" type="phone" id="telephoneForm"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <form:errors path="pickUpDate"/>
                        <div class="form-group form-group--inline">
                            <label> Data <form:input type="date" path="pickUpDate" id="dateForm"/> </label>
                        </div>
                        <form:errors path="pickUpTime"/>
                        <div class="form-group form-group--inline">
                            <label> Godzina <form:input type="time" path="pickUpTime" id="timeForm"/></label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <form:textarea path="pickUpComment" rows="5" id="descriptionForm"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" class="buttonStepForm">Dalej</button>
                </div>
            </div>

            <!-- STEP 6 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text"
                                      id="quantityFormCompleted"></span
                                >
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="institutionFormCompleted"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="streetFormCompleted"></li>
                                <li id="cityFormCompleted"></li>
                                <li id="zipCodeFormCompleted"></li>
                                <li id="telephoneFormCompleted"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="dateFormCompleted"></li>
                                <li id="timeFormCompleted"></li>
                                <li id="descriptionFormCompleted"></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button id="submitForm" type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form:form>
    </div>
</section>


<jsp:include page="footer.jsp" />

<script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>

</body>
</html>
