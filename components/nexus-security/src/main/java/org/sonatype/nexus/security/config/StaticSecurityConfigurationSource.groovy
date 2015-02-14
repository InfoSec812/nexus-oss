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

package org.sonatype.nexus.security.config

import org.sonatype.nexus.security.authc.AuthenticatingRealmImpl
import org.sonatype.nexus.security.authz.AuthorizingRealmImpl

import javax.inject.Named
import javax.inject.Singleton

/**
 * Security configuration defaults.
 *
 * @since 3.0
 */
@Named('static')
@Singleton
class StaticSecurityConfigurationSource
    extends PreconfiguredSecurityConfigurationSource
    implements SecurityConfigurationSource
{
  StaticSecurityConfigurationSource() {
    super(new SecurityConfiguration(
        anonymousAccessEnabled: true,
        anonymousUsername: 'anonymous',
        anonymousPassword: 'anonymous',
        realms: [
            AuthenticatingRealmImpl.ROLE,
            AuthorizingRealmImpl.ROLE
        ]
    ))
  }

  /**
   * The 'static' source is immutable.
   */
  @Override
  void storeConfiguration() {
    throw new UnsupportedOperationException()
  }
}

