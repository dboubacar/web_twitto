var localdb=[];
var follows=[];
function Message(id, login, text, date,comments) {
	this.id = id;
	this.login = login;
	this.text = text;
	this.date = date;
	if(comments==undefined){
	 comments=[];
	}
	this.comments=comments;
	
}
Message.prototype.getHtml = function() {
	    var modif='<img  class="modf" onclick="fdelete('+this.id+')" src="./icon/delete.svg" />';
	    modif+='<img class="modf" onclick="fedit('+this.id+')" src="./icon/edit.svg" />';
		var s='<div id="message'+this.id+'" class="ms1">';
		s+='<span class="iduser">'+this.login+'</span> <span class="datepost">'+this.date+'</span><br/>';
		s+=this.text;
		s+='<input type="button" onclick="listenerBmodal('+this.id+')" value="Reponses"/>';
		if(this.login==env.login){
			s+=modif;
		}
		s+= '</div>';
		return s;
}
function Commentaire(id,login,text,date){
	this.id=id;
	this.login=login;
	this.text=text;
	this.date=date;
}
Commentaire.prototype.getHtml=function(){
	var sc='';
	sc='<div class="cmt"><strong>'+this.login+' :</strong>'+this.date+'<p>'+this.text+'</p> </div>';
    return sc;
}

function User(idu,login,mdp,nom,prenom){
		this.idu=idu;
		this.login=login;
		if(nom==undefined || prenom==undefined || mdp==undefined){
			nom="";
			prenom="";
			mdp=mdp;

			
		}
		
		this.nom=nom;
		this.prenom=prenom;
		this.mdp=mdp;
	
}
function defaultMessage(){
		 
		   var user1=new User(1,"Oumar1");
		   var user2=new User(2,"Jean2");
		   var user3=new User(3,"chris3");
		   var user4=new User(4,"Diallo4");
		   var user5=new User(5,"Mari5");
		   var user6=new User(6,"Matheo6");
		   var user7=new User(7,"Solitaire7");
		  //  alert(user);
		
		  follows[1]=[2,5,7,3]; 
		  follows[2]=[1,3,5]; 
		  follows[3]=[7,6,4,1,5];
		  follows[4]=[1,2];
		c1=new Commentaire(1,user2,"Toute l\’eau utilisee t\'aurais pu prendre 4 bain !",'22/02/2018');
		c2=new Commentaire(2,user3,"t es vraimentconne mddr ca se voit t en cap coiffure!",'22/07/2018');
		c3=new Commentaire(3,user4,"Mais pourquoi ils mettent du plastique dans de la terre, aussi?",'22/05/2018');	
		var com=[c1,c2,c3];
		
		localdb[5] = new Message(5, user6, "Sans l humilité vous nêtes rien, vous penser que tout vous est dû et que vos récompenses ne sont que le fruits de vos efforts.\
											Détrompez vous lorsque vous faites des efforts c est l univers tout entier qui concourt a votre récompense afin que la récolte soit digne de la semance.",'22/05/2018');
		localdb[4] = new Message(4, user5, "Ne confondons pas le fait d être exigeant, avec celui d être capricieux. L exigence doit nous pousser à élever nos normes, et à nous améliorer, pour être la personnification de ce que nous exigeons.",'22/05/2018');
		localdb[3] = new Message(3, user4, "Un couple devient réellement épanouie lorsque chacun accepte que son autre moitié est différent de lui et que les deux apprennent à faire avec. Autrement ça sera frustration sur frustration, embrouilles sur embrouilles",'22/05/2018');
		localdb[2] = new Message(2, user3, "Si vous vous sentez con dites-vous que ça fait un an que j arrose mes cactus et je viens de me rendre compte qu ils sont en plastique",'22/05/2018',com);
		localdb[1] = new Message(1, user2, "Just because you don t share it on social media, doesn t mean you re not up to big things. Live it and stay low key, privacy is everything.",'22/05/2018');
		localdb[0] = new Message(0, user1, "Je veux juste dire à une personne ce soir que le Mois d avril est le mois de la renonciation.",'22/05/2018');
		//localdb[7] = new Message(7, user1, "Si vous vous sentez con dites-vous que ça fait un an que j arrose mes cactus et je viens de me rendre compte qu ils sont en plastique",'22/05/2018');
// alert(localdb[0].author.idu);
}
