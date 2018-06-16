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
            <!-- add/update toggle lang -->
            <a href="/${url}lang=en">[EN]</a>
            <a href="/${url}lang=wry">[WRY]</a>
        </div>
        <hr/>
        <br>
        <form:form method="POST" commandName="project">

            <spring:message code="label.pname"/>:
            <form:input path="projectName" value = "${personnel.name.fname}"/>
            <form:errors class="error" path="projectName"/><br/>

            <spring:message code="label.psdate"/>:
            <form:input path="startDate" type="date" value = "${personnel.birthday}"/>
            <form:errors class="error" path="startDate"/><br/>
            
            <spring:message code="label.pedate"/>:
            <form:input path="endDate" type="date" value = "${personnel.dateHired}"/>
            <form:errors class="error" path="endDate"/><br/>

            <br>
            <spring:message code="label.personnel"/>:</br><br>

            <div style=" width: 45%;float: left;margin-left:2%;"><spring:message code="label.available"/>
                <select multiple="multiple" id='lstBox1' name="available" style="height: 200px; width: 100%;">
                    <c:forEach items="${personnelList}" var="p">
                        <option value="${p.id}">${p.name.lname}, ${p.name.fname} ${p.name.mname}</option>
                    </c:forEach>>
                </select>
            </div>
            <div style=" width: 6%;float: left;margin-top:20px;">
                  <input type="button" value=">>" id="btnAllRight" style=" width: 100%;height: 50px;"/><br />
                  <input type="button" value=">" id="btnRight" style=" width: 100%;height: 50px;"/><br />
                  <input type="button" value="<" id="btnLeft" style=" width: 100%;height: 50px;"/><br />
                  <input type="button" value="<<" id="btnAllLeft" style=" width: 100%;height: 50px;"/>
            </div>
            <div style=" width: 45%;float: left;margin-right:2%;"><spring:message code="label.assigned"/>
                <select multiple="multiple" id='lstBox2' name="lstBox2" style="height: 200px; width: 100%;">
                    <c:forEach items="${assigned}" var="p">
                        <option value="${p.id}">${p.name.lname}, ${p.name.fname} ${p.name.mname}</option>
                    </c:forEach>>
                </select>
             <br> <br>  <br>
            </div>

            

            <br/>
            <input type = "hidden" name="addOrUpdate" value="${pact}">
            <input type = "hidden" name="pid" value="${personnel.id}">
            <input type = "submit" id="submitbutton" value = '<spring:message code="label.submit"/>'>
        </form:form>
         <form action="/listProject">
            <button type="submit"><spring:message code="label.back"/></button>
        </form>

        <script>
                    $('#btnRight').click(function () {
                        var selectedOpts = $('#lstBox1 option:selected');
                        $('#lstBox2').append($(selectedOpts).clone());
                        $(selectedOpts).remove();
                    });
                    $('#btnAllRight').click(function () {
                        var selectedOpts = $('#lstBox1 option');
                        $('#lstBox2').append($(selectedOpts).clone());
                        $(selectedOpts).remove();
                    });
                    $('#btnLeft').click(function () {
                        var selectedOpts = $('#lstBox2 option:selected');
                        $('#lstBox1').append($(selectedOpts).clone());
                        $(selectedOpts).remove();
                    });
                    $('#btnAllLeft').click(function () {
                        var selectedOpts = $('#lstBox2 option');
                        $('#lstBox1').append($(selectedOpts).clone());
                        $(selectedOpts).remove();
                    });
                    $('#submitbutton').click(function () {
                        $('#lstBox2 option').prop('selected', true);
                    });
            </script>
    </body>
</html>