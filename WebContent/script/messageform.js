function newMessage(f){
	if(f.textmsg.value.length>0){
		
		//alert(f.textmsg.value);
		var tmp=f.textmsg.value;
		f.textmsg.value="";

		ajoutMessage(tmp);
	
		return false;
	}
	
}

function newCom(f,idmsg){
	
	if(f.ameliorer.value.length>0 && idmsg!=undefined){
		
		//alert(f.textmsg.value);
		//lert(idmsg);
		var tmp=f.ameliorer.value;
		f.ameliorer.value="";
		ajoutCom(tmp,idmsg);
		
		return false;
	}
	
}

function getCount(log){
	var t=0;
	for(var i=0; i<env.msg.length;i++){
		if(env.msg[i].login==log){
			t+=1;
		}
	}
	
	return t;
	
}