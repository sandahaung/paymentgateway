<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Company</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script>
            $(document).ready(function(){                
                $("#username").blur(function(){
                    $("#usernameSpan").text("");
                    var urlVar = "/paymentgateway/company/verify/username/" + $("#username").val();                                        
                    $.ajax({
                        url: urlVar,
                        dataType: "json",
                        type: "get",
                        success: function(data) {                            
                            if (data.exists == true)
                                $("#usernameSpan").text(data.message);
                        },
                        error:function (xhr, ajaxOptions, thrownError){
                            alert(xhr.status);
                            alert(thrownError);
                        } 
                    });
                });
            });
        </script>
    </head>
    <body>

        <h1>Create New Company</h1>

        <c:url var="saveUrl" value="/company/companies/add" />
        <form:form modelAttribute="companyAttribute" method="POST" action="${saveUrl}">
            <table>
                <tr>
                    <td><form:label path="companyId">Company ID:</form:label></td>
                    <td><form:input path="companyId"/></td>
                    <td><span id="companyIdSpan" /></td>
                </tr>

                <tr>
                    <td><form:label path="companyName">Company Name:</form:label></td>
                    <td><form:input path="companyName"/></td>
                    <td><span id="companyNameSpan" /></td>
                </tr>

                <tr>
                    <td><form:label path="companyEmail">Company Email:</form:label></td>
                    <td><form:input path="companyEmail"/></td>
                    <td><span id="companyEmailSpan" /></td>
                </tr>

                <tr>
                    <td><form:label path="username">Username:</form:label></td>
                    <td><form:input path="username" id="username" /></td>
                    <td><span id="usernameSpan" /></td>
                </tr>

                <tr>
                    <td><form:label path="password">Password:</form:label></td>
                    <td><form:input path="password"/></td>
                    <td><span id="passwordSpan" /></td>
                </tr>
            </table>

            <input type="submit" value="Save" />
        </form:form>

    </body>
</html>