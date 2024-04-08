// create_account_form.html의 input이 빈칸일 경우 if문으로 경고창을 띄우는 함수

function create_account_form() {
    console.log("create_account_from() 호출");

    let form = document.create_account_form;

    if(form.u_m_id.value === "") {
        alert("아이디를 입력하세요.");
        form.u_m_id.focus();
    } else if(form.u_m_pw.value === "") {
        alert("비밀번호를 입력하세요.");
        form.u_m_pw.focus();
    } else if (form.u_m_pw_again.value === "") {
        alert("비밀번호 확인을 입력하세요.");
        form.u_m_pw_again.focus();
    } else if (form.u_m_name.value === "") {
        alert("이름을 입력하세요.");
        form.u_m_name.focus();
    } else if (form.u_m_mail.value === "") {
        alert("이메일을 입력하세요.");
        form.u_m_mail.focus();
    } else if (form.u_m_phone.value === "") {
        alert("전화번호를 입력하세요.");
        form.u_m_phone.focus();
    } else if (form.u_m_gender.value === "") {
        alert("성별을 선택하세요.");
        form.u_m_gender.focus();
    } else {
        form.submit();
    }

}