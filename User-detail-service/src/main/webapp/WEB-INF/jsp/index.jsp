<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Review Application!!</title>
</head>
<body>
    <h2>Please provide your name and email-id to continue</h2>
    <form:form action="/user/continue" method="post" modelAttribute="userdetails" name="form">
        <table ><tr>
         <th>Name</th>
        <th>Email</th>
</tr>
        <tr>
        <td><form:input type="text" path="name" id="name"/></td>
        <td><form:input type="text" path="email" id="email"/></td>

        </tr>
        </table>

        <p><input type="submit" value="continue"/></p>
        </form:form>
</body>
</html>