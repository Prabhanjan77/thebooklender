$(document).ready(function() {
	console.log("print it anirudh");
  $("#loginButton").click(function(event) {
    event.preventDefault();
    var loginForm = document.forms.namedItem("loginForm");
    var formData = {}
    for (i = 0; i < loginForm.length - 1; i++) {
      formData[loginForm.elements[i].name] = loginForm.elements[i].value;
      console.log("print it prashant");
    }
    console.log("print iteeeeee");
    var formJson = JSON.stringify(formData);
    console.log(formJson);
    var url = "http://localhost:8080/thebooklender/api/users/email";
    console.log(url);
    
    jQuery.ajax({
      url: url,
      type: "POST",
      data: formJson,
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      success: function(response) {
        console.log("hello");
        if (response != undefined) {
          for (var key in response) {
            localStorage.setItem(key, response[key]);
          }
          window.location.href = "/thebooklender/";
        } else {
          document.getElementById("amit2").innerText="wrong username or password";
        }
      },
      error: function() {
       
      }
    });
  });
});
