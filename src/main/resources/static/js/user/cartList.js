//js를 통해 리스트와 아작스를 연결
$(document).ready(function () { // 페이지가 로딩되는 순간 바로 실행
    console.log("ready!");
    cartListAjax(1); // 들어가서 1페이지가 보임
});

function cartListAjax(page) { // 위에서 보낸 매개변수 1을 받아 준다!
    const innerHtml = $("#cartListForm") //cartListAjax.html 소스를 붙일 파일 위치 지정
    const f = document.getElementById("form1"); //cartList.html의 form1과 ㅇ녀결
    f.page.value = page;


    $.ajax({
        url: "/user/cartListAjax", //백엔드 경로
        type: 'GET',
        cache: false,
        data: $('#form1').serialize(),
        dataType: "html",
        async: false,
        //성공 시에 cartList.html의 cartListForm태그 위치에 백엔드 경로(cartListAjax)에 연결 된 리턴 값인 프론트 cartListAjax.html을 넣어서 보여줌
        success: function (data) {
            $(innerHtml).html(data)

            setTimeout(function () {
            }, 1000)
        },
        error: function (e) {
            $(innerHtml).html("")
        }
    })

}

