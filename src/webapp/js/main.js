async function checkParamForm(path, method, userId) {
    console.log(path);
    console.log(method);
    console.log(userId);
    let user;
    if (userId !== null) {
        let response = await fetch("user/?id=" + userId);
        console.log(response.ok);
        if (response.ok) {
            user = await response.json();
            console.log(user);
        }
    }
    const form = document.getElementById('formUser');
    form.setAttribute('action', path);
    form.setAttribute('method', method);
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