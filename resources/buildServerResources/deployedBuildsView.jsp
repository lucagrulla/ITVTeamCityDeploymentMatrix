<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>
    <head>
       <link rel="stylesheet" href="${teamcityPluginResourcesPath}/matrix.css" type="text/css" media="all" /> 
    </head>
    <body>
          
         <c:forEach items="${data}" var="env" >
             <div class="env">
              <p class="env-description">${env.key}<p>
              <p class="build-name">${env.value.buildName}<p>
              <p class="build">Build: ${env.value.buildNumber}</p>
              <p class="finish-date">the deployed build is from:${env.value.finishDate}</p>
             <!--  in progress
              <a href="${teamcityPluginResourcesPath}/changeset.jsp?buildId=${env.value.buildId}&buildNumber=${env.value.buildNumber}">changeset</a>
            -->
            </div>
         </c:forEach>
    </body>
</html>
