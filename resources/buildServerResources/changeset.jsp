<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>

         <c:forEach items="${data}" var="description" >
            <div>
                <p>${description}</p>
            </div>
         </c:forEach>
</html>