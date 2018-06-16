<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<div>
  <title><spring:message code="label.title"/></title>
  <body>
    <h1 style="display: inline-block;"><spring:message code="label.title"/></h1>
    <div style="float: right;display: inline-block;"> 
        <a href="/?lang=en">[EN]</a>
        <a href="/?lang=wry">[WRY]</a>
    </div>
</div>
    <hr>
    <h2>
      <a href = "/personnelManager">1. <spring:message code="label.manp"/></a> <br/>
      <a href = "/addRole">2. <spring:message code="label.manr"/></a> <br/>
      <a href = "/listProject">3. <spring:message code="label.manj"/></a> <br/>
  </h2>
  </body>
</html>