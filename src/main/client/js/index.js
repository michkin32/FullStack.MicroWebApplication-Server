import MessageService from "./message-service.js";

let userId = "Tester";
const messageService = new MessageService();

window.addEventListener("load", function () {
    //document.getElementById("greeting").innerHTML = `Welcome ${userId}`;
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
    })
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

            messageService.createNewMessage(data)
                .then(successCallback, errorCallback);

            function successCallback(response) {
                // This data comes from the resolve method
                addMessageToThread(response);
            }

            function errorCallback(response) {
                // This data comes from the reject method
                console.log(response);
            }
        }
    }
}