<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>


<html>
     <head>
        <title><spring:message code="label.uprole"/></title>
    </head>

    <body>
        <h2 style="display: inline-block;"><spring:message code="label.uprole"/></h2> 
        <div style="float: right;display: inline-block;"> 
            <a href="/updateRole?roleid=${roleid}&lang=en">[EN]</a>
            <a href="/updateRole?roleid=${roleid}&lang=wry">[WRY]</a>
        </div>
         <hr/>
        <div>
            <form:form method="POST" commandName="roles">
            <table border = "1" width = "50%" id="roleTable" align="center">
                <col width="65%">
                <col width="15%">
                <tr bgcolor = "#D3D3D3">
                    <th><spring:message code="label.rolename"/></a></th>
                    <th><spring:message code="label.action"/></th>
                </tr>
                <tr>
                    <td>
                        <form:input path="role" value = "${r.role}" style="font-size:30;height:50;width:100%;"/>
                        <form:errors class="error" path="role"/>
                    </td>
                    <input type="hidden" value="${r.roleId}" name="roleid"/>
                    <td align="center">
                            <input style="width:100%;" type="submit" value="<spring:message code='label.save'/>"/>
                            <a href = "/deleteRole?roleid=<c:out value='${roleid}'/>"> <button type="button" style="width:100%;"><spring:message code="label.delete"/></button></a>
                        </td>
                </tr>
            </table>
            </form:form>
        </div>
        <br>

        <form action="/addRole">
            <button type="submit"><spring:message code="label.back"/></button>
        </form>


    </body>
</html>