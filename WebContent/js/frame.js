$("div#menu").prepend(
"		<nav class='navbar navbar-default navbar-fixed-top' role='navigation'>" + "\n" + 
"			<div class='navbar-header'>" + "\n" + 
"				<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>" + "\n" + 
"					<span class='sr-only'>Toggle navigation</span>" + "\n" + 
"					<span class='sr-only'>Toggle navigation</span>" + "\n" + 
"					<span class='icon-bar'></span>" + "\n" + 
"					<span class='icon-bar'></span>" + "\n" + 
"					<span class='icon-bar'></span>" + "\n" + 
"				</button>" + "\n" + 
"				<a class='navbar-brand' href='Homepage.html' style='margin-left: 0.5vw; margin-right: 0.5vw; padding: 0;'><img src='img/frame/logo_white.png' class='img-responsive' style='height: 7vh; padding: 1.2vh; display: inline;' /></a>" + "\n" + 
"			</div>" + "\n" + 
"			<div class='navbar-collapse' role='navigation'>" + "\n" + 
"				<ul class='nav navbar-nav'>" + "\n" + 
"					<li id='homepage'><a href='Homepage.html'>首頁</a></li>" + "\n" + 
"					<li id='sendPush'><a href='SendPush.html'>發送推播</a></li>" + "\n" + 
"					<li id='userSuggestion'><a href='UserSuggestion.html'>系統回報</a></li>" + "\n" + 
"				</ul>" + "\n" + 
"				<ul class='nav navbar-nav navbar-right' style='margin-right: 1vw;'>" + "\n" + 
"					<li><a href='' onclick='logout()'>登出</a></li>" + "\n" + 
"				</ul>" + "\n" + 
"			</div>" + "\n" + 
"		</nav>" + "\n"
);

// 拿html檔名
function getHTMLName() {
	var path = window.location.pathname;
	var page = path.split("/").pop();
	var name = page.split(".");
	return name[0];
}

// 在nav加上active class
$(function(){
	var name = getHTMLName();
	switch(name) {
	case "Homepage": 
		document.getElementById("homepage").className = "active";
		break;
	case "SendPush": 
		document.getElementById("sendPush").className = "active";
		break;
	case "UserSuggestion":
		document.getElementById("userSuggestion").className = "active";
		break;
	}
});

function logout() {
	$.ajax({
		type: "POST",
		url: "AdministratorLoginServlet",
		data: {
			state: "logout"
		},
		dataType: "text",
		success: function(response) {
			window.location.href = 'Login.html';
		},
		error: function() {
			console.log("BBDPAdministrator frame.js logout() error");
		}
	});
}

$(document).ready(function() {
	$.ajax({
		type: "POST",
		url: "AdministratorLoginServlet",
		data: {
			state: "loginVerify"
		},
		dataType: "text",
		success: function(response) {
			if (response == "logout") {
				window.location.href = 'Login.html';
			}
		},
		error: function() {
			console.log("BBDPAdministrator frame.js loginVerify error");
		}
	});
});



