$(document).ready(function(){
	$(".banner ul.lista_banner").slick({
		autoplay: true,
		dots: false,
		arrows: false,
		slidesToShow: 1,
		slidesToScroll: 1,
		infinite: true,
		autoplaySpeed: 2000,
		responsive: [
			
		  {
			breakpoint: 1210,
			settings: {
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  infinite: true,
			  dots: false
			}
		  },
			{
			breakpoint: 640,
			settings: {
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  infinite: true,
			  dots: false
			}
		  },
			{
			breakpoint: 320,
			settings: {
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  infinite: true,
			  dots: false
			}
		  }
			
		]
	});
});


