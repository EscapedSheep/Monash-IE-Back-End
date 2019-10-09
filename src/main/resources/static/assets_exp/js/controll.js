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
})