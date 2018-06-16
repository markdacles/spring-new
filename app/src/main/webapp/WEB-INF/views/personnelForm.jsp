<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title><spring:message code="label.${pact}"/></title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    </head>

    <body>
        <h2 style="display: inline-block;"><spring:message code="label.${pact}"/></h2> 
        <div style="float: right;display: inline-block;"> 
            <a href="/${url}lang=en">[EN]</a>
            <a href="/${url}lang=wry">[WRY]</a>
        </div>
        <hr/>
        <br>
        <form:form method="POST" commandName="personnel">

            <spring:message code="label.fname"/>:
            <form:input path="name.fname" value = "${personnel.name.fname}"/>
            <form:errors class="error" path="name.fname"/><br/>

            <spring:message code="label.mname"/>:
            <form:input path="name.mname" value = "${personnel.name.mname}"/>
            <form:errors class="error" path="name.mname"/><br/>

            <spring:message code="label.lname"/>:
            <form:input path="name.lname" value = "${personnel.name.lname}"/>
            <form:errors class="error" path="name.lname"/><br/>

            <spring:message code="label.brgy"/>:
            <form:input path="address.brgy" value = "${personnel.address.brgy}"/>
            <form:errors class="error" path="address.brgy"/><br/>

            <spring:message code="label.city"/>:  
            <form:input path="address.city" value = "${personnel.address.city}"/>
            <form:errors class="error" path="address.city"/><br/>

            <spring:message code="label.birthday"/>:
            <form:input path="birthday" type="date" value = "${personnel.birthday}"/>
            <form:errors class="error" path="birthday"/><br/>
            
            GWA:
            <form:input path="gwa" value = "${personnel.gwa}"/>
            <form:errors class="error" path="gwa"/><br/>
            
            <spring:message code="label.dateHired"/>:
            <form:input path="dateHired" type="date" value = "${personnel.dateHired}"/>
            <form:errors class="error" path="dateHired"/><br/>

            Landline:
            <input type="button" value="+" id="addLandline"/> </br> 

            Mobile:
            <input type="button" value="+" id="addMobile"/> </br>

            Email:
            <input type="button" value="+" id="addEmail"/> <br>

            <br>
            <div id = "contactSection">
            <c:forEach items="${personnel.contact}" var="c">
                <div>
                    <input type="hidden" name="contactType" value="${c.contactType}"/>${c.contactType} : 
                        <input type="text" name="contactDetails" value="${c.contactDetails}" required/><input type='button' id='remove-button' value='-'><br>
                </div>
            </c:forEach>
            </div>

            <script>
                $("#addLandline").click(function() {
                    $("#contactSection").append('<div>' + '<input type="hidden" name="contactType" value="Landline"/>Landline : ' + '<input type="text" name="contactDetails" required/>' + "<input type='button' id='remove-button' value='-'><br></div>");
                });

                $("#addMobile").click(function() {
                       $("#contactSection").append('<div>' + '<input type="hidden" name="contactType" value="Mobile"/>Mobile : ' + '<input type="text" name="contactDetails" required/>' +  "<input type='button' id='remove-button' value='-'><br></div>");
                });

                $("#addEmail").click(function() {
                   $("#contactSection").append('<div>' + '<input type="hidden" name="contactType" value="Email"/>Email : ' + '<input type="text" name="contactDetails" required/>' +  "<input type='button' id='remove-button' value='-'><br></div>");
                });

                $(document).on('click', '#remove-button', function() {
                        $(this).closest("div").remove();
                });
            </script> 

            <br>
            <spring:message code="label.roles"/>:</br>

                <c:forEach items="${roleList}" var="r">
                    <c:set var="checked" value="${personnel.roles.contains(r) ? 'checked' : ''}"/>
                    <input type="checkbox" name="checkedRoles" value="${r.roleId}" ${checked}/>${r.role}<br/>
                </c:forEach>
            <br/>
            <input type = "hidden" name="addOrUpdate" value="${pact}">
            <input type = "hidden" name="pid" value="${personnel.id}">
            <input type = "submit" value = '<spring:message code="label.submit"/>'>
        </form:form>
        <br>
         <form action="/personnelManager">
            <button type="submit"><spring:message code="label.back"/></button>
        </form>
    </body>
</html>