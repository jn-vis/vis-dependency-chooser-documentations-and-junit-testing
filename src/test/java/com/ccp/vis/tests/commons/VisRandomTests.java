package com.ccp.vis.tests.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.ccp.decorators.CcpFileDecorator;
import com.ccp.decorators.CcpFolderDecorator;
import com.ccp.decorators.CcpJsonRepresentation;
import com.ccp.decorators.CcpStringDecorator;
import com.ccp.dependency.injection.CcpDependencyInjection;
import com.ccp.implementations.db.bulk.elasticsearch.CcpElasticSerchDbBulk;
import com.ccp.implementations.db.crud.elasticsearch.CcpElasticSearchCrud;
import com.ccp.implementations.db.query.elasticsearch.CcpElasticSearchQueryExecutor;
import com.ccp.implementations.db.utils.elasticsearch.CcpElasticSearchDbRequest;
import com.ccp.implementations.http.apache.mime.CcpApacheMimeHttp;
import com.ccp.implementations.json.gson.CcpGsonJsonHandler;
import com.ccp.implementations.password.mindrot.CcpMindrotPasswordHandler;
import com.ccp.jn.async.business.factory.CcpJnAsyncBusinessFactory;
import com.ccp.local.testings.implementations.CcpLocalInstances;
import com.ccp.local.testings.implementations.cache.CcpLocalCacheInstances;

public class VisRandomTests {
	public static void main(String[] args) throws Exception {
		CcpDependencyInjection.loadAllDependencies(
				CcpLocalInstances.mensageriaSender.getLocalImplementation(new CcpJnAsyncBusinessFactory()),
				new CcpElasticSearchQueryExecutor(),
				new CcpElasticSearchDbRequest(), 
				new CcpMindrotPasswordHandler(),
				new CcpElasticSerchDbBulk(),
				CcpLocalCacheInstances.map,
				new CcpElasticSearchCrud(),
				new CcpGsonJsonHandler(), 
				new CcpApacheMimeHttp()
				);
		CcpFolderDecorator folder = new CcpStringDecorator("documentation/database/elasticsearch/scripts/entities/create").folder();
		Map<String, List<String>> map = new TreeMap<>();
		folder.readFiles(file -> {
			String fileName = file.getName();
			String javaClassName = "VisEntity" + new CcpStringDecorator(fileName).text().toCamelCase().toString();
			CcpJsonRepresentation json = file.asSingleJson().getInnerJsonFromPath("mappings", "properties");
			Set<String> fields = json.fieldSet();
			for (String field : fields) {
				List<String> javaClassesName = map.getOrDefault(field, new ArrayList<>());
				javaClassesName.add(javaClassName);
				map.put(field, javaClassesName);
			}
		});
		CcpFileDecorator reset = new CcpStringDecorator("c:\\logs\\fields.txt").file().reset();
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			List<String> list = map.get(string);
			reset.append(string + " = " + list);
		}
	}

}
