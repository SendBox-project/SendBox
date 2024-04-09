// create_account_form.html의 input이 빈칸일 경우 if문으로 경고창을 띄우는 함수

function create_account_form() {
 console.log("create_account_from() 호출");

 let form = document.create_account_form;

 if(form.admin_id.value === "") {
  alert("아이디를 입력하세요.");
  form.admin_id.focus();
 } else if(form.password.value === "") {
    alert("비밀번호를 입력하세요.");
    form.password.focus();
 } else if (form.password_again.value === "") {
    alert("비밀번호 확인을 입력하세요.");
    form.password_again.focus();
 } else if (form.name.value === "") {
    alert("이름을 입력하세요.");
    form.name.focus();
 } else if (form.mail.value === "") {
    alert("이메일을 입력하세요.");
    form.mail.focus();
 } else if (form.phone.value === "") {
    alert("전화번호를 입력하세요.");
    form.phone.focus();
 } else if (form.gender.value === "") {
     alert("성별을 선택하세요.");
     form.gender.focus();
 } else if (form.part.value === "") {
     alert("담당지역을 선택하세요.");
     form.part.focus();
 } else {
    form.submit();
 }

}