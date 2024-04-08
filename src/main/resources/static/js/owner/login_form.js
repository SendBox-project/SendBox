

function loginForm() {
    console.log("loginForm() 호출");

    let form = document.login_form;

    if (form.o_m_id.value === "") {
        alert("아이디를 입력하세요.");
        form.o_m_id.focus();
    } else if (form.o_m_pw.value === "") {
        alert("비밀번호를 입력하세요.");
        form.o_m_pw.focus();
    }
}