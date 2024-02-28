<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your review is valuable for us :-) </title>
</head>
<body>

    <form:form action="/postreview" method="post" modelAttribute="review" name="form">
        <table>
        <tr>
        <td>

            <c:out value="${reviewdesc}"/>
        <form:input type="text" path="reviewdesc" id="reviewdesc"/>
        </td>
        </tr>
        </table>
        <form:hidden path = "product.id" value = "${productId}"/>
        <p><input type="submit" value="postreview"/></p>
        </form:form>
</body>
</html>