//form생성
var formTag = document.createElement('form');
formTag.setAttribute('action', 'login.jsp');
formTag.setAttribute('method', 'get');

//label id,passwd :
var id = document.createTextNode('id');
var passwd = document.createTextNode('passwd');

//input id,password :
var inputId = document.createElement('input');
inputId.setAttribute('type', 'text');
inputId.setAttribute('name', 'id');
var inputPw = document.createElement('input');
inputPw.setAttribute('type', 'password');
inputPw.setAttribute('name', 'passwd');

//label send,reset :
var inputSend = document.createElement('input');
inputSend.setAttribute('type', 'submit');
inputSend.setAttribute('value', 'Send');
var inputCancle = document.createElement('input');
inputCancle.setAttribute('type', 'reset');
inputCancle.setAttribute('value', 'Cancle');

formTag.appendChild(id);
formTag.appendChild(inputId);
formTag.appendChild(passwd);
formTag.appendChild(inputPw);
formTag.appendChild(inputSend);
formTag.appendChild(inputCancle);

document.body.appendChild(formTag);