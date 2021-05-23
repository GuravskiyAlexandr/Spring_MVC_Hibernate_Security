<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-striped" style="width: 50%; margin: auto">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">FirstName</th>
        <th scope="col">LastName</th>
        <th scope="col">Email</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.firstName}</td>
            <td class="align-middle">${user.lastName}</td>
            <td class="align-middle">${user.email}</td>
            <td style="width: 80px;" class="align-middle">
                <button style="width: 80px;"
                        onclick="checkParamForm('/edit', 'POST',`${user.id}`)"
                        type="button" data-bs-toggle="modal"
                        data-target="#exampleModalCenter"
                        data-bs-target="#staticBackdrop"
                        class="btn btn-primary">Edit
                </button>
            </td>
            <td style="width: 80px;" class="align-middle">
                <button onclick="checkParamForm('/delete', 'GET', `${user.id}`)"
                        type="button"
                        class="btn btn-danger"
                        data-bs-toggle="modal"
                        data-target="#exampleModalCenter"
                        data-bs-target="#staticBackdrop">Delete
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<script>
    function checkParamForm(path, method, id) {
        let user;
        <c:forEach var="user" items="${usersList}">
        if (`${user.id}` === id) {
            user = {
                id: `${user.id}`,
                firstName: `${user.firstName}`,
                lastName: `${user.lastName}`,
                email: `${user.email}`
            }
        }
        </c:forEach>
        setParamForm(path, method, user);
    }
</script>