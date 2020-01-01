export default class UserService {

    login(userName) {
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

            request.open("GET", `${window.location.origin}/user/${userName}/login`);
            request.send();
        })
    }

    createUser(user) {
        const request = new XMLHttpRequest();
        return new Promise(function (resolve, reject) {
            request.onload = function () {
                // Process the response
                if (request.status >= 200 && request.status < 300) {
                    resolve(JSON.parse(request.responseText));
                } else {
                    reject({
                        status: request.status,
                        statusText: request.statusText
                    });
                }
            };

            request.open("POST", `${window.location.origin}/user`);
            request.setRequestHeader("Content-type", "application/json; charset=utf8");
            request.send(JSON.stringify(user));
        });
    }

    getChats(userId) {
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

            request.open("GET", `${window.location.origin}/user/${userId}/chats`);
            request.send();
        });
    }
}