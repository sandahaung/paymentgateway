<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Company Added</title>
</head>
<body>
 
<h1>Success</h1>
 
<p>You have added a new company.</p>
 
<c:url var="companyListUrl" value="/company/companies" />
<p>Return to <a href="${companyListUrl}">Company List</a></p>
</body>
</html>