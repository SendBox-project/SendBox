function getSearchList() {
    // 검색어를 가져옴
    let keyword = document.getElementById("search").value;

    // 검색어를 이용하여 서버에 GET 요청을 보냄
    fetch(`/store/searchList?storeName=${storeName}`)
        .then(response => response.json()) // 응답을 JSON으로 변환
        .then(data => {
            // 검색 결과를 처리하여 HTML로 표시
            let searchResults = document.getElementById("search_result");
            searchResults.innerHTML = ""; // 이전 결과를 지움

            data.forEach(store => {
                // 각 가게에 대한 정보를 리스트에 추가
                let listItem = document.getElementById("store_list");
                listItem.textContent = store.storeName;
                searchResults.appendChild(listItem);
            });
        })
        .catch(error => console.error('Error:', error)); // 오류 처리
}