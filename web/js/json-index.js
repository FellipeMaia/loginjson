function load(page){
    $("#included-wrapper").load(page+'?id='+sessionStorage.getItem('idSession')); 
} 

function color(color){
    document.getElementById("content-wrapper").style.backgroundColor = color;
}

function closeSession(){
    var ourRequest = new XMLHttpRequest();
    ourRequest.open('POST', './logout');
    ourRequest.onload = function () {
        if (ourRequest.status === 202) {
            sessionStorage.setItem('idSession',null);
            sessionStorage.setItem('permissao',null);
            window.location.replace('./login.html'); 
        } else {
            console.log("We connected to the server, but it returned an error.");
        }
    };
    ourRequest.onerror = function() {
        alert("Login encerrado!!)");
        console.log("Login encerrado!!");
    };
    ourRequest.send();

    
}

