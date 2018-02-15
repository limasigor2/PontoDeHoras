$(function(){
    $(".button-collapse").sideNav();
});

// Fechar side-nave mobile
 $(function(){
     $(".close-nav").sideNav("hide");
 });

 // Fechar side-nav mobile quando clicar
$(".scroll-suave").click(function(){
	$(".close-nav").sideNav("hide");
})