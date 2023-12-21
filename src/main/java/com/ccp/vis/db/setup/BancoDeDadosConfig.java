package com.ccp.vis.db.setup;

import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.especifications.db.setup.CcpDbSetupCreator;
import com.ccp.implementations.db.setup.elasticsearch.CcpElasticSearchDbSetup;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;

public class BancoDeDadosConfig {
	
	public static void main(String[] args) {
		CcpDependencyInjection.loadAllDependencies
		(
			new CcpGsonJsonHandler(),
			new CcpApacheMimeHttp(),
			new CcpElasticSearchDbRequest(),
			new CcpElasticSearchDbSetup()
		);
		
		CcpDbSetupCreator dependency = CcpDependencyInjection.getDependency(CcpDbSetupCreator.class);	
		
		String mainPath = "documentation\\database\\elasticsearch\\scripts";
		String createFolder = mainPath + "create_table";
		String insertFolder = mainPath + "insert";
		dependency.setup("vis", createFolder, insertFolder);
	}

}
