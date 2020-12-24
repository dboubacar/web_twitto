function creatuser(){
	
	if(verif_inscription()){
		var login=document.getElementById("login").value;
		var nom=document.getElementById("nom").value;
		var prenom=document.getElementById("prenom").value;
		var p1=document.getElementById("pass").value;
		var p2=document.getElementById("passconf").value;
		if(p1==p2){
			verifLoginExist1(login);
		}
	}else{
	    alert("ko");
	}
	
}



function verifLogin(login){
	if(login.value.length<3){
		funcErroI(login,true);
		return false;
	}else{
		funcErroI(login,false);
		verifLoginExist(login);
		return true;

		
	}		
}

function verifChamp(champ){
	if(champ.value.length<3){
		funcErroI(champ,true);
		return false;
	}else{
		funcErroI(champ,false);
		return true;
	}		
}

function funcErroI(champ,error){
	if(error){
		champ.style.backgroundColor="red";
	}else{
		champ.style.backgroundColor="";
	}	
}


function verifMdp2(mdp){
	
	if(mdp.value.length<3){
		funcErroI(mdp,true);
		return false;
	}else{
		//alert("fuck");
		first_mdp=document.getElementById("pass").value;
        if(first_mdp!=mdp.value){
        	$('#merror').text("Mot de passe non identiques").css("color" ,"red");
			funcErroI(mdp,true);
        }else{
        	$('#merror').text("");
        	funcErroI(mdp,false);
        	return true;
        }
	}		
}

function verif_inscription(){
	//alert("Pret");
	var n=verifChamp(document.getElementById("nom"));
	var p=verifChamp(document.getElementById("prenom"));
	var log=verifLogin(document.getElementById("login"));
	var p1=verifChamp(document.getElementById("pass"));
	var p2=verifMdp2(document.getElementById("passconf"));
	if(n==true && p==true && log==true && p1==true && p2==true){
		if(document.getElementById("pass").value==document.getElementById("passconf").value){
			return true;
		}else{
        	$('#merror').text("Mot de passe non identiques").css("color" ,"red");
        	return false;
		}	
	}
	return false;
}




