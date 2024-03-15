/**
 * 웹소켓 채팅
 */
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    var rndUserName = "User"+getRandomNumber();
    var headers = {
            'name' : rndUserName
        };
    stompClient.connect(headers, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.send("/app/enter", {}, JSON.stringify({'name': rndUserName}));
    });
}

function getRandomNumber() {
    return Math.floor(Math.random() * 1000); // 0 이상 1000 미만의 랜덤한 숫자 생성
}

function disconnect() {
    if (stompClient !== null) {
        exit();
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/chat", {}, JSON.stringify({'chat': $("#chat").val()}));
}
function enter(){
    stompClient.send("/app/enter", {}, JSON.stringify({}));
}
function exit(){
    stompClient.send("/app/exit", {}, JSON.stringify({}));
}

function showGreeting(message) {
	console.log("어떤메시지?>>>>>"+message);
	var str ='<tr><td>' + message + '</td></tr>';
    $("#greetings").append(str);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).on("click",function() { connect(); });
    $( "#disconnect" ).on("click",function() { disconnect(); });
    $( "#send" ).on("click",function() { sendName(); });
});