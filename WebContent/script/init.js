
function init(){
	
	env = new Object();
	env.follows=[];
	env.followers=[];
	env.msg=[];
	env.lastId=-1;
	env.maxId=-1;
	env.minId=-1;
	env.noConnection = false;

	makeInscription();

	//Event to activate when last message appear
	/*$("body").on("appear", function(event, $affected) {
		$.clear_appear();
		completeMessages();
	});*/
	if (env.noConnection) {
	
		env.key="3f5f8491-e210-4b62-8087-df384febfda0";
		env.id=1;
		env.login="sidi1";
		///$((makeMainPanel(-1)));
		makeInscription();

	} else {
		makeConnexion();
	}
		/*var p = getCookie("page");
		var k = getCookie("key");
		if (p == null || k== null) {
		   	  makeConnexion();
		} 
		else{
			 makeConnexionPanel();
				//setCookie("page", 1);	
		}
		
	}*/
	
	
	
}