package com.ccp.jn.vis.test.resume;

import static org.junit.Assert.assertTrue;

import com.ccp.constantes.CcpConstants;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.db.dao.elasticsearch.CcpElasticSearchDao;
import com.ccp.implementations.db.setup.elasticsearch.CcpElasticSearchDbSetup;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.jn.vis.test.asserting.TemplateDeTestes;

public class ResumeStep1DadosDaCarreiraTest {		// extends TemplateDeTestes{
	
	/*
	 * 0-text-cargoMaisRecente
	 * 1-text-cargoDesejado
	 * 2-integer-experiencia
	 * 3-select-tipoVaga
	 */
	
	// resolvendo o extends TemplateDeTestes
	{
		CcpDependencyInjection.loadAllDependencies(new CcpGsonJsonHandler(), new CcpElasticSearchDao(),
				new CcpElasticSearchDbRequest(), new CcpApacheMimeHttp(), new CcpElasticSearchDbSetup());		
	}
	
	public void testandoAPresencaDeTodosOsCampos () {
		CcpJsonRepresentation json = CcpConstants.EMPTY_JSON
				.put("cargoMaisRecente", "")
				.put("cargoDesejado", "")
				.put("experiencia", "")
				.put("tipoVaga", "");
		assertTrue(json.containsKey("tipoVaga") == false);
		
	}
		
	
	protected String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

}
