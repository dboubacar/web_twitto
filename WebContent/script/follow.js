function ListeAbonm(id_user){
	//alert("ewlll");
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//ListeAbonm",
			datatype:"json",
			data:"key="+env.key+"&id="+id_user,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				//alert("Follows");
				//alert("Welcomsucc")
				respListeAbonm(rep);	
			}
		});
	} 
	else {
	
	}	
}
	
	
function respListeAbonm(rep){
	//alert("Welcom");
	var friend = rep.abonm;
	//alert("Follows");
	for (var i=0;i<friend.length;i++){
		// alert(friend[i].nom);
		//alert(friend[0].id_user);
		$("#mesAbm").append('<li><a href="#" onclick="profil('+friend[i].id_user+');">'+friend[i].nom+' '+friend[i].prenom+'</a><input onclick="removeORadd('+friend[i].id_user+',this);"  class="babon" type="button" value="Desabonner" /></li>');
		// $("#mesAbm").append('<li>'+friend[0].id_user+'</li>');	
 	}
}




function ListeAbonnes(id_user){
	//alert("ewlll");
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//ListeAbonnes",
			datatype:"json",
			data:"key="+env.key+"&id="+id_user,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				//alert("Welcomsucc")
				//alert("Followers");
				respListeAbonnes(rep);	
			}
		});
	} 
	else {
	
	}	
}

function respListeAbonnes(rep){
	//alert("Followers");

	var friend = rep.abonnes;
	
	
	for (var i=0;i<friend.length;i++){
		if(env.follows.includes(friend[i].id_user)){
		$("#mesAbm").append('<li><a href="#" onclick="profil('+friend[i].id_user+');">'+friend[i].nom+' '+friend[i].prenom+'</a><input onclick="removeORadd('+friend[i].id_user+',this);"  class="babon" type="button" value="Desabonner" /></li>');
			}
		else{
		$("#mesAbm").append('<li><a href="#" onclick="profil('+friend[i].id_user+');">'+friend[i].nom+' '+friend[i].prenom+'</a><input onclick="removeORadd('+friend[i].id_user+',this);"  class="babon" type="button" value="Suivre" /></li>');
		
		}
	}
}

function removeORadd(id,k){
	alert("suivreOrsupp");
	if(k.value=="Suivre"){
		addFriend(id,k);
	}else{
		removeFriend(id,k);
	}
	 getStats(env.id);
}

function addFriend(id,k){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//AddFriend",
			datatype:"json",
			data:"key="+env.key+"&id_friend="+id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				k.value="Desabonner";
			}
		});
	} 
	else {
		
	}	
}

function removeFriend(id,k){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//RemoveFriend",
			datatype:"json",
			data:"key="+env.key+"&id_friend="+id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				k.value="Suivre";
			}
		});
	} 
	else {
		
	}	
}

function profil(id){
	alert(id);
}

function getStats(id){
	//alert("stats");
	if(id<0){
		id=env.id;
	}
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//GetStats",
			datatype:"json",
			data:"key="+env.key+"&id="+id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				//alert("ok");
				respGetStats(rep);	
			}
		});
	} 
	else {
	
	}
		
	
}


function getFollows(){
	
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//GetFollows",
			datatype:"json",
			data:"key="+env.key+"&id="+env.id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				respGetFollows(rep);	
			}
		});
	} 
	else {
	
	}
		
	
}

function respGetFollows(rep){
	env.follows=rep.follows;
	//alert(env.follows.length);
	//alert(env.follows.length);
}


function getFollowers(){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//GetFollowers",
			datatype:"json",
			data:"key="+env.key+"&id="+env.id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
			},
			success: function (rep) {
				//alert("wok");
				respgetFollowers(rep);	
			}
		});
	} 
	else {
	
	}
		
	
}

function respGetStats(rep){
	//alert("Succes");
	var ntweets=rep.ntweets;
	//alert(ntweets);

	var nfollows=rep.friend.follows;
	//alert(nfollows);
	var nfollowers=rep.friend.followers;
	var nom=rep.friend.nom;
	var prenom=rep.friend.prenom;
	//alert(nom+" "+prenom);
	$("#ntweets").text(ntweets);
	$("#nfollows").text(nfollows);
	
	$("#nfollowers").text(nfollowers);
	$("#nompre").text(nom+" "+prenom);
	$("#plog").text(nom+" "+prenom);


	//alert(ntweets);
	//alert(nfollows);
	//alert(nfollowers);

}

function respgetFollowers(rep){

	//alert("Followers");
	env.followers=rep.followers;
	//alert(env.followers.length);
}

function ListTweets(id_user){
      //getFollows();
	  //getFollowers();
	//alert("Liste");
	$((makeMainPanel(id_user)));
	//alert(env.follows.length);
	
	
}