

function loginForm() {
    console.log("loginForm() 호출");

    let form = document.login_form;

    if(form.admin_id.value === "") {
        alert("아이디를 입력하세요.");
        form.admin_id.focus();
    } else if(form.password.value === "") {
        alert("비밀번호를 입력하세요.");
        form.password.focus();
    } else {
        form.submit();
    }
}