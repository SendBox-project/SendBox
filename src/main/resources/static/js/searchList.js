// function getSearchList() {
//     // 검색어를 가져옴
//     let storeName = document.getElementById("search").value;
//
//     // 검색어를 이용하여 서버에 GET 요청을 보냄
//     fetch(`/store/searchList?storeName=${storeName}`)
//         .then(response => response.json()) // 응답을 JSON으로 변환
//         .then(data => {
//             // 검색 결과를 처리하여 HTML로 표시
//             let searchResults = document.getElementById("search_result");
//             searchResults.innerHTML = ""; // 이전 결과를 지움
//
//             data.forEach(store => {
//                 // 각 가게에 대한 정보를 리스트에 추가
//                 let listItem = document.getElementById("store_list");
//                 listItem.textContent = store.storeName;
//                 searchResults.appendChild(listItem);
//             });
//         })
//         .catch(error => console.error('Error:', error)); // 오류 처리
// }

let page = 0; // 페이지 번호를 저장하는 변수

function getSearchList() {
    // 검색어를 가져옴
    let storeName = document.getElementById("search").value;

    // 검색어를 이용하여 서버에 GET 요청을 보냄
    fetch(`/store/searchList?storeName=${storeName}`)
        .then(response => response.json()) // 응답을 JSON으로 변환
        .then(data => {
            // 검색 결과를 처리하여 HTML로 표시
            let searchResults = document.getElementById("search_result");
            searchResults.innerHTML = ""; // 이전 결과를 지움

            data.forEach(store => {
                // 각 매장에 대한 정보 반복
                let storeItem = document.createElement("div");
                storeItem.classList.add("store_item");

                // 상세 페이지로 넘어가는 링크
                let detailLink = document.createElement("a");
                detailLink.classList.add("goto_detail");
                detailLink.href = "/store/detail?id=" + store.storeName; //상세 페이지 URL을 지정
                // detailLink.textContent = "상세 보기";
                //썸네일을 링크 영역에 추가
                detailLink.appendChild(thumbnailDiv);

                //썸네일 생성
                let thumbnailDiv = document.createElement("div");
                thumbnailDiv.classList.add("search_thumbnail");
                let thumbnailImg = document.createElement("img");
                thumbnailImg.src = store.thumbnail;
                thumbnailImg.alt = "썸네일";
                thumbnailDiv.appendChild(thumbnailImg);

                //매장정보 생성
                let infoDiv = document.createElement("div");
                infoDiv.classList.add("search_info");
                let infoNameDiv = document.createElement("div");
                infoNameDiv.classList.add("search_infoName");
                let nameParagraph = document.createElement("p");
                nameParagraph.textContent = store.storeName;
                infoNameDiv.appendChild(nameParagraph);
                infoDiv.appendChild(infoNameDiv);

                // 각 매장에 대한 price값
                store.rooms.forEach(room => {
                    let roomInfoDiv = document.createElement("div");
                    roomInfoDiv.classList.add("room_info");
                    let roomPriceParagraph = document.createElement("p");
                    roomPriceParagraph.textContent = room.price;
                    roomInfoDiv.appendChild(roomPriceParagraph);
                    infoDiv.appendChild(roomInfoDiv);
                });

                //썸네일과 매장정보를 storeItem에 추가
                storeItem.appendChild(thumbnailDiv);
                storeItem.appendChild(infoDiv);

                //검색 결과 목록에 storeItem 추가
                searchResults.appendChild(storeItem);
            });
            // 페이지 번호 증가
            page++;
        })
        .catch(error => console.error('Error:', error)); // 오류 처리
}

// 무한 스크롤............... 다시 찾아서 수정해보기
window.onscroll = function() {
    // 현재 스크롤 위치
    let scrollHeight = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
    let scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
    let clientHeight = document.documentElement.clientHeight;

    // 스크롤이 맨 아래에 도달하면 추가 검색 수행
    if (scrollTop + clientHeight >= scrollHeight) {
        getSearchList();
    }
}