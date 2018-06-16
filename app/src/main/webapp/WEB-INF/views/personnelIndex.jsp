<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html>
    <div>
    <head><title><spring:message code="label.${pact}"/></title></head>

    <body>
        <h2 style="display: inline-block;"><spring:message code="label.${pact}"/></h2> 
        <div style="float: right;display: inline-block;"> 
            <a href="/personnelManager?lang=en">[EN]</a>
            <a href="/personnelManager?lang=wry">[WRY]</a>
        </div>
         <hr>
        <form style="float:right;" action="/addPersonnel">
            <button type="submit" >
                <spring:message code="label.addp"/>
            </button>
        </form>
        

        <form method="POST"  commandName="uploadFile" enctype="multipart/form-data">
            Upload Personnel From File: <input type="file" name="file"/>
            <button type="submit" name="submit">Upload</button>${fileerror}
        </form>

        <br>

        <form action="/personnelManager">
            <input type="text" name="nameFilter" placeholder="Search by name" value="${param.nameFilter}"/>
            <input type="submit" value="Search">
        </form>

    </br>

    </div>

        <div style="overflow-x:auto;">
            <table border = "1" style="width:100%;white-space: nowrap;">
                <tr bgcolor = "#D3D3D3">
                    <th><a href =  "/personnelManager?sortby=<c:out value='id'/>">ID</a></th>
                    <th><a href =  "/personnelManager?sortby=<c:out value='name'/>"><spring:message code="label.name"/></a></th>
                    <th><a href =  "/personnelManager?sortby=<c:out value='address'/>"><spring:message code="label.address"/></a></th>
                    <th><a href =  "/personnelManager?sortby=<c:out value='bday'/>"><spring:message code="label.birthday"/></a></th>
                    <th><a href =  "/personnelManager?sortby=<c:out value='gwa'/>">GWA</a></th>
                    <th><a href =  "/personnelManager?sortby=<c:out value='datehired'/>"><spring:message code="label.dateHired"/></a></th>
                    <th><spring:message code="label.contact"/></th>
                    <th><spring:message code="label.roles"/></th>
                    <th><spring:message code="label.update"/></th>
                    <th><spring:message code="label.delete"/></th>
                </tr>

                <c:forEach items = "${personnelList}" var = "p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name.lname}, ${p.name.fname} ${p.name.mname}</td>
                        <td>${p.address.brgy}, ${p.address.city}</td>
                        <td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${p.birthday}" /></td>
                        <td>${p.gwa}</td>
                        <td><fmt:formatDate pattern = "dd/MM/yyyy" value = "${p.dateHired}" /></td>
                        <td>
                            <c:forEach items = "${p.contact}" var = "c">
                                ${c.contactType}: ${c.contactDetails} </br>
                            </c:forEach>
                        <td>
                            <c:forEach items = "${p.roles}" var = "r">
                                &bull; ${r.role}</br>
                            </c:forEach>
                        </td>
                        <td>
                            <a href = "/updatePersonnel?pid=<c:out value='${p.id}'/>" ><spring:message code="label.updatep"/></a>
                        </td>
                        <td>
                            <a href = "/deletePersonnel?pid=<c:out value='${p.id}'/>" ><spring:message code="label.deletep"/></a>
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