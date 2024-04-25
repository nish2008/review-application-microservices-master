<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="stag" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello "${username}"!</title>
</head>
<body>
    <h2>Below are the list of products</h2>
      <table>
      <tr>
         <th>Products</th>
         <th>Read review</th>
         <th>Write review</th>
         </tr>

         <c:forEach var = "product" items ="${list}">
         <tr>
         <td>
         <c:out value="${product.productName}"/>
         </td>
         <td><a href="/user/writereview?id=${product.id}">Write review</a></td>
                  <td><a href="/user/readreview?id=${product.id}">Read review</a></td>
         </tr>
          </c:forEach>
         </table>

</body>
</html>