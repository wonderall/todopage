/**let token = ""
$('.submit').on("click", function() {
	console.log("submit??")
	var username = $('.username').val()
	var password = $('.password').val()
	console.log(username)
	console.log(password)
	$.ajax({
		type: 'post',           // 타입 (get, post, put 등등)
		url: '/auth/signin',           // 요청할 서버url
		async: false,            // 비동기화 여부 (default : true)
		headers: {              // Http header
			"Content-Type": "application/json",
		},
		dataType: 'JSON',       // 데이터 타입 (html, xml, json, text 등등)
		data: JSON.stringify({
			'username': username,
			'password': password,
		}),
		success: function(result) { // 결과 성공 콜백함수
			console.log(result)
			
			

		},
		error: function(error) { // 결과 에러 콜백함수
			console.log(error)
		}
	})
	
}) */



