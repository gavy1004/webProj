/**
 * variable2.js
 */

var tOn = document.getElementById('turnOn');
tOn.onmouseover = function() {
	document.getElementById('show').src = 'https://www.w3schools.com/js/pic_bulbon.gif'
}
var tOff = document.getElementById('turnOff');
tOff.onmouseover = function() {
	document.getElementById('show').src = 'https://www.w3schools.com/js/pic_bulboff.gif'
}

var light = document.getElementById('show');
light.onmouseover = function() {
	document.getElementById('show').src = 'https://www.w3schools.com/js/pic_bulbon.gif'
}
light.onmouseout = function() {
	document.getElementById('show').src = 'https://www.w3schools.com/js/pic_bulboff.gif'
}

