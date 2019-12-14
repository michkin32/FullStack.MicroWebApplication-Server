export default class LoginService {

    login(userName) {
        const request = new XMLHttpRequest();

        return new Promise(function (resolve, reject) {
            // Setup our listener to process compeleted requests
            request.onload = function () {
                // Process the response
                if (request.status >= 200 && request.status < 300) {
                    // If successful
                    const threads = JSON.parse(request.responseText);
                    // this data is passed to the success callback
                    resolve(threads);
                } else {
                    // this data is passed to the failure callback
                    reject({
                        status: request.status,
                        statusText: request.statusText
                    });
                }
            };

            request.open("GET", `http://localhost:8080/user/${userName}/login`);
            request.send();
        })
    }


    createUser(user) {
        const request = new XMLHttpRequest();

        return new Promise(function (resolve, reject) {
            // Setup our listener to process compeleted requests
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

            request.open("POST", `http://localhost:8080/user`);
            request.send(JSON.stringify(user));
        });
    }
}