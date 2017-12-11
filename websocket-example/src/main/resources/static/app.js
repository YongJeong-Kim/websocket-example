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
    stompClient.subscribe('/user/queue/hello/uuu', function (greeting) {
//      showGreeting(JSON.parse(greeting.body).message);
    	alert('helhhhhhhhhhhhhhlo : ');
    	console.log(greeting);
    });
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    var v;
    stompClient = Stomp.over(socket);
	stompClient.debug = null;
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        var sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
        console.log("connected, session id: " + sessionId);
//        stompClient.subscribe('/topic/greetings', function (greeting) {
        stompClient.subscribe('/hello/' + v, function (greeting) {
//            showGreeting(JSON.parse(greeting.body).message);
        	showGreeting(JSON.parse(greeting.body));
            console.log(JSON.parse(greeting.body));
            alert(v);
        });
        
        stompClient.subscribe('/user/queue/reply', function (greeting) {
//          showGreeting(JSON.parse(greeting.body).message);
        	alert('in user name');
        });
        
       /* stompClient.subscribe('/user/queue/hello/uuu', function (greeting) {
        	alert('hello : ');
        	console.log(greeting);
        });*/
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    /*stompClient.send("/app/hello/uuu", {}, 
    		JSON.stringify(
    				{
    					'name': $("#name").val(),
    					'message': $("#message").val()
    				}
    		));*/
	 stompClient.send("/app/hello/uuu", {}, 
	    		JSON.stringify(
	    				{
	    					'name': $("#name").val(),
	    					'message': $("#message").val()
	    				}
	    		));
	 
	
    console.log('send name');
}

function showGreeting(message) {
	
    $("#greetings").append("<tr><td>" + message.name + " : " + message.message + "</td></tr>");
}

$(function () {

    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

