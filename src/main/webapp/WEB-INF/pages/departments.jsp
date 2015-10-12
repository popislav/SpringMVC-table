<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <br>
    <h2>${message}</h2>
    <br>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Department</th>
            <th>Employee Count</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="departments" items="${departments}">
            <tr>
                <td><c:out value="${departments.department}"></c:out></td>
                <td><c:out value="${departments.count}"></c:out></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script>
    <%--console.log(${departments});--%>
    $(document).ready(function () {
        $('.departments').addClass('active');
    });
</script>
<jsp:include page="footer.jsp"></jsp:include>