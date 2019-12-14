import MessageService from "./message-service.js";
import LoginService from "./login-service.js";

let userId = "";
let userPassword = "";
const messageService = new MessageService();
const loginService = new LoginService();

window.addEventListener("load", function () {
    createUserLoginListener();
    createFormListener();
    messageService.getAllMessages().then(successCallback, errorCallback);
});

function successCallback(response) {
    // This data comes from the resolve method
    populateThread(response);
    console.log(response);
}

function errorCallback(response) {
    // This data comes from the reject method
    console.log(response);
}

function populateThread(messages) {
    messages.forEach(message => {
        addMessageToThread(message);
    });
}

function addMessageToThread(message) {
    const messageListItem = document.createElement("div");
    messageListItem.className = "message";

    const messageHead = document.createElement("div");
    messageHead.className = "message-head";

    const userIdContent = document.createElement("div");
    userIdContent.className = "message-sender";
    userIdContent.innerHTML = message.fromid;
    messageHead.appendChild(userIdContent);

    const timeContent = document.createElement("div");
    timeContent.className = "message-time";
    timeContent.innerHTML = message.timestamp;
    messageHead.appendChild(timeContent);

    const messageBody = document.createElement("div");
    messageBody.innerHTML = message.message;

    messageListItem
        .appendChild(messageHead)
        .appendChild(messageBody);
    document.getElementById("message-container").appendChild(messageListItem);

    let objDiv = document.getElementById("message-container");
    objDiv.scrollTop = objDiv.scrollHeight;
}

function createFormListener() {
    const input = document.getElementById("message-text-box-input");

    input.onkeydown = function (event) {
        // stop the regular form submission
        if (event.key === 'Enter') {
            event.preventDefault();

            const data = {
                fromid: userId,
                message: input.value
            };
            input.value = "";

            function successCallback(response) {
                // This data comes from the resolve method
                addMessageToThread(response);
            }
            function errorCallback(response) {
                // This data comes from the reject method
                console.log(response);
            }
            messageService.createNewMessage(data)
                .then(successCallback, errorCallback);
        }
    }
}

function createUserLoginListener() {

    const loginSubmit = document.getElementById("user-login-submit");

    loginSubmit.onclick = function (event) {
        userId = document.getElementById("login-username-input").value;
        userPassword = document.getElementById("login-username-input").value;


        function successCallback(response) {
            // This data comes from the resolve method
            console.log(response);
            document.getElementById("user-login").className = "hidden";
        }
        function errorCallback(response) {
            // This data comes from the reject method
            console.log(response);
            document.getElementById("user-login").className = "error";
        }
        loginService.login(userId).then(successCallback, errorCallback);
    }

}