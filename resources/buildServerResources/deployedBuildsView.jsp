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

                <tr><td></td>
                <c:forEach items="${tags}" var="tag">
                    <td><b>${tag}</b></td>
                </c:forEach>
                <tr>

                <c:forEach items="${revisions}" var="build">
                    <tr>
                        <td align="left">${build.value.projectConfiguration}</td>
                        <td>${build.value.revision}</td>
                        <td>X</td>
                        <td>X</td>
                    </tr>
                </c:forEach>

            </table>

        </div>

    </body>
</html>