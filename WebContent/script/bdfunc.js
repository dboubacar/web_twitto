
function connecte(login, pass) {
	
	if (!env.noConnection) {
		//alert("connexion");
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/Twitto//LoginUser",
			data : "login="+login+"&password="+pass,
			datatype : "json",
			error : function(jqXHR, textStatus, errorThrown) {
			  alert(textStatus);
				
			},
			success : function (rep) {
				//alert("succccccccc");
				//alert(rep.codError);
				responseConnexion(rep);
			}
		});
	} else {
		alert("mode Locale");
	}
}

function responseConnexion(rep) {
	//var json = rep;
	//mys= JSON.parse(json);
	//var bool=false;
	if (rep.codError == -1) {
		$('#merror').text("Aucun user avec ce login").css("color" ,"red");
		
	} else if (rep.codError == 1) {
		$('#merror').text("Login ou mot de passe incorrects!").css("color" ,"red");
	} else {
		//	bool=true;
	        // alert("Succes connexion");
			env.key=rep.key;
			env.id=rep.id_user;
			env.login=rep.login;
			alert("Bienvenue "+env.login);
	        // alert(rep.key);
	        // alert(rep.id_user);
			makeMainPanel(-1);

	}
	//return bool;
}

function completeMessages(key,from,min,max,nbMax) {	
	//var from=env.from;
	if (env.key != undefined) {
		if (!env.noConnection)  {
			$.ajax ({
				type:"GET",
				url:"http://localhost:8080/Twitto//getMessages",
				datatype:"text/plain",
				data:"key="+key+"&from="+from+"&idMin="+min+"&idMax="+max+"&nbMax="+nbMax,
				datatype : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus);
				},
				success : function (rep) {
					completeMessagesResponse(rep);
				}
			});
		}
	}else{
		alert("hors Ligne");
	}
}

function refreshMessages() {
	//alert("refreshMessages()");
	//alert(env.key);
	//alert(env.from);
	//alert(env.maxId);
	if (env.key != undefined) {
		if (!env.noConnection)  {
			$.ajax ({
				type:"GET",
				url:"http://localhost:8080/Twitto//getMessages",
				datatype:"text/plain",
				data:"key="+env.key+ "&from="+env.from+"&idMin="+env.maxId+"&idMax=-1&nbMax=1",
				datatype : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus);
				},
				success : function (rep) {
					//alert("refreshMessagesresponse()");
					refreshMessagesresponse(rep);
				}
			});
		}
	}
}

function refreshCom(parent_id) {
	
	//alert("refreshMessages()");
	//alert(env.key);
	//alert(env.from);
	//alert(env.maxId);
	if (env.key != undefined) {
		if (!env.noConnection)  {
			$.ajax ({
				type:"GET",
				url:"http://localhost:8080/Twitto//getMessages",
				datatype:"text/plain",
				data:"key="+env.key+ "&from="+env.from+"&idMin="+env.maxId+"&idMax=-1&nbMax=1",
				datatype : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus);
				},
				success : function(rep){
					
					refreshComresponse(rep,parent_id);
				}
					//alert("refreshMessagesresponse()");
					
				
			});
		}
	}
}


function revival(key,value) {
	console.log("in revival");
	if(value.error == 0 ){
		alert("error");
		var o = new Object(value.error);
		return o;
	}
	if(value.parent_id===-1 || value.comments!=undefined){
		//alert("Message "+value._id);
		var m=new Message(value._id,value.login,value.text,value.datem,value.comments);
		
	
		return m;
	}else if(value.parent_id !=undefined && value.parent_id !=-1){
		//alert("com "+value._id);
		var c = new Commentaire(value._id,value.login,value.text,value.datem);
		return c;
	}
	else {
		//var c = new Commentaire(12,"kante","bonjour","12/02/2014");
	
		return value;
	}
}

function completeMessagesResponse(message) {
	var tab = JSON.parse(message,revival).messages;
	
	//alert(tab[0]);
	//alert(tab);
	
	for (var i=tab.length-1; i>=0; i--) {
		//alert("welll");
		//alert(tab[i].id);
	    if(tab[i].comments!=undefined)
	    {   //alert(tab[i].comments);
	    	$("#allmsg").append(tab[i].getHtml());
	    	$("#pallmsg").append(tab[i].getHtml());

	    }
		env.msg[tab[i].id]=tab[i];
		if (tab[i].id > env.maxId) {
			env.maxId = tab[i].id;
		}
		if ((env.minId < 0) || (tab[i].id < env.minId)) {
			env.minId = tab[i].id;
		}
		lastId = tab[i].id;
	   
	}
	env.lastId=lastId;
	
	//$("#message"+lastId).appear();
	//$.force_appear();
	
	//var t=[1,2,3]
	//alert(localdb.length);
	//alert(tab[1]);
	//alert(tab[2]);
	
}

function refreshMessagesresponse(rep){
var tab = JSON.parse(rep,revival).messages;
	
	//alert(tab[0]);
	//alert(tab);
	for (var i=tab.length-1; i>=0; i--) {
		//alert("welll");
		//alert(tab[i].id);
	    if(tab[i].comments!=undefined)
	    {   //alert(tab[i].comments);
	    	$("#allmsg").prepend(tab[i].getHtml());
	    }
		env.msg[tab[i].id]=tab[i];
		if (tab[i].id > env.maxId) {
			env.maxId = tab[i].id;
		}
		
	}
	
}

function refreshComresponse(rep,parent_id){
	//alert("ok");

	var tab = JSON.parse(rep,revival).messages;
		
		//alert(tab[0]);
		//alert(tab);
		
			//alert("welll");
			//alert(tab[i].id);
		    if(tab[tab.length-1].comments==undefined)
		    {   //alert(tab[i].comments);
		    //	alert(tab[tab.length-1].id);
		    	$(".modal-footer").prepend(tab[tab.length-1].getHtml());
		    	env.msg[parent_id].comments.push(tab[tab.length-1].id);
		    }
			env.msg[tab[tab.length-1].id]=tab[tab.length-1];
			env.maxId=tab[tab.length-1].id;
			
			//
			
			
		
		
	}

function ajoutMessage(text){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//AddComment",
			datatype:"json",
			data:"key="+env.key+"&text="+text+"&parent_id=-1",
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
				text="";
			},
			success: function (rep) {
				text="";
				refreshMessages();
				
			}
		});
	} else {
		
	}
	
}

function ajoutCom(text,parent_id){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//AddComment",
			datatype:"json",
			data:"key="+env.key+"&text="+text+"&parent_id="+parent_id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
				text="";
			},
			success: function (rep){
				refreshCom(parent_id) ;
				text="";
			}
		});
	} else {
		
	}
	
}

function fdelete(id){
	if(!env.noConnection) {
		//alert(text);
		$.ajax ({
			type:"GET",
			url:"http://localhost:8080/Twitto//DeleteMessage",
			datatype:"json",
			data:"key="+env.key+"&idmsg="+id,
			error:function(jqXHR, textStatus, errorThrown) {
				alert(textStatus);
				
			},
			success: function (rep) {
				
				respdelete(id);
				
			}
		});
	} else {
		
	}
}

function fedit(id){
	alert(id);
	
}

function respdelete(id){
	
	
//	if(env.msg[id].comments.length>0){
		//env.msg[id].text="Ce Message a été supprimer";
		//$("#message"+id).html(env.msg[id].getHtml());
		//env.msg.splice(id);
		//$("#message"+id).remove();
	//}else{
		env.msg.splice(id);
		$("#message"+id).remove();
		
	//}
}


function logOut() {
	alert("logOut");
	if (env.key != undefined) {
		if (!env.noConnection)  {
			$.ajax ({
				type:"GET",
				url:"http://localhost:8080/Twitto//LogOut",
				datatype:"json",
				data:"key="+env.key,
				datatype : "json",
				error : function(jqXHR, textStatus, errorThrown) {
					alert(textStatus);
				},
				success : function (rep) {
					//alert("refreshMessagesresponse()");
					alert("succes logout");
					respLogOut(rep);
				}
			});
		}
	}
}
function respLogOut(){
	
	env.key=null;
	env.id=null;
	env.login=null;
	makeConnexion();
}


