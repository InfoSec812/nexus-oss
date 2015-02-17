/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-2015 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.configuration.application;

import java.io.IOException;

import org.sonatype.configuration.ConfigurationException;
import org.sonatype.nexus.configuration.source.ApplicationConfigurationSource;

/**
 * A component responsible for configuration management.
 *
 * @author cstamas
 */
public interface NexusConfiguration
    extends ApplicationConfiguration, MutableConfiguration
{
  /**
   * Explicit loading of configuration. Does not force reload.
   */
  void loadConfiguration() throws ConfigurationException, IOException;

  /**
   * Explicit loading of configuration. Enables to force reloading of config.
   */
  void loadConfiguration(boolean forceReload) throws ConfigurationException, IOException;

  ApplicationConfigurationSource getConfigurationSource();

  boolean isInstanceUpgraded();

  boolean isConfigurationUpgraded();

  boolean isConfigurationDefaulted();

  // ------------------------------------------------------------------
  // Booting

  /**
   * Creates internals like reposes configured in nexus.xml. Called on startup.
   */
  void createInternals() throws ConfigurationException;

  /**
   * Cleanups the internals, like on shutdown.
   */
  void dropInternals();
}
