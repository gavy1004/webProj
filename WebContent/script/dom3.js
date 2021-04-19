var names = []; // 배열 요소가3개 
names[0] = '유정모';
names.push('구자혁'); // push 마지막 위치에 추가
names.push('석정원');
names.pop(); // pop 마지막 위치 제거 .석정원삭제
delete names[0]; // 요소 정보 삭제 . empty 상태
names.shift(); // 처음 위치제거
names.unshift('김이담'); //  처음위치 삽입
names.push('김가비');
names.push('심코코');
names.push('박보리');
names.push('임야용');
names.shift(); //김이담 삭제
names.unshift('성진아');

var returnVal = names.map(function (val, idx, ary) { //요소를 받아서 또 다른 기능을 추가
    var person = {}; //{}object type 선언
    person.name = val;
    person.age = idx;

    return person; //object리턴
});
console.log(returnVal);
console.log('=============')

var returnFil = returnVal.filter(function (val, idx, cary) { // true,false 값 리턴
    return idx % 2 == 0;
});

// var show = document.getElementById('show');
// var ulTag = document.createElement('ul');
// show.appendChild(ulTag);

// names.forEach(function(val, idx, ary) {  //function 메소드 사용가능
//     console.log(`names 요소 : ${val},${idx},${ary}`);
//     callback값의 첫번쨰요소는 첫번째 값이다, 두번쨰 요소는 인덱스
//     console.log('names 요소 : ' + i);
//     var liTag = document.createElement('li');
//     liTag.innerHTML = val;   // <li>성진아</li>
//     ulTag.appendChild(liTag); 
// });

console.log(returnFil);