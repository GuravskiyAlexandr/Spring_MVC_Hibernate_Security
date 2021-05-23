<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Add new user</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

<%--                form--%>
                <form id="formUser" action="" method="">
                    <input id="userId" type="hidden" name="id" value="">
                    <div class="form-group">
                        <label for="firstName">Name</label>
                        <input value="" type="text" name="firstName" class="form-control" id="firstName"
                               placeholder="firstName">
                    </div>

                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" name="lastName" class="form-control" id="lastName" placeholder="lastName">
                    </div>

                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input type="email" name="email" class="form-control" id="email"
                               placeholder="Enter email">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>


    function setParamForm(path, method, user) {
        console.log(path);
        console.log(method);
        console.log(user);


        const button = document.getElementById('formUser');
        button.setAttribute('action', path);
        button.setAttribute('method', method);
        if (user != null) {
            const userId = document.getElementById('userId');
            const firstName = document.getElementById('firstName');
            const lastName = document.getElementById('lastName');
            const email = document.getElementById('email');
            userId.setAttribute('value', user.id);
            firstName.setAttribute('value', user.firstName);
            lastName.setAttribute('value', user.lastName);
            email.setAttribute('value', user.email);
        }
    }

</script>