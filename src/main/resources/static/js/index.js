import MessageService from "./message-service.js";
import UserService from "./user-service.js";

const messageService = new MessageService();
const userService = new UserService();


$( document ).ready(function() {
    createUserLoginListener();
});

function startChatApp(user) {
    let userChats = [];
    let currentChat;

    $("#user-login").addClass("hidden");
    getUserChats();
    createFormListener();

    function getUserChats() {
        function successCallback(response) {
            response.forEach(chat => {
               addChatToThread(chat);
            });
            console.log(response);
            currentChat = userChats[0];
            getChatMessages();
        }
        function errorCallback(response) {
            console.log(response);
        }
        userService.getChats(user.userId).then(successCallback, errorCallback);
    }
    function addChatToThread(chat) {
        $("#chat-container").append(
            "<div class='chat'>@ " + chat.chatName + "</div>");
        userChats.push(chat);
    }

    function getChatMessages() {
        function successCallback(response) {
            response.forEach(message => {
                addMessageToThread(message);
            });
            console.log(response);
        }
        function errorCallback(response) {
            console.log(response);
        }
        messageService.getAllMessages(currentChat.chatId).then(successCallback, errorCallback);
    }
    function addMessageToThread(message) {
        const messageListItem = document.createElement("div");
        messageListItem.className = "message";

        const messageHead = document.createElement("div");
        messageHead.className = "message-head";

        const userIdContent = document.createElement("div");
        userIdContent.className = "message-sender";
        userIdContent.innerHTML = message.sender.userName;
        messageHead.appendChild(userIdContent);

        const timeContent = document.createElement("div");
        timeContent.className = "message-time";
        timeContent.innerHTML = message.timeStamp;
        messageHead.appendChild(timeContent);

        const messageBody = document.createElement("div");
        messageBody.innerHTML = message.messageBody;

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
                    messageBody: input.value
                };
                input.value = "";

                function successCallback(response) {
                    addMessageToThread(response);
                }
                function errorCallback(response) {
                    console.log(response);
                }
                messageService.createNewMessage(user.userId, currentChat.chatId, data)
                    .then(successCallback, errorCallback);
            }
        }
    }
}

function createUserLoginListener() {
    $("#user-login .login-switch").click(function () {

        let login = $("#login-wrapper");
        let register = $("#register-wrapper");

        if(login.hasClass("hidden")) {
            login.removeClass("hidden");
            register.addClass("hidden");
        } else {
            login.addClass("hidden");
            register.removeClass("hidden");
        }
    });

    $("#user-login-submit").click( function () {
        let userName = document.getElementById("login-username-input").value;
        let userPassword = document.getElementById("login-username-input").value;

        function successCallback(response) {
            console.log(response);
            startChatApp(response);
        }
        function errorCallback(response) {
            console.log(response);
            $("#user-login #login-wrapper .error-message").removeClass("hidden");
        }
        userService.login(userName).then(successCallback, errorCallback);
    });

    $("#user-register-submit").click( function () {
        const userData = {
            email : $("#register-email-input").val(),
            userName : $("#register-username-input").val(),
            password : $("#register-password-input").val()
        };

        function successCallback(response) {
            console.log(response);
            startChatApp(response)
        }
        function errorCallback(response) {
            console.log(response);
            $("#user-login #login-wrapper .error-message").removeClass("hidden");
        }
        userService.createUser(userData).then(successCallback, errorCallback);
    });
}