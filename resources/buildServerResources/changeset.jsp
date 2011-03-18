<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>

          <h1>Changeset for build ${buildNumber}</h1>

         <c:forEach items="${data}" var="description" >
            <div class="comment">
                <p>${description}</p>
            </div>
         </c:forEach>
</html>