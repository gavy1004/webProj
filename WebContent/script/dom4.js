var ps = document.querySelector('div>p');
ps.textContent='hello';
ps.
console.log(ps );
ps.forEach(function(val){
        console.log(val);
        val.textContent='<h1>Hello</h1>';
        val.style.backgroundcolor = 'green';
});
