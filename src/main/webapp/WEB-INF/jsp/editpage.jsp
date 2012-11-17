<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Company</title>
</head>
<body>
 
<h1>Edit Company</h1>
 
<c:url var="saveUrl" value="/company/companies/edit/id/${companyAttribute.id}" />
<form:form modelAttribute="companyAttribute" method="POST" action="${saveUrl}">
 <table>
  <tr>
   <td><form:label path="id">Id:</form:label></td>
   <td><form:input path="id" disabled="true"/></td>
  </tr>
  
  <tr>
   <td><form:label path="companyId">Company ID:</form:label></td>
   <td><form:input path="companyId"/></td>
  </tr>
 
  <tr>
   <td><form:label path="companyName">Company Name:</form:label></td>
   <td><form:input path="companyName"/></td>
  </tr>

  <tr>
   <td><form:label path="companyEmail">Company Email:</form:label></td>
   <td><form:input path="companyEmail"/></td>
  </tr>
  
  <tr>
   <td><form:label path="username">Username:</form:label></td>
   <td><form:input path="username"/></td>
  </tr>
  
  <tr>
   <td><form:label path="password">Password:</form:label></td>
   <td><form:input path="password"/></td>
  </tr>
 </table>
  
 <input type="submit" value="Save" />
</form:form>
 
</body>
</html>