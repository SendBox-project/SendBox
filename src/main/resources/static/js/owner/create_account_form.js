

function create_account_form() {
    console.log("create_account_from() 호출");

    let form = document.getElementById("create_account_from");

    if(form.ownerId.value === "") {
        alert("아이디를 입력하세요.");
        form.ownerId.focus();
        return false;
    } else if (form.password.value === "") {
        alert("비밀번호를 입력하세요.");
        form.password.focus();
        return false;
    } else if (form.password_again.value === "") {
        alert("비밀번호 확인을 입력하세요.");
        form.password_again.focus();
        return false;
    } else if (form.password.value !== form.password_again.value) {
        alert("비밀번호가 일치하지 않습니다.");
        form.passwordAgain.focus();
        return false;
    } else if (form.name.value === "") {
        alert("이름을 입력하세요.");
        form.name.focus();
        return false;
    } else if (form.mail.value === "") {
        alert("이메일을 입력하세요.");
        form.mail.focus();
        return false;
    } else if (form.phone.value === "") {
        alert("전화번호를 입력하세요.");
        form.phone.focus();
        return false;
    } else if (form.gender.value === "") {
        alert("성별을 선택하세요.");
        form.gender.focus();
        return false;
    } else {
        form.submit();
    }

}