export default class MessageService {


    getAllMessages(chatId) {
        const request = new XMLHttpRequest();

        return new Promise(function (resolve, reject) {
            request.onload = function () {
                if (request.status >= 200 && request.status < 300) {
                    const threads = JSON.parse(request.responseText);
                    resolve(threads);
                } else {
                    reject({
                        status: request.status,
                        statusText: request.statusText
                    });
                }
            };

            request.open("GET", `${window.location.origin}/chat/${chatId}/messages`);
            request.send();
        })
    }


    createNewMessage(userId, chatId, message) {
        const request = new XMLHttpRequest();

        return new Promise(function (resolve, reject) {
            request.onload = function () {
                // Process the response
                if (request.status >= 200 && request.status < 300) {
                    // If successful
                    resolve(JSON.parse(request.responseText));
                } else {
                    reject({
                        status: request.status,
                        statusText: request.statusText
                    });
                }
            };

            request.open("POST", `${window.location.origin}/user/${userId}/chat/${chatId}/message`);
            request.setRequestHeader("Content-type", "application/json; charset=utf8");
            request.send(JSON.stringify(message));
        });
    }
}