$(function(){
    var $play = document.getElementById('play');
	$play.addEventListener('click',function () {
                    window.location.href="/game";
				});
    var $explore = document.getElementById('explore');
	$explore.addEventListener('click',function () {
                    window.location.href="/explore";
				});
    var $educate = document.getElementById('educate');
	$educate.addEventListener('click',function () {
                    window.location.href="/educate";
				});
    var $travel = document.getElementById('travel');
	$travel.addEventListener('click',function () {
                    window.location.href="/travel";
				});
});

function upload(num) {
    $label = $('#label' + num);
    $form = $('#form' + num);
    $btn = $('#tree' + num);
    $path = $('#path' + num);
    $label.text("uploading...");
    $.ajax({
  			    url: '/ocr',
  			    type: 'POST',
  			    cache: false,
  			    data: new FormData($form[0]),
  			    processData: false,
  			    contentType: false
  			}).done(function(res) {
  				console.log(res);
  				if (res.code === 200){
                    $label.text("Congratulation! You found it!");
                    $btn.css({animation:'none'});
                    $path.css({fill:'#66ff00'});
                    $btn.prop('disabled',true);
                }
  				else{
                    if (res.code === 300){
                        $label.text("Please upload the image of name board of the tree~");
                        setTimeout(function(){
                            $label.text("Choose an image...");
                        },4000);
                    }
                    else {
                        $label.text("Seems you didn't find it! Try again!");
                        setTimeout(function(){
                            $label.text("Choose an image...");
                        },4000);
                    }
                    
                }
  			}).fail(function(res) {
  				$label.text("Seems you didn't find it! Try again!");
  				setTimeout(function(){
                	$label.text("Choose an image...");
                },4000);
                
  			});
};

var file1 = document.getElementById('file1');
file1.onchange = function(e) {
    upload('1');
};

var file2 = document.getElementById('file2');
file2.onchange = function(e) {
    upload('2');
};

var file3 = document.getElementById('file3');
file3.onchange = function(e) {
    upload('3');
};

var file4 = document.getElementById('file4');
file4.onchange = function(e) {
    upload('4');
};

var file5 = document.getElementById('file5');
file5.onchange = function(e) {
    upload('5');
};

var file6 = document.getElementById('file6');
file6.onchange = function(e) {
    upload('6');
};

var file7 = document.getElementById('file7');
file7.onchange = function(e) {
    upload('7');
};

var file8 = document.getElementById('file8');
file8.onchange = function(e) {
    upload('8');
};

var file9 = document.getElementById('file9');
file9.onchange = function(e) {
    upload('9');
};

var file10 = document.getElementById('file10');
file10.onchange = function(e) {
    upload('10');
};