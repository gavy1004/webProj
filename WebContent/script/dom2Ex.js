function createTitle() {
    var trTag = document.createElement('tr');
    for (var i = 0; i < arguments.length; i++) {
        var td1 = document.createElement('th');
        td1.innerHTML = arguments[i];
        trTag.appendChild(td1);
    }

    tbl.appendChild(trTag);
}
//회원리스트에 회원정보를 보여주는 Func
function createData() {
    for (var person of persons) { // for ~ of 배열의 반복
        var trTag = document.createElement('tr');
        trTag.setAttribute('id', person.id);
        trTag.onmouseover = mouseOverFnc;
        trTag.onmouseout = mouseOutFnc;

        for (var field in person) { // for ~ in 필드의 요소 반복
            if (field == 'id') {
                var tdTag = document.createElement('td');
                tdTag.onclick = modifyFunc;

                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            } else if (field == 'name') {
                var tdTag = document.createElement('td');
                var link = document.createElement('a');
                // link.setAttribute('href', 'dom.jsp?name=Hong&id=user1&score=80&gender=여');
                link.setAttribute('href', 'dom.jsp?name='+person.name+'&id='+person.id+'&score='+person.score+'&gender='+person.gender);
                link.innerHTML = person.name;
                tdTag.appendChild(link);
                trTag.appendChild(tdTag);
            } else {
                var tdTag = document.createElement('td');
                var text = document.createTextNode(person[field]);
                trTag.appendChild(tdTag);
                tdTag.appendChild(text);
            }

        }
        var btn = document.createElement('button');
        btn.innerHTML = '삭제';
        btn.onclick = deleteRow;

        var tdTag = document.createElement('td');
        tdTag.appendChild(btn);
        trTag.appendChild(tdTag);

        tbl.appendChild(trTag);
    }
}

function mouseOverFnc() {
    this.style.backgroundColor = "yellow";

}

function mouseOutFnc() {
    this.style.backgroundColor = '';

}

function deleteRow() {
    this.parentNode.parentNode.remove();

}

function modifyFunc() {
    console.log(this);
    var idVal = this.innerHTML;
    var nameVal = this.previousSibling.firstChild.innerHTML;
    var scoreVal = this.nextSibling.innerHTML;
    var genVal = this.parentNode.childNodes[3].innerHTML;
    console.log(idVal, nameVal, scoreVal, genVal);

    document.getElementById('name').value = nameVal;
    document.getElementById('id').value = idVal;
    document.getElementById('score').value = scoreVal;
    var genders = document.querySelectorAll('input[name="gender"]');
    for (var i = 0; i < genders.length; i++) {
        if (genders[i].value == genVal) {
            genders[i].checked = true;
        }
    }
}

function saveBtnFnc() {
    var iName = document.getElementById('name');
    var iId = document.querySelector('input[name="id"]');
    var iScore = document.getElementsByTagName('input')[2];
    var iGender = document.querySelector('input[name="gender"]:checked');

    var tr = document.createElement('tr');
    tr.setAttribute('align', 'center'); //속성
    tr.onmouseover = mouseOverFnc; // 마우스를 올리면 배경색 라벤더
    tr.onmouseout = mouseOutFnc; // 마우스를 내리면 배경색 없음

    // name
    var td = document.createElement('td');
    td.innerHTML = iName.value;
    tr.appendChild(td);

    // id
    var td = document.createElement('td');
    td.innerHTML = iId.value;
    tr.appendChild(td);

    // score
    var td = document.createElement('td');
    td.innerHTML = iScore.value;
    tr.appendChild(td);

    // gender
    var td = document.createElement('td');
    td.innerHTML = iGender.value;
    tr.appendChild(td);

    //button
    var td1 = document.createElement('td'); // button이 들어갈 새로운 td추가
    var buttons = document.createElement('button'); // button 생성
    buttons.innerHTML = "삭제"; // button Label 넣어주기

    buttons.onclick = deleteRow; // button 클릭 시 삭제

    td1.appendChild(buttons); // td1에 button 넣어주기
    tr.appendChild(td1); // tr에 td1 추가
    tbl.appendChild(tr);
}
//수정버튼을 클릭했을떄 변경
function modifyBtnFunc() {
    var id = document.getElementById('id').value;
    // 사용자가 변경한 값을 반영
    var name = document.getElementById('name').value;
    var score = document.getElementById('score').value;
    var gender = document.querySelector('input[name="gender"]:checked').value;
   
    var targetTr = document.getElementById(id); //id로 tr찾아오려고
    console.log(targetTr);
    // <a href='dom.js?name=?&id=?&score=?&gender=?'>
    targetTr.children[0].innerHTML = '<a href="dom.jsp?name='+name+'&id='+id+'&score='+score+'&gender='+gender+'">'+name+'</a>';
    targetTr.children[2].innerHTML = score;
    targetTr.children[3].innerHTML = gender;



}