<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>
    <head>
       <link rel="stylesheet" href="${teamcityPluginResourcesPath}matrix.css" type="text/css" media="all" />
       <link rel="stylesheet" href="${teamcityPluginResourcesPath}fancybox/jquery.fancybox-1.3.4.css" type="text/css" media="screen" />

       <script type="text/javascript" src="${teamcityPluginResourcesPath}jquery-1.5.1.min.js"></script>
       <script type="text/javascript" src="${teamcityPluginResourcesPath}fancybox/jquery.fancybox-1.3.4.pack.js"></script>
       <script type="text/javascript" src="${teamcityPluginResourcesPath}deploymentDashboard.js"></script>
    </head>
    <body>

         <c:forEach items="${data}" var="env" >
             <div class="env">
              <p class="env-description">${env.key}<p>
              <p class="build">Build: ${env.value.buildNumber}</p>
              <p class="build-name">${env.value.buildName}<p>
              <p class="finish-date">build timestamp:${env.value.finishDate}</p>
              <div class="changeset">
                  <a class="openmodalbox" href="changeset.html?buildId=${env.value.buildId}&buildNumber=${env.value.buildNumber}">build comments</a>
              </div>
            </div>
         </c:forEach>

         <div class="ddd"></div>
    </body>
</html>
