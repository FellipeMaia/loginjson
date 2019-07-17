
function login() {
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('POST', './login');
    ourRequest.onload = function () {
        var ourData = JSON.parse(this.response);
        if (ourRequest.status >= 200 && ourRequest.status < 400) {
            if(ourData !== null || ourData !== ""){
                sessionStorage.setItem('idSession',ourData.idSession);
                sessionStorage.setItem('permissao',ourData.permisoes);
                window.location.replace('./?id='+ourData.idSession); 
            }
        } else {
            console.log("We connected to the server, but it returned an error.");
        }
    };
    ourRequest.onerror = function() {
        alert("Login ou/e Senha incorreto(s)");
        console.log("Connection error");
    };
    ourRequest.setRequestHeader('Content-type','application/json;charset=utf-8');
    var parametro = {};
    parametro.login = document.getElementById("inputLogin").value;
    parametro.psw = document.getElementById("inputPassword").value;
    ourRequest.send(JSON.stringify(parametro),true);

    
}


function getPermissao(){
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('POST', './permissao');
    ourRequest.onload = function () {
        var ourData = JSON.parse(this.response);
        if (ourRequest.status >= 200 && ourRequest.status < 400) {
            if(ourData !== null || ourData !== ""){
                sessionStorage.setItem('permissao',ourData);
            }
        } else {
            console.log("We connected to the server, but it returned an error.");
        }
    };
    ourRequest.onerror = function() {
        alert("Login ou/e Senha incorreto(s)");
        console.log("Connection error");
    };
    ourRequest.setRequestHeader('Content-type','application/json;charset=utf-8');
    var parametro = {};
    parametro.id = sessionStorage.getItem('idSession');
    ourRequest.send(JSON.stringify(parametro),false);
}
