<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <br>
		<h2>${message}</h2>
        <br>
        <form class="form-inline">
            <div class="form-group">
            <input type="hidden" name="insertEmpl" value="true">
            <input type="text" name="id" placeholder="ID">
            <input type="text" name="name" placeholder="Name">
            <input type="text" name="surname" placeholder="Surname">
            <input type="text" name="experience" placeholder="Experience">
            <input type="text" name="department" placeholder="Department">
            </div>
            <button type="submit" class="btn btn-success btn-sm insert-button">Insert new employee</button>
        </form>

        <br>

    <%--<p>${employees}</p>--%>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Experience</th>
                <th>Department</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employees" items="${employees}">
            <tr>
                <td><c:out value="${employees.id}"></c:out></td>
                <td><c:out value="${employees.name}"></c:out></td>
                <td><c:out value="${employees.surname}"></c:out></td>
                <td><c:out value="${employees.experience}"></c:out></td>
                <td><c:out value="${employees.department}"></c:out></td>
                <td>
                        <button type="button" data-toggle="modal" data-target="#modalUpdate" class="btn btn-warning btn-sm update-button" eid="${employees.id}"
                                ename="${employees.name}" esurname="${employees.surname}" eexperience="${employees.experience}"
                                edepartment="${employees.department}">Update</button>
                </td>
                <td><form>
                        <input type="hidden" name="deleteid" value="${employees.id}">
                        <button type="submit" class="btn btn-danger btn-sm delete-button">Delete</button>
                </form></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>


        <!-- Modal -->
        <div class="modal fade" id="modalUpdate" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Update employee</h4>
                    </div>
                    <form class="form-inline">
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="hidden" name="updateid" value="true">
                                <input type="text" id="uid" name="id" readonly="readonly">
                                <input type="text" id="uname" name="name">
                                <input type="text" id="usurname" name="surname">
                                <input type="text" id="uexperience" name="experience">
                                <input type="text" id="udepartment" name="department">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-warning save-changes">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
	</div>
    <script>
        $(document).ready(function(){
            $('.hello').addClass('active');
            <%--console.log(${employees});--%>
            $(".update-button").click(function(){
                var id = $(this).attr("eid");
                var name = $(this).attr("ename");
                var surname = $(this).attr("esurname");
                var experience = $(this).attr("eexperience");
                var department = $(this).attr("edepartment");
                $("#uid").attr("value", id);
                $("#uname").attr("value", name);
                $("#usurname").attr("value", surname);
                $("#uexperience").attr("value", experience);
                $("#udepartment").attr("value", department);
            });
        });
    </script>
<jsp:include page="footer.jsp"></jsp:include>