<%@ include file="/include.jsp" %>
<%@ page import="itv.*" %>

<html>
    <head>
       <link rel="stylesheet" type="text/css" href="/plugins/pipeline/main.css" />
    </head>
    <body>
        <div id="mainContent">
            <h1>The Matrix</h1>
            <table border="1" class="testList historyList dark borderBottom">
                 <c:forEach items="${data}" var="row">
                    <tr>
                        <c:forEach items="${row}" var="cell">
                            <td><b>${cell}</b></td>
                        </c:forEach>
                    <tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>