<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>

<html>
    <div>
    <head>
        <title><spring:message code="label.${pact}"/></title>
    </head>

    <body>
        <h2 style="display: inline-block;"><spring:message code="label.${pact}"/></h2> 
        <div style="float: right;display: inline-block;"> 
            <a href="/listProject?lang=en">[EN]</a>
            <a href="/listProject?lang=wry">[WRY]</a>
        </div>
         <hr>
        <form action="/addProject">
            <button type="submit"><spring:message code="label.addj"/></button>
        </form>
    </div>

        <div style="overflow-x:auto;">
            <table border = "1" style="width:100%;white-space: nowrap;">
                <tr bgcolor = "#D3D3D3">
                    <th><a href =  "/listProject?sortby=<c:out value='id'/>">ID</a></th>
                    <th><a href =  "/listProject?sortby=<c:out value='name'/>"><spring:message code="label.pname"/></a></th>
                    <th><a href =  "/listProject?sortby=<c:out value='start'/>"><spring:message code="label.psdate"/></a></th>
                    <th><a href =  "/listProject?sortby=<c:out value='end'/>"><spring:message code="label.pedate"/></a></th>
                    <th><spring:message code="label.personnel"/></th>
                    <th><spring:message code="label.update"/></th>
                    <th><spring:message code="label.delete"/></th>
                </tr>

                <c:forEach items = "${projectList}" var = "p">
                    <tr>
                        <td>${p.projectId}</td>
                        <td>${p.projectName}</td>
                        <td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${p.startDate}" /></td>
                        <td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${p.endDate}" /></td>
                        <td>
                            <c:forEach items = "${p.personnel}" var = "per">
                                &bull; ${per.name.lname}, ${per.name.fname} ${per.name.mname}</br>
                            </c:forEach>
                        </td>
                        <td>
                             <a href = "/updateProject?pid=<c:out value='${p.projectId}'/>" ><spring:message code="label.updatej"/></a>
                        </td>
                        <td>
                             <a href = "/deleteProject?pid=<c:out value='${p.projectId}'/>" ><spring:message code="label.deletej"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
         <form action="/">
            <button type="submit"><spring:message code="label.back"/></button>
        </form>
    </body>
</html>