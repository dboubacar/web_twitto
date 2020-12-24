
function getFromLocalDb(from,idMin,idMax,nbMax) {
	var f=[];
	var nb=0;
	var tab=[];
	if(from>0){ f=follows[from];}
	
	
	for(var i=localdb.length-1;i>=0;i--){
		if(nb>=nbMax && nbMax>0) break;
		if(idMax<0||(localdb[i].id>idMin && localdb[i].id<idMax)){
			if(from<0 || localdb[i].author.idu==from || f.includes(localdb[i].author.idu)){
				console.log("kkk");
						tab.push(localdb[i]);
						nb++;
			}
		}
		
	}	
		/*for(var i=1;i<localdb.length;i++){
			tab[i]=localdb[i];
			
		}*/
	return tab;
}
function affiche(){
	defaultMessage();
	//alert(localdb[0].author.idu);
	t=getFromLocalDb(1,0,-1,-1);
	//defaultMessage();
	var s="";
	for(var i=0;i<t.length;i++){
			s+=t[i].getHtml();
			
		}
		

	$('#allmsg').html(s);


}
function modal(idmsg){

/* local var author=localdb[idmsg].author;
var text=localdb[idmsg].text;
 var sm='<div id="myModal" class="modal"> <div class="modal-content">';
     sm+='<div class="modal-header">';
     sm+='<span class="close" onclick="fermer()">&times;</span>';
     sm+='<img src="../icon/box.svg" /> <span >'+author+'</span>';
     sm+='<p >'+text+'</p>';
     sm+='</div><div class="modal-body">';
     sm+='<form method="post" action="traitement.php">';
     sm+='<textarea name="noms" id="ameliorer" rows="2" cols="50"></textarea>';
     sm+='<input type="submit" value="repondre" />   </form></div>';
     sm+='<div class="modal-footer">';
     sm+=getAllComment(idmsg)
     sm+='</div></div></div>';*/
	var author=env.msg[idmsg].login;
	var text=env.msg[idmsg].text;
	
	 var sm='<div id="myModal" class="modal"> <div class="modal-content">';
	     sm+='<div class="modal-header">';
	     sm+='<span class="close" onclick="fermer()">&times;</span>';
	     sm+='<img src="./icon/box.svg" /> <span >'+author+'</span>';
	     sm+='<p >'+text+'</p>';
	     sm+='</div><div class="modal-body">';
	     sm+='<form onsubmit="return newCom(this,'+idmsg+');">';
	     sm+='<textarea name="noms" id="ameliorer" rows="2" cols="50"></textarea>';
	     sm+='<input class="bposter" type="submit" value="Poster" />   </form></div>';
	     sm+='<div class="modal-footer">';
	     sm+=getAllComment(idmsg);
	     sm+='</div></div></div>';
	     //alert(sm);
return sm;

}
function listenerBmodal(idmsg){
	//var t=modal(idmsg);
	//$("#allmsg").append(t);
//alert(env.msg[idmsg].id);
      // $("#myModa
	var t=modal(idmsg);
	///$("#allmsg").append(t);
	$("#ntext").prepend(t);

    $("#myModal").css("display","block");
	

}
function fermer(){
	 $("#myModal").remove();
}
function getAllComment(idmsg){
	//alert(env.msg[idmsg].comments.length);
	//var k=env.msg[idmsg].comments[0];
     //alert(env.msg[k].getHtml());
	s="";
   for(var i=env.msg[idmsg].comments.length-1;i>=0;i--){
		var k=env.msg[idmsg].comments[i];
	    //alert(k);
		s+=env.msg[k].getHtml();
	}
	//alert(s);
	return s;
}		
	
	

	
function setCookie(sName, sValue) {
    var today = new Date(), expires = new Date();
    expires.setTime(today.getTime() + (365*24*60*60*1000));
    document.cookie = sName + "=" + encodeURIComponent(sValue) + ";expires=" + expires.toGMTString();
}

function getCookie(sName) {
    var oRegex = new RegExp("(?:; )?" + sName + "=([^;]*);?");

    if (oRegex.test(document.cookie)) {
            return decodeURIComponent(RegExp["$1"]);
    } else {
            return null;
    }
}
	
