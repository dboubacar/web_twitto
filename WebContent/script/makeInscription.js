function makeInscription(){		
    $('head link').remove();
    $('head').append('<link id="fcss" rel="stylesheet" href="./css/inscription.css" />');
    var s="";
    s+='<body> <header><div id="log"> <a href="#" >Connexion </a> </div>\
        </header><div id="middle"><div id="first"><form  onsubmit="return creatuser(this);"> <div class="titre"><h1>Twittor</h1></div>\
        <div class="ouvrir"> <h3>Rejoignez nous</h3> </div>\
        <div class="nom"><span>Nom : </span><input id="nom" type="text" onblur="verifChamp(this)"  placeholder="votre nom"/></label>\
        </div><div class="prenom"><span>Prenom : </span><input onblur="verifChamp(this)" id="prenom" type="text"  placeholder="votre prenom"/></label>\
        </div><div class="idlogin"><span>Login : </span><input onblur="verifLogin(this)" id="login" type="text"  placeholder="Login"/></label>\
        </div>\
    	<div class="idpass"><spam>Mot de passe :</spam><input onblur="verifChamp(this)" id="pass" placeholder="Mot de Passe" type="password">\
    	<div class="idpassconf"><spam>Mot de passe :</spam><input onblur="verifMdp2(this)" id="passconf" placeholder="Confirmer le Mot de passe" type="password">\
    	</div>\
    	<div id="merror"> </div>\
    	<div class="valid"><input id="submit" onclick="creatuser();" type="button" value="Inscription"> \
        </div></form></div></div> </body>';
    $('body').html(s);

}