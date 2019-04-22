var catId = 1;
var subcatId = 1;
var birthday = 0;
$(document).ready(function() {
//	var data = [{"bdayDiscount":20,"cat_id":1,"desc":"APPLE  MACBOOK","discount":0,"extras":[],"img_url":"17","item_id":17,"name":"IPAD","price":0,"seller_id":1,"subcat_id":11},{"bdayDiscount":20,"cat_id":1,"desc":"APPLE","discount":20,"extras":[{"key":"RAM","value":"512MB"},{"key":"STORAGE","value":"1000TB"},{"key":"COLOR","value":"SPACE_GREY"}],"img_url":"NA","item_id":1,"name":"IPAD","price":100,"seller_id":1,"subcat_id":11},{"bdayDiscount":20,"cat_id":1,"desc":"APPLE  MACBOOK pro","discount":0,"extras":[],"img_url":"18","item_id":18,"name":"IPAD","price":0,"seller_id":1,"subcat_id":11},{"bdayDiscount":20,"cat_id":1,"desc":"APPLE  MACBOOK pro 1","discount":0,"extras":[{"key":"RAM","value":"512MB"},{"key":"STORAGE","value":"1000TB"},{"key":"COLOR","value":"SPACE_GREY"}],"img_url":"19","item_id":19,"name":"IPAD","price":0,"seller_id":1,"subcat_id":11},{"bdayDiscount":20,"cat_id":1,"desc":"APPLE  MACBOOK pro 1","discount":0,"extras":[{"key":"RAM","value":"512MB"},{"key":"STORAGE","value":"1000TB"},{"key":"COLOR","value":"SPACE_GREY"}],"img_url":"20","item_id":20,"name":"IPAD","price":0,"seller_id":1,"subcat_id":11},{"bdayDiscount":20,"cat_id":1,"desc":"APPLE  MACBOOK pro 1","discount":0,"extras":[{"key":"RAM","value":"512MB"},{"key":"STORAGE","value":"1000TB"},{"key":"COLOR","value":"SPACE_GREY"}],"img_url":"21","item_id":21,"name":"IPAD","price":0,"seller_id":1,"subcat_id":11}];
	var URL = 'http://localhost:'+ location.port +'/ooad/category.html';
	catId = getURLParameter('cat_id', URL);
	subcatId = getURLParameter('subcat_id', URL);
	birthday = isBirthday();
	var ctxPath = "http://localhost:" + location.port + "/ooad/api/item/getItemForACategoryAndSubcategory/" + catId + "/" + subcatId + "/" + birthday;
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : ctxPath ,
		dataType : "json", // data type of response
		success : populate,
		error : function() {
	    	 window.location.href = "/ooad/";
		}
	});
				
});

function isBirthday() {
	if (localStorage.getItem("date") != null) {
		var curDate = new Date();
		var DOB = new Date(localStorage.getItem("date"));
		if ( curDate.getDate() == DOB.getDate() && curDate.getMonth() == DOB.getMonth() ) return 1;
	}
	return 0;
}

function populate( data ) {
	console.log(data);
		var i = data.length;
		var txt;
		while( i-- > 0 )
		{
			txt += ' <div class="col-sm-6 col-md-4 wow fadeInUp">' + '<div class="products">' + '<div class="product">' + 
					'<div class="product-image">' + ' <div class="image"> <a href="item.html?item_id=' + data[i].item_id + "&bday=" + birthday + 
					'"><img src="' + "./img/item/download" + '.jpeg" alt=""></a> </div>' + '<!-- /.image -->' + 
					'<div class="tag new"><span>new</span></div>'+ '</div>' + '<!-- /.product-image -->' + 
					'<div class="product-info text-left">' + '<h3 class="name"><a href="item.html?item_id='+ data[i].item_id + "&bday=" + birthday + 
					'">' + data[i].name + '</a></h3>' + '<div class="rating rateit-small"></div>' + '<div class="description"></div>' +
					'<div class="product-price"> <span class="price">' + data[i].price +' </span> <span class="price-before-discount">$' + 
					data[i].price + data[i].price * (data[i].discount / 100) +' </span> </div>' + '<!-- /.product-price --> ' + '</div>' + 
					'<!-- /.product-info -->' + '<div class="cart clearfix animate-effect">' + '<div class="action">' + 
					'<ul class="list-unstyled">' + '<li class="add-cart-button btn-group">' + 
					'<button class="btn btn-primary icon" data-toggle="dropdown" type="button"> <i class="fa fa-shopping-cart"></i> </button>' +
					'<button class="btn btn-primary cart-btn" type="button">Add to cart</button>' + '</li>' + 
					'<li class="lnk wishlist"> <a class="add-to-cart" href="underconstruction.html" title="Wishlist">' + 
					'<i class="icon fa fa-heart"></i> </a> </li>' + '<li class="lnk"> <a class="add-to-cart" href="item.html" title="Compare">' + 
					'<i class="fa fa-signal"></i> </a> </li>'+ '</ul> </div> <!-- /.action -->' + 
					'</div><!-- /.cart --></div><!-- /.product --> </div><!-- /.products --> </div><!-- /.item -->';
		}
		document.getElementById("grid-container").innerHTML = txt;
}

function getURLParameter( sParam , staticURL) {
	var baseURL = (window.location).href;
	if ( baseURL.length == staticURL.length ) {
		window.location.href = "/ooad/";
		return;
	}
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
         var sParameterName = sURLVariables[i].split('=');
         if (sParameterName[0] == sParam)
             return sParameterName[1];
     }
}

function findRange(){
	var min = document.getElementById("min").value;
	var max = document.getElementById("max").value;
	var url = "http://localhost:" + location.port + "/ooad/api/item/priceRange/" + catId + "/" + subcatId + "/" + min + "/" + max + "/" + birthday;
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : url ,
		dataType : "json", // data type of response
		success : populate, // call function populate on success
		error : function() {
			// window.location.href = "/ooad/";
		}
	});
}
