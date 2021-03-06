package org.eclipse.dirigible.runtime.registry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.dirigible.repository.api.IRepository;
import org.eclipse.dirigible.repository.api.IRepositoryPaths;
import org.eclipse.dirigible.repository.api.IResource;
import org.eclipse.dirigible.repository.api.RepositoryPath;
import org.eclipse.dirigible.repository.ext.utils.RequestUtils;
import org.eclipse.dirigible.repository.logging.Logger;

public class WorkspacePublishServlet extends RepositoryServlet {

	private static final Logger logger = Logger.getLogger(WorkspacePublishServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestPath = PathUtils.extractPath(request);
		RepositoryPath workspacePath = new RepositoryPath(requestPath);

		RepositoryPath registryPath = new RepositoryPath("");
		registryPath = registryPath.append(IRepositoryPaths.DB_DIRIGIBLE_REGISTRY_PUBLIC);
		String[] segments = workspacePath.getSegments();
		for (int i = 1; i < segments.length; i++) {
			registryPath = registryPath.append(segments[i]);
		}
		String originalPath = getRepositoryPathPrefix(request) + workspacePath;
		IRepository repository = getRepository(request);
		IResource resource = repository.getResource(originalPath);
		if (resource.exists()) {
			repository.createResource(registryPath.toString(), resource.getContent());
		} else {
			logger.error(String.format("Error copying resource from %s to %s", originalPath, registryPath.toString()));
		}
		// postImport actions
	}

	@Override
	protected String getRepositoryPathPrefix(HttpServletRequest req) {
		return IRepositoryPaths.DB_DIRIGIBLE_USERS + RequestUtils.getUser(req) + IRepository.SEPARATOR + IRepositoryPaths.WORKSPACE_FOLDER_NAME;
	}

}
