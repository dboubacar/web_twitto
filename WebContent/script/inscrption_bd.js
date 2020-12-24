function verifLoginExist(log) {
	var login=log.value;
	if (!env.noConnection) {
		//alert("connexion");
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Twitto//UserExist",
			data : "login="+login,
			datatype : "json",
			error : function(jqXHR, textStatus, errorThrown) {
			  alert(textStatus);
				$('#merror').text("verifier votre connection!").css("color" ,"red");
			//   trouv=false;
			},
			success : function (rep) {
				if (rep.req == -1) {
					$('#merror').text("");
					//alert(trouv);
				} else {
					 funcErroI(log,true);

					$('#merror').text("Ce login Existe!").css("color" ,"red");
				} 
				
			}
		});
	} else {
		$('#merror').text("verifier votre connection!").css("color" ,"red");

	}

}

function creat_User(login,password,nom,prenom){
	if (!env.noConnection) {
			//alert("connexion");
			$.ajax({
				type : "GET",
				url : "http://localhost:8080/Twitto//CreateUser",
				data : "login="+login+"&password="+password+"&nom="+nom+"&prenom="+prenom,
				datatype : "json",
				error : function(jqXHR, textStatus, errorThrown) {
				  alert(textStatus);
					
				},
				success : function (rep) {
					//alert("succccccccc");
					//alert(rep.codError);
					if(rep.req==1){
						alert("utilisateur cree avec succes");
						alert("Connecter vous !");
					   	  makeConnexion();


					}
					
				}
			});
		} else {
			
			alert("mode Locale");
	}
}


function verifLoginExist1(login) {
	if (!env.noConnection) {
		//alert("connexion");
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Twitto//UserExist",
			data : "login="+login,
			datatype : "json",
			error : function(jqXHR, textStatus, errorThrown) {
			  alert(textStatus);
				$('#merror').text("verifier votre connection!").css("color" ,"red");
			//   trouv=false;
			},
			success : function (rep) {
				if (rep.req == -1) {
					var nom=document.getElementById("nom").value;
					var prenom=document.getElementById("prenom").value;
					var password=document.getElementById("pass").value;
					creat_User(login,password,nom,prenom);
					//alert(trouv);
				} else {
					 funcErroI(log,true);

					$('#merror').text("Ce login Existe!").css("color" ,"red");
				} 
				
			}
		});
	} else {
		$('#merror').text("verifier votre connection!").css("color" ,"red");

	}

}
