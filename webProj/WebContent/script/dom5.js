var buttons = document.getElementsByTagName('button') //요소를 태그 이름으로 가져온다
for (var i = 0; i < buttons.length; i++) {
    buttons[i].setAttribute('onClick', 'showTable()');
}
var p1 = {
    name: '성진아',
    score: 80,
    gender: '여'
}
var p2 = {
    name: '김수민',
    score: 83,
    gender: '여'
}
var p3 = {
    name: "장재우",
    score: 85,
    gender: '남'
}
for (var field in p3) {
    console.log(field, p3[field]);
}
var persons = [p1, p2, p3];
for (var p of persons) {
    console.log(`name의 요소 ${p.name}`);
    console.log(`name의 요소 ${p.score}`);
}

function showTable() {
    var tableTag = document.createElement('table'); //테이블 태그
    tableTag.setAttribute('border', '1'); //테이블 속성
    for (var person of persons) { // 배열에서 반복
        var tr1 = document.createElement('tr');
        for (var field in person) { //object에서 반복
            var td1 = document.createElement('td');
            td1.innerHTML = person[field];
            tr1.appendChild(td1);
        }
        tableTag.appendChild(tr1);
    }
    var show = document.getElementById('show');
    show.appendChild(tableTag);

}


// number 1 ~ 10
// filter, map , forEach -> 짝수만 걸러내도록 -> evenVal;
// evenVal -> n * 3; -> mapVal;
// mapVal -> console.log 출력 

var numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
//arrow function
numbers.filter((val) => val % 2 == 0)
    .map((val) => val * 3)
    .forEach((val) => console.log(val));

function sum(a, b) { // 아래와 같은 표현
    return a + b;
}
var sum = (a, b) => a + b; // arrow function
sum(10, 20);


// var evenVal = numbers.filter(function (val, idx, ary) {
//     return idx % 2 == 1;
// });

// var mapVal = evenVal.map(function (val, idx, ary) {
//     return val * 3;
// });

// console.log(evenVal);
// console.log(mapVal);

// mapVal.forEach(function (val, idx, ary) {
//     console.log(`요소는 : ${val} , ${idx}, ${ary}`);
// });