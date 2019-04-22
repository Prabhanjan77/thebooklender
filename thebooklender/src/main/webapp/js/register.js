$(document).ready(function() {
  $("#registerButton").click(function(event) {
    event.preventDefault();
	var v1=document.getElementById("email2").value;
	var v2=document.getElementById("name1").value;
	var v3=document.getElementById("pass").value;
	var v4=document.getElementById("addr").value;
		//console.log(v1+ " "  +v2+" "+v3);

		var formData = {}
	    formData["email"] = v1;
		formData["name"] = v2;
	    formData["password"] = v3;
	    formData["address"]=v4;
	    var formJson = JSON.stringify(formData);
	    //console.log(formJson);


	var url="/thebooklender/api/buyer/signup";


    jQuery.ajax({
        url: url,
        type: "POST",
        data: formJson,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function(response) {
          var returnedData = JSON.parse(response);
					var x=response;
					console.log(returnedData);
					console.log(x);
					if(x==true){
					window.location.href = "/thebooklender/"
        }
				else {
					document.getElementById("amit1").innerText="user already exists";
				}
			},
        error: function() {
					console.log("error");
					console.log(response);
        }
      });
		});
	});
