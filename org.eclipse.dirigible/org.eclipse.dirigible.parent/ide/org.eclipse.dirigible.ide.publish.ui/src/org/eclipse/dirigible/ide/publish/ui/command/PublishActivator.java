/******************************************************************************* 
 * Copyright (c) 2015 SAP and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SAP - initial API and implementation
 *******************************************************************************/

package org.eclipse.dirigible.ide.publish.ui.command;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.eclipse.dirigible.ide.common.CommonParameters;
import org.eclipse.dirigible.repository.logging.Logger;

public class PublishActivator implements BundleActivator {
	
	private static final Logger logger = Logger.getLogger(PublishActivator.class);

	@Override
	public void start(BundleContext arg0) throws Exception {
		try {
			if (CommonParameters.isRCP()) {
				new AutoActivateAction().init(null);
			}
		} catch (Exception e) {
			logger.error("Auto Activator has not been registered", e);
		}
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		//
	}

}
