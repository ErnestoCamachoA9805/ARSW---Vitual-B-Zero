const url= '';
let stompClient;
let selectedUser;
let newMessages= new Map();

let timerFetchAll= setInterval(()=> fetchAll(),500);

function connectToChat(userName){
    console.log("Conecting to chat...")
    let socket= new SockJS(url + '/chat')
    stompClient= Stomp.over(socket);
    stompClient.connect({}, function(frame){
         console.log("Connected to " + frame);
         stompClient.subscribe("/topic/messages/" + userName, function(response){
             let data= JSON.parse(response.body);
             //render(data.message,data.fromLogin); // Aqui es donde hace el render de todos los mensajes
             if(selectedUser === data.fromLogin){
                render(data.message,data.fromLogin);
             }
         });
    });
}

function sendMsg(from, text){
    stompClient.send("/app/chat/" + selectedUser ,{}, JSON.stringify({
        fromLogin: from,
        message: text,
        toUser:selectedUser
    }))
}

function registration(){
    let userName= document.getElementById("userName").value;
    $.get(url + "/registration/" + userName, function(response){
        connectToChat(userName);
    }).fail(function(error){
        if (error.status === 400){
            alert("The user name is taken")
        }
    })
}

function fetchAll(){
    $.get(url + "/fetchAllUsers" , function(response){
        let users= response;
        let usersTemplateHTML= "";
        for(let i= 0; i<users.length; i++){
            usersTemplateHTML= usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')" user="' + users[i] + '"><li class="clearfix">\n' +
            '                <img src="../images/NoUserImage.PNG" width="55px" height="55px" alt="avatar" />\n' +
            '                <div class="about">\n' +
            '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
            '                    <div class="status">\n' +
            '                        <i class="fa fa-circle offline"></i>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '            </li></span>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}

function selectUser(userName){
    console.log("selected User: " + userName);
    selectedUser= userName; 
    let isNew= document.getElementById("newMessage_"+userName) !== null;
    if(isNew){
        let element= document.getElementById("newMessage_"+userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName),userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat With ' + selectedUser);
    $('#chatHistory').html('');
}

function clearAllUsers(){
    $.post("/ClearAllUsers")
}

function clearUser(){
    let userName= document.getElementById("userName").value;
    $.post("/ClearUser/" + userName)
    alert("you're free to leave")
}


$("#entrarthechat").click(function() {
    $("#entrarthechat").attr('disabled', 'disabled');
    $("#logout").removeAttr('disabled');
});

$("#logout").click(function() {
    $("#entrarthechat").removeAttr('disabled');
    $("#logout").attr('disabled', 'disabled');
});


function renderPastMessages(){
    
}