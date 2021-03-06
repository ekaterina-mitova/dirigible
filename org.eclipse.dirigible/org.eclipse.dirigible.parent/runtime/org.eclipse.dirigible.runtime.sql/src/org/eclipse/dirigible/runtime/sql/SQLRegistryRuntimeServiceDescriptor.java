package org.eclipse.dirigible.runtime.sql;

import org.eclipse.dirigible.runtime.registry.IRuntimeServiceDescriptor;

/**
 * Descriptor for the SQL Registry Service
 */
public class SQLRegistryRuntimeServiceDescriptor implements IRuntimeServiceDescriptor {

	private final String name = "SQL Registry";
	private final String description = "SQL Registry Service lists all the services written in SQL.";
	private final String endpoint = "/registry-sql";
	private final String documentation = "http://www.dirigible.io/help/service_registry_sql.html";

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getEndpoint() {
		return endpoint;
	}

	@Override
	public String getDocumentation() {
		return documentation;
	}

}
