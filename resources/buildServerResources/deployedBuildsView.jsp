<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>
    <head>
       <link rel="stylesheet" type="text/css" href="/plugins/pipeline/main.css" />
       <link rel="stylesheet" href="<%= request.getContextPath()%>matrix.css" type="text/css" media="all" /> 
    </head>
    <body>
          
         <c:forEach items="${data}" var="env" >
             <div class="env">
              <p class="env-description">${env.key}<p>
              <p class="build">Build: ${env.value.buildNumber}</p>
              <p class="finish-date">build finished:${env.value.finishDate}</p>
            </div>
         </c:forEach>
    </body>
</html>

<style type="text/css">
<!--

.env {
		border-radius: 15px;
			border:1px solid;
			width:30%;
			background-color:orange;
			float:left;
			margin-left:30px;
			box-shadow:10px 10px 20px #000;
}

.env-description {
    font-weight:bold;
	font-size:30px;
	text-align:center;
}

.finish-date {
    text-align:center;
}

.build{
	text-align:center;
	font-weight:bold;
}
-->
</style>