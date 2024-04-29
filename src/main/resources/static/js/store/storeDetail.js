//장바구니 추가 기능
function addCart() { /*cartList.html에 form태그와 연결*/
    const f = document.getElementById("order_form"); //cartList.html의 form1과 ㅇ녀결
    const select_value = document.getElementById('sizeType'); // 다큐먼트(즉 html 문서)안에 id 설정 해놓은 것 중에 sizeType 이거와 맞는 소스를 가져옴!
    //teamList.html 에서 id가 searchType인 애의 값을 가져와서 변수 select_value에 넣는다.
    f.roomNo.value = select_value.options[select_value.selectedIndex].value; // 옵션 중에서 셀렉트 된 것만 가져온다. 여기선 cartListAjax의 roomNo

    f.submit();

}

//결제하기
function paymentGo() {
    const f = document.getElementById("order_form"); //cartList.html의 form1과 ㅇ녀결
    const select_value = document.getElementById('sizeType'); // 다큐먼트(즉 html 문서)안에 id 설정 해놓은 것 중에 sizeType 이거와 맞는 소스를 가져옴!
    //teamList.html 에서 id가 searchType인 애의 값을 가져와서 변수 select_value에 넣는다.
    f.roomNo.value = select_value.options[select_value.selectedIndex].value; // 옵션 중에서 셀렉트 된 것만 가져온다. 여기선 cartListAjax의 roomNo

    $.ajax({
        url: "/user/payment", //백엔드 경로
        type: 'GET',
        cache: false,
        data: $('#order_form').serialize(),
        async: false,
        success: function (data) {   //성공시에 pc창에 카카오페이지 창 띄우기
            location.href = data.next_redirect_pc_url;
            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
        }
    })
}