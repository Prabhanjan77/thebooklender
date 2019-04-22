$(document).ready(function() {
  console.log(localStorage);
  $("#anirudh").hide();
  $("#bhai").hide();
  $("#bhai1").hide();
  $("#bhai2").hide();
  $(".username").empty();
  if (localStorage.getItem("user_email") != null) {
   // $(".user").css("display", "inline-block");
    var name = localStorage.getItem("user_name");
   // console.log("user ka name hai ="+name);
   // $("#printName").empty();
    
    $("#afterlogin").show();
    $("#beforelogin").hide();
    $("#registered").show();
    $("#unregistered").hide();
    $(".username").text("Logout");
    $("#printName").text(name);
    // birthday check
    
  } else {
	  $("#unregistered").show();
	  $("#registered").hide();
	  $("#afterlogin").hide();
	  $("#beforelogin").show();
    $(".username").text("Login & Signup");
    $(".user").css("display", "none");
  } // check if storage mobileNum is set
  // jQuery methods go here...
  $(".logInb1").click(function() { //changed from trigger on '._2f5Jjv', as it was the class of 'more' and 'cart' as well
    if (localStorage.getItem("user_email") != null) {
      localStorage.clear();
      window.location.href = "/thebooklender/";
    }
    console.log("click function");
    $("#loginModal").css("display", "block");
    $("#signUpModal1").css("display", "none");
    $("#signUpModal2").css("display", "none");

    //	  $('#signUpModal1').modal('hide');
    //	  $('#signUpModal2').modal('hide');
    //	  $('#loginModal').modal('show');
  });


  var loginModal = document.getElementById('loginModal');
  var signUpModal1 = document.getElementById('signUpModal1');
  /*not required as we r using modal now
  // When the user clicks anywhere outside of the modal, close it
//  window.onclick = function(event) {
//    console.log(loginModal.id);
//    if (event.target == loginModal || event.target == signUpModal1 || event.target == signUpModal2) {
//      $(".uMF2cc").css("display", "none");
//      $("._32LSmx").css("display", "none");
//    }
//  }
*/
  $('.signUpb1').click(function() { //changed to class, as it is also applied for 'change' button once mobile number is entered for signup
    console.log("sign up");
    $("#loginModal").css("display", "none");
    $("#signUpModal1").css("display", "block");
    $("#signUpModal2").css("display", "none");

  });

  $('#continue').click(function() {
    var v99 = $('#email1').val();
    console.log(v99);
    //		alert(mobileNo);
    if(v99=="")
    {
        document.getElementById("amit3").innerText = "this field is empty";

    }
    else{
    var url = "/thebooklender/api/buyer/validate";
    jQuery.ajax({
      url: url,
      type: "POST",
      data: v99,
      dataType: "text",
      contentType: "text/plain; charset=utf-8",
      success: function(response) {

        //var returnedData = JSON.parse(response);
        var x = response;
        //console.log(returnedData);
        console.log("x=" + x);
        if (x == "true") {
          console.log("user already exists. give error");
          document.getElementById("amit3").innerText = "user already exists";

          //window.location.href = "http://localhost:8080/frontend/"
        } else {
        	document.getElementById("signup2").reset();

          console.log("user dont already exists");
          $('#email1').val('');
          $("#loginModal").css("display", "none");
          $("#signUpModal1").css("display", "none");
          $("#signUpModal2").css("display", "block");
          $("#email2").val(v99);
                 }
      },
      error: function() {
        console.log("error");
        console.log(response);
      }
    });
  }});
});