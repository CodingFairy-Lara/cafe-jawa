/**
websocket란
- 클라이언트/서버간의 양방향 통신지원하는 표준 API
- client-side : WebSocket객체
- server-side : webSocket.jar(tomcat)
 */
 
// endpoint  웹소켓접속이 처음 이루어지는 url /helloWebSocket 
const ws = newWebSocket('ws://${location.host}/mvc/helloWebSocket');
 
ws.addEventListener('open', (e) => {
	console.log('open : ', e);
});

ws.addEventListener('message', (e) => {
	console.log('message : ', e);
});

ws.addEventListener('error', (e) => {
	console.log('error : ', e);
});

ws.addEventListener('close', (e) => {
	console.log('close : ', e);
});