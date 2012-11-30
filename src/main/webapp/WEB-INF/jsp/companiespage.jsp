<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Companies</title>
</head>
<body>
<h1>Companies</h1>
 
<c:url var="addUrl" value="/company/companies/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
 <thead style="background:#fcf">
  <tr>
   <th>Company ID</th>
   <th>Company Name</th>
   <th>Company Email</th>
   <th>User Name</th>
   <th>Enabled</th>
   <th colspan="3"></th>
  </tr>
 </thead>
 <tbody>
 <c:forEach items="${companies}" var="company">
   <c:url var="editUrl" value="/company/companies/edit/id/${company.id}" />
   <c:url var="deleteUrl" value="/company/companies/delete/id/${company.id}" />
  <tr>
   <td><c:out value="${company.companyId}" /></td>
   <td><c:out value="${company.companyName}" /></td>
   <td><c:out value="${company.companyEmail}" /></td>
   <td><c:out value="${company.username}" /></td>
   <td><c:out value="${company.enabled}" /></td>
   <td><a href="${editUrl}">Edit</a></td>
   <td><a href="${deleteUrl}">Delete</a></td>
   <td><a href="${addUrl}">Add</a></td>
  </tr>
 </c:forEach>
 </tbody>
</table>
 
<c:if test="${empty companies}">
 There are currently no companies in the list. <a href="${addUrl}">Add</a> a company.
</c:if>
 
</body>
</html>