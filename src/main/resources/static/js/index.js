var state = [];

function setDefaultState() {
	var id = generateID();
	var baseState = {};
	baseState[id] = {
		status: "new",
		id: id,
		title: "This site uses 🍪to keep track of your tasks"
	};
	syncState(baseState);
}

function generateID() {
	var randLetter = String.fromCharCode(65 + Math.floor(Math.random() * 26));
	return randLetter + Date.now();
}

function pushToState(title, status, id) {
	var baseState = getState();
	baseState[id] = { id: id, title: title, status: status };
	syncState(baseState);
}
/**
function setToDone(id) {
	var baseState = getState();
	if (baseState[id].status === 'new') {
		baseState[id].status = 'done'
	} else {
		baseState[id].status = 'new';
	}

	syncState(baseState);
} */

function deleteTodo(id) {
	console.log(id)
	var baseState = getState();
	//delete baseState[id]
	syncState(baseState)
}

function resetState() {
	localStorage.setItem("state", null);
}

function syncState(state) {
	localStorage.setItem("state", JSON.stringify(state));
}

function getState() {
	return JSON.parse(localStorage.getItem("state"));
}

function getList() {
	$.ajax({
		type: 'get',           // 타입 (get, post, put 등등)
		url: '/todo',           // 요청할 서버url
		async: false,            // 비동기화 여부 (default : true)
		headers: {              // Http header
			"Content-Type": "application/json",
		},
		dataType: 'JSON',       // 데이터 타입 (html, xml, json, text 등등)
		data: JSON.stringify(),
		success: function(result) { // 결과 성공 콜백함수

			//var data = JSON.parse(result);			
			$.each(result.data, function(index, data) { // 데이터 =item
				//console.log(index)
				//console.log(data)
				//var data = JSON.parse(datas);
				var c = data.done === "true" ? "danger" : "";
				var item =
					'<li data-id="' +
					data.id +
					'" class="animated flipInX ' +
					c +
					'"><div class="checkbox"><span class="close"><i class="fa fa-times"></i></span><label><span class="checkbox-mask"></span><input type="checkbox" />' +
					data.title +
					"</label></div></li>";
				$(".err").addClass("hidden");
				$(".todo-list").append(item);
			});

		},
		error: function(error) { // 결과 에러 콜백함수
			console.log(error)
		}
	})
}

function addItem(text, status, id, noUpdate) {
	if (text === "") {
		$(".err")
		.removeClass("hidden")
		.addClass("animated bounceIn");
		return;
	}
	$.ajax({
		type: 'post',           // 타입 (get, post, put 등등)
		url: '/todo',           // 요청할 서버url
		async: false,            // 비동기화 여부 (default : true)
		headers: {              // Http header
			"Content-Type": "application/json",
		},
		dataType: 'JSON',       // 데이터 타입 (html, xml, json, text 등등)
		data: JSON.stringify({
			'title': text
		}),
		success: function(result) { // 결과 성공 콜백함수
			var item = ""
			
			$(".todo-list").empty();
			$.each(result.data, function(index, data) { // 데이터 =item
				//console.log(index)
				//console.log(data)
				//var data = JSON.parse(datas);
				var c = data.done === "true" ? "danger" : "";
				var item =
					'<li data-id="' +
					data.id +
					'" class="animated flipInX ' +
					c +
					'"><div class="checkbox"><span class="close"><i class="fa fa-times"></i></span><label><span class="checkbox-mask"></span><input type="checkbox" />' +
					data.title +
					"</label></div></li>";
				$(".err").addClass("hidden");
				$(".todo-list").append(item);
			});
			$(".form-control").val("")
		},
		error: function(error) { // 결과 에러 콜백함수
			console.log(error)
		}
	})
	/*
	var id = id ? id : generateID();
	var c = status === "done" ? "danger" : "";
	var item =
	  '<li data-id="' +
	  id +
	  '" class="animated flipInX ' +
	  c +
	  '"><div class="checkbox"><span class="close"><i class="fa fa-times"></i></span><label><span class="checkbox-mask"></span><input type="checkbox" />' +
	  text +
	  "</label></div></li>";
  
	var isError = $(".form-control").hasClass("hidden");
  
	if (text === "") {
	  $(".err")
		.removeClass("hidden")
		.addClass("animated bounceIn");
	} else {
	  $(".err").addClass("hidden");
	  $(".todo-list").append(item);
	}
  
	$(".refresh").removeClass("hidden");
  
	$(".no-items").addClass("hidden");
  
	$(".form-control")
	  .val("")
	  .attr("placeholder", "✍️ Add item...");
	setTimeout(function() {
	  $(".todo-list li").removeClass("animated flipInX");
	}, 500);
  
	if (!noUpdate) {
	  pushToState(text, "new", id);
	}*/
}

function refresh() {
	$(".todo-list li").each(function(i) {
		$(this)
			.delay(70 * i)
			.queue(function() {
				$(this).addClass("animated bounceOutLeft");
				$(this).dequeue();
			});
	});

	setTimeout(function() {
		$(".todo-list li").remove();
		$(".no-items").removeClass("hidden");
		$(".err").addClass("hidden");
	}, 800);
}

$(function() {
	var err = $(".err"),
		formControl = $(".form-control"),
		isError = formControl.hasClass("hidden");

	if (!isError) {
		formControl.blur(function() {
			err.addClass("hidden");
		});
	}

	$(".add-btn").on("click", function() {
		var itemVal = $(".form-control").val();
		addItem(itemVal);
		formControl.focus();
	});

	$(".refresh").on("click", refresh);

	$(".todo-list").on("click", 'input[type="checkbox"]', function() {
		var li = $(this)
			.parent()
			.parent()
			.parent();
		li.toggleClass("danger");
		li.toggleClass("animated flipInX");

		//setToDone(li.data().id);

		setTimeout(function() {
			li.removeClass("animated flipInX");
		}, 500);
	});

	$(".todo-list").on("click", ".close", function() {
		var box = $(this)
			.parent()
			.parent();
		console.log(box)
		if ($(".todo-list li").length == 1) {
			box.removeClass("animated flipInX").addClass("animated                bounceOutLeft");
			setTimeout(function() {
				box.remove();
				$(".no-items").removeClass("hidden");
				$(".refresh").addClass("hidden");
			}, 500);
		} else {
			box.removeClass("animated flipInX").addClass("animated bounceOutLeft");
			setTimeout(function() {
				box.remove();
			}, 500);
		}

		deleteTodo(box.data().id)
		$.ajax({
			type: 'delete',           // 타입 (get, post, put 등등)
			url: '/todo',           // 요청할 서버url
			async: false,            // 비동기화 여부 (default : true)
			headers: {              // Http header
				"Content-Type": "application/json",
			},
			dataType: 'JSON',       // 데이터 타입 (html, xml, json, text 등등)
			data: JSON.stringify({
				'id': box.data().id
			}),
			success: function(result) { // 결과 성공 콜백함수
				//console.log(result)
			},
			error: function(error) { // 결과 에러 콜백함수
				console.log(error)
			}
		})
	});

	$(".form-control").keypress(function(e) {
		if (e.which == 13) {
			var itemVal = $(".form-control").val();
			addItem(itemVal);
		}
	});
	$(".todo-list").sortable();
	$(".todo-list").disableSelection();
});

var todayContainer = document.querySelector(".today");


var d = new Date();


var weekday = new Array(7);
weekday[0] = "Sunday(일) 🖖";
weekday[1] = "Monday(월) 💪😀";
weekday[2] = "Tuesday(화) 😜";
weekday[3] = "Wednesday(수) 😌☕️";
weekday[4] = "Thursday(목) 🤗";
weekday[5] = "Friday(금) 🍻";
weekday[6] = "Saturday(토) 😴";


var n = weekday[d.getDay()];


var randomWordArray = Array(
	"Oh my, it's ",
	"Whoop, it's ",
	"Happy ",
	"Seems it's ",
	"Awesome, it's ",
	"Have a nice ",
	"Happy fabulous ",
	"Enjoy your "
);
var now = new Date();


var year = now.getFullYear();//연도
var month = now.getMonth() + 1;//월
var date = now.getDate();//일
var day = now.getDay();//요일
var hr = now.getHours();//시간
var min = now.getMinutes();//분
var sec = now.getSeconds();//초


var randomWord =
	randomWordArray[Math.floor(Math.random() * randomWordArray.length)];


todayContainer.innerHTML = year+"."+month+"."+ date+ " - "+n;

$(document).ready(function() {

	getList()
	/*
	var state = getState();
	if (!state) {
		setDefaultState();
		state = getState();
	}

	Object.keys(state).forEach(function(todoKey) {
		var todo = state[todoKey];
		addItem(todo.title, todo.status, todo.id, true);
	});

	var mins, secs, update;

	init();
	function init() {
		(mins = 25), (secs = 59);
	}


	set();
	function set() {
		$(".mins").text(mins);
	}


	$("#start").on("click", start_timer);
	$("#reset").on("click", reset);
	$("#inc").on("click", inc);
	$("#dec").on("click", dec);

	function start_timer() {

		set();

		$(".dis").attr("disabled", true);

		$(".mins").text(--mins);
		$(".separator").text(":");
		update_timer();

		update = setInterval(update_timer, 1000);
	}

	function update_timer() {
		$(".secs").text(secs);
		--secs;
		if (mins == 0 && secs < 0) {
			reset();
		} else if (secs < 0 && mins > 0) {
			secs = 59;
			--mins;
			$(".mins").text(mins);
		}
	}


	function reset() {
		clearInterval(update);
		$(".secs").text("");
		$(".separator").text("");
		init();
		$(".mins").text(mins);
		$(".dis").attr("disabled", false);
	}


	function inc() {
		mins++;
		$(".mins").text(mins);
	}


	function dec() {
		if (mins > 1) {
			mins--;
			$(".mins").text(mins);
		} else {
			alert("This is the minimum limit.");
		}
	} */
});

