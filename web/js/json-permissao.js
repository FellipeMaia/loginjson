var el = document.getElementById("page-links");
var arreyComPermissao = sessionStorage.getItem('permissao').split(',');
var arreyElentos = el.querySelectorAll("a");
var arreySemPermissao = [];
for(var i = 0; i< arreyElentos.length; i++){
    var nome = arreyElentos[i].getAttribute("name")
    if(!arreyComPermissao.includes(nome)){
        arreySemPermissao.push(arreyElentos[i]);
    }
}
for(var i = 0;i< arreySemPermissao.length; i++){
    el.removeChild(arreySemPermissao[i]);
}
