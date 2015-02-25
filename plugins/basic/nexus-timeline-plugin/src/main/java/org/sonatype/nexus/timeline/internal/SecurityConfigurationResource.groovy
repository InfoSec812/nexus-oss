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
package org.sonatype.nexus.timeline.internal

import org.sonatype.nexus.security.config.CPrivilege
import org.sonatype.nexus.security.config.CRole
import org.sonatype.nexus.security.config.MemorySecurityConfiguration
import org.sonatype.nexus.security.config.StaticSecurityConfigurationResource

import javax.inject.Named
import javax.inject.Singleton

/**
 * Timeline plugin static security resource.
 *
 * @since 3.0
 */
@Named
@Singleton
class SecurityConfigurationResource
implements StaticSecurityConfigurationResource
{
  @Override
  MemorySecurityConfiguration getConfiguration() {
    return new MemorySecurityConfiguration(
        privileges: [
            new CPrivilege(
                id: 'feeds-read', // 44
                type: 'method',
                name: 'Feeds - (read)',
                description: 'Give permission to view the different feeds. The extents of this privilege are related to the allowed targets.',
                properties: [
                    'method': 'read',
                    'permission': 'nexus:feeds'
                ]
            )
        ],
        roles: [
            new CRole(
                id: 'ui-system-feeds',
                name: 'UI: System Feeds',
                description: 'Gives access to the System Feeds screen in Nexus UI',
                privileges: ['feeds-read']
            )
        ]
    )
  }
}

