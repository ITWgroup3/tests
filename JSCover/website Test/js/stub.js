var linker = {
	launchApp: function(appPath) {
		return 0;
	},

	initiateDownload: function(url, app_name, cat, desc) {
		var storage = sessionStorage;
		// build cathtml.html
		var storeCatData = storage.getItem( "cathtml.html" );
		if ( storeCatData != null )
		{
			var reg = new RegExp(cat, "i");
			if ( storeCatData.match(reg) == null )
			{
				storeCatData += "<li id=\""+cat+"\"><a onfocus=\"blur();\">"+cat+"</a></li>";
			}
		}
		else
		{
			storeCatData = "<li id=\""+cat+"\"><a onfocus=\"blur();\">"+cat+"</a></li>";
		}
		storage.setItem( "cathtml.html", storeCatData );
		// build appList.html
		var storeAppListData = storage.getItem( "appList.html" );
		var urlParts = url.split('/');
		var appFileName = urlParts[urlParts.length - 1];
		var PathToApp = "/Applications/AppFolder/" + cat + "/" + app_name + "App/" + appFileName;
		if ( storeAppListData != null )
		{
			//var PathToApp = "aaa";
			storeAppListData += "<div class=\"installed-app\" category=\""+cat+"\" description=\""+desc+"\">";
            storeAppListData += "<a href=\""+PathToApp+"\"><img src=\"img/application_icon.png\"/>"+app_name+"</a>";
            storeAppListData += "</div>";
		}
		else
		{
			storeAppListData = "<div class=\"installed-app\" category=\""+cat+"\" description=\""+desc+"\">";
            storeAppListData += "<a href=\""+PathToApp+"\"><img src=\"img/application_icon.png\"/>"+app_name+"</a>";
            storeAppListData += "</div>";
		}
		storage.setItem( "appList.html", storeAppListData );
		// build JSON
		var storeJSONData = storage.getItem( "update.json" );
		if ( storeJSONData != null )
		{
			var len = storeJSONData.length;
			storeJSONData = storeJSONData.substring(0, len - 2);
			storeJSONData += ",\n{\"Appname\" : \""+appFileName+"\",\"Category\":\""+cat+"\"}\n]";
		}
		else
		{
			storeJSONData = "[\n{\"Appname\" : \""+appFileName+"\",\"Category\":\""+cat+"\"}\n]";
		}
		storage.setItem( "update.json", storeJSONData );
		// call a JS callback
		finishedDownload();
		return;
	},

	setFrame: function() {

	},

	JSCallback: function() {

	},

	fetchFile: function(url) {
		var storage = sessionStorage;
		if ( typeof sessionStorage == undefined )
		{
			if ( url === "appList.html" ) {
				return "<div class=\"installed-app\" category=\"Mathematics\" description=\"asdassdad\"><a href=\"/Applications/AppFolder/Mathematics/AppTestApp/Coureswork.jar\"><img src=\"img/application_icon.png\"/>AppTest</a></div>";
			} else if ( url === "cathtml.html" ) {
				return "<li id=\"Mathematics\"><a onfocus=\"blur();\">Mathematics</a></li>";
			} else if ( url === "update.json" ) {
				return "[ {\"Appname\" : \"AppTest\",\"Category\":\"Mathematics\"} ]";
			} else {
				return " ";
			}
		}
		else
		{
			var storeData = storage.getItem( url );
			if ( storeData == null )
			{
				return " ";
			}
			else
			{
				return storeData;
			}
		}
	},

	json: function() {

	},

	uninstallApp: function(appname, category) {
		var storage = sessionStorage;
		var storedJSONData = storage.getItem( "update.json" );
		var blockJSON = storedJSONData.split("\n");
		var storeData = "";
		var count = 0;
		var len = blockJSON.length;
		for ( var i = 0 ; i < len ; i++ )
		{
			var regAppName = new RegExp(appname, "i");
			var regCategory= new RegExp(category, "i");
			if ( (blockJSON[i].match(regAppName) == null) && (blockJSON[i].match(regCategory) == null) )
			{
				storeData += blockJSON[i] + "\n";
				count++;
			}
		}
		if ( count >= 3 )
		{
			storage.setItem( "update.json", storeData );
		}
		else
		{
			storage.removeItem( "update.json" );
		}
		return true;
	},

	updateFile: function(filename, updateData) {
		var storage = sessionStorage;
		if ( typeof sessionStorage == undefined )
		{
			return;
		}
		else
		{
			storage.setItem( filename, updateData );
		}
	},

	bootChecks: function() {
		return true;
	}
};