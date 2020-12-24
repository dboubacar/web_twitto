function makeConnexion(){
    $('head link').remove();

    $('head').append('<link id="fcss" rel="stylesheet" href="./css/connexion.css" />');

    var s="";

    s+='<body>';
    
    s+=' <header> </header>';
    s+='<div id="middle">';
    s+='  <div class="element"><div id="s"><div id="s1"> <div class="s11"><img src="./icon/searchs.svg" /></div>';
	s+='<div class="s12">Suivez vos passions</div>';
					 
	 s+='</div><div id="s2"><div class="s21"><img src="./icon/people.svg" /></div>';
	s+='<div class="s22">Decouvrez ce dont les gens parlent</div></div><div id="s3">';
	 s+='<div class="s31"><img src="./icon/message.svg" /></div><div class="s32">Rejoingnez la conversation</div></div></div></div>';
     s+='<div class="element">';
        	 
    s+='<div id="first"><form onsubmit="return connexion(this);">  <div class="titre"><h1>Twittor</h1></div><div class="ouvrir"> <h3>Découvrez ce qui se passe dans la fac en temps réel</h3>';
	s+='</div><div class="idlogin"><span>Login : </span><input id="login"  type="text"  placeholder="Login"/></label></div>';
	s+='<div class="idpsass"><spam>Mot de passe :</spam><input onblur="verifMdp(this)" id="pass" placeholder="Mot de Passe" type="password"></div>';
	s+='<div id="merror"> </div>';
	s+='<div class="valid"><input id="submit" type="submit" value="Connexion"> </div><div class="links">';
	
	s+='<div id="link1">Mot de passe perdu</div><div id="link2">Pas encore inscrit</div></div></form></div></div></div>';  
    s+='<footer><a href="#"> Copyright 1999-2018 by Refsnes Data. All Rights Reserved.</a></footer></body>';

$('body').html(s);
}

