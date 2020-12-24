
function connexion(f){
	var v=verifFormCon(f);
	if(v){
		alert("Connexion en cours................");
		connecte(f.login.value, f.pass.value);
		return false;
	}else{
		$('#merror').text("error de parametres").css("color" ,"red");
		return false;
	}
}
function verifFormCon(f){
	var p=verifPseudo(f.login);
	var m=verifMdp(f.pass);
	if(p==true && m==true){
		return true;
	}else{
		return false;
	}
}

function funcErro(champ,error){
	if(error){
		champ.style.backgroundColor="red";
	}else{
		champ.style.backgroundColor="";
	}	
}

function verifPseudo(pseudo){
	if(pseudo.value.length==0){
		funcErro(pseudo,true);
		return false;
	}else{
		funcErro(pseudo,false);
		return true;
	}		
}

function verifMdp(mdp){
	if(mdp.value.length==0){
		funcErro(mdp,true);
		return false;
	}else{
		funcErro(mdp,false);
		return true;
	}
	
}
