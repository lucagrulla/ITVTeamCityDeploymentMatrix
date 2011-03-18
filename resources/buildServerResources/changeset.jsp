<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>

          <h2>Build #${buildNumber}</h1>

          <ol>
         <c:forEach items="${data}" var="description" >
               <li class="comment">${description}</li>
         </c:forEach>
         </ol>
</html>