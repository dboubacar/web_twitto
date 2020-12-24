

function makeMainPanel(fromId, fromLogin, query) {
	//$((getFollows()));
	//$((getFollowers()));
	if(fromId==undefined){
		fromId=-1;
	}
	 $('head link').remove();
	 $('head').append('<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">');
	 $('head').append('<link id="fcss" rel="stylesheet" href="./css/main.css" />');
	// alert("bonjour");
		
		
		var s="";
		//en tete
		    s='<body><header><div id="tete"><div class="head"><div id="home"> <a  href="#"  onclick="Home();" >Home</a>\
			</div></div><div class="head"> <div id="logo"> <img src="./icon/logo.svg" /></div> </div> ';
			s+='<div class="head"> <div class="wrap"><div class="search">';
     	    s+='<input type="text" class="searchTerm" placeholder="Vous cherchez quoi?">';
     		s+='<button type="submit" class="searchButton"><i class="fa fa-search"></i></button>';
			s+='</div></div></div><div class="head">';
			s+='<div class="out"><input id="submit" type="button"  onclick="logOut();" value="Deconnexion">'; 
			s+='</div></div></div></header>';
		if(fromId<0){
			s+='<div id="middle"><div class="main"><div id="profil"><div class="tof"><img src="./icon/profil.jpg"/>';
			s+='</div><div class="tof"><p id="nompre" >Boubacar Diallo</p><p>profil</p></div>';
			s+='</div><div id="stats"><div class="tweets"><p class="l"><a href="#" onclick="ListTweets('+env.id+');">Tweets</a></p>';
			s+='<p id="ntweets" class="chiffre1"> 314</p></div><div class="tweets">';
			s+='<p class="l"><a href="#" onclick="listeFollow('+env.id+',1);">Follows</a></p>';
			s+='<p id="nfollows" class="chiffre2"> 364</p></div><div class="tweets">';
			s+='<p class="l"><a href="#" onclick="listeFollow('+env.id+',2);">Followers</a></p>';
		    s+='<p id="nfollowers" class="chiffre3"> 102</p></div>';
			s+='</div></div><div class="main"><div id="ntext">';
			s+='<form onsubmit="return newMessage(this);">';
		    s+='<textarea  id="textmsg" rows="2" cols="50"></textarea>';
		    s+='<input class="bposter" type="submit" value="Poster" />   </form>';
			s+='</div><div id="ltext"><div id="allmsg"></div></div></div>';
			s+='<div class="main"><div id="propos"><h5> A Propos </h5>';
            s+='<p> 2018 Twittor propos Centre d\'assistance Conditions Politique de confidentialité Cookies Informations sur la publicité Marque Blog État';
			s+='du service Applications Offres d\'emploi Marketing Professionnels Développeur</p>';
			s+='</div></div></div></body>';
		}
		else{
			//alerenv.follows.has(4));
			 	s+='<div id="middle"><div id="profile"><p id="plog">  fanta	</p>';
				s+='<div id="s"> <div id="ditw"><div class="ptweets"><p class="pt">Tweets</p>';
				s+='<p id="ntweets" class="pchiffre"> 314</p></div><div class="ptweets">';
				s+='<p class="pt">Abonnements</p><p id="nfollows" class="pchiffre"> 314</p></div>';
				s+='<div class="ptweets"><p class="pt">Abonnes</p><p id="nfollowers" class="pchiffre"> 314</p>';
				s+='</div></div>';
			  //1er cas profil
			  if(fromId==env.id){
				   s+='<div id="bt"><input id="pbsuivre" type="submit" value="Edit" /></div>';
					//s+='</div></div><div id="pallmsg"></div></div> <script>$("#middle").css("display","block");</script></body>';
			  }
			  //2eme cas page d'un follows
			  else if(env.follows.includes(fromId)){
				//  alert(env.follows);
				   s+='<div id="bt"><input id="pbsuivre"  onclick="removeORadd('+fromId+',this);" type="submit" value="Desabonner" /></div>';
			  }
			  else
			  	{
				   s+='<div id="bt"><input id="pbsuivre" onclick="removeORadd('+fromId+',this);" type="submit" value="Suivre" /></div>';
			  	}
			  s+='</div></div><div id="pallmsg"></div></div> <script>$("#middle").css("display","block");</script></body>';
				
		   }
		
		$('body').html(s);
		completeMessages(env.key,fromId,-1,-1,10);
		//completeMessages(env.key, -1, -1, -1, 10);
		getStats(fromId);
		 getFollows();
		 getFollowers();
		
		

		
	
}


function listeFollow(id_user,f){
	
	 $('head link').remove();
	 $('head').append('<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">');
	 $('head').append('<link id="fcss" rel="stylesheet" href="./css/main.css" />');
	// alert("bonjour");
		var s="";
		//en tete
	 s='<body><header><div id="tete"><div class="head"><div id="home"> <a  href="#" onclick="Home();"  >Home </a>\
		</div></div><div class="head"> <div id="logo"> <img src="./icon/logo.svg" /></div> </div> ';
	 s+='<div class="head"> <div class="wrap"><div class="search">';
     s+='<input type="text" class="searchTerm" placeholder="Vous cherchez quoi?">';
     s+='<button type="submit" class="searchButton"><i class="fa fa-search"></i></button>';
	 s+='</div></div></div><div class="head">';
	 s+='<div class="out"><input id="submit" type="submit" value="Deconnexion">'; 
	 s+='</div></div></div></header>';
	 
	 s+='<div id="middle"><div id="profile"><p id="plog">  BOUBACAR DIALLO	</p>';
	 //s+="<p id='titab'> BOUBACAR SIDY </p>";
	 s+='<div id="s"> <div id="ditw"><div class="ptweets"> <p class="pt">Tweets</p>';
	 s+='<p id="ntweets" class="pchiffre"> 314</p></div><div class="ptweets">';
	 s+='<p class="pt">Abonnement</p><p id="nfollows" class="pchiffre"> 314</p></div>';
	 s+='<div class="ptweets"><p class="pt">Abonnes</p><p id="nfollowers" class="pchiffre"> 314</p>';
	 s+='</div></div><div id="bt">';
	 s+='</div></div></div><div id="pallmsg"><ul id="mesAbm"> </ul></div></div> <script>$("#middle").css("display","block");</script></body>';
	 $('body').html(s);
	 if(f==1){
		 ListeAbonm(id_user);
	 }else{
		 ListeAbonnes(id_user);
	 }
	 getStats(env.id);

}
		

function Home(){
	makeMainPanel(-1);
}

