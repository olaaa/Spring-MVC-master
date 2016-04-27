<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Actions</td>
    </tr>
    <c:forEach items="${projects}" var="p">
        <tr>
            <td>${p.name}</td>
            <td><a href="projects/edit/${p.projectId}">Edit</a>&nbsp;
                <a href="projects/delete/${p.projectId}">Delete</a></td>

        </tr>
    </c:forEach>
</table>
<br>

<%--объект, который придёт на эту форму, будет замаплен на поля--%>
<%--атрибут в реквесте--%>
<form:form commandName="project" method="POST" >
    <form:hidden path="projectId"/>

    <table>
        <tr>
            <td>Name:</td>
            <%--"name" поле объекта Project--%>
            <td><form:input path="name"/></td>
            <td><form:errors path="name" cssStyle="color: red;"/></td>

        </tr>

        <tr>
            <td></td>
            <td align="right"><input type="submit" value="submit"></td>
            <td></td>
        </tr>

    </table>


</form:form> 