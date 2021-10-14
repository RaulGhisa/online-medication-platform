import SockJS from 'sockjs-client';
const Stomp = require('stompjs');

const Connection = (serverUrl) => {
    let socket = new SockJS(serverUrl);
    let stompClient = Stomp.over(socket);

    return stompClient;
}

export default Connection;