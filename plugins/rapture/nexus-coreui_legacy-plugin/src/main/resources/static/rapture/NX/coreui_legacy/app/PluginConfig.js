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
/*global Ext, NX*/

/**
 * CoreUi plugin configuration.
 *
 * @since 3.0
 */
Ext.define('NX.coreui_legacy.app.PluginConfig', {
  '@aggregate_priority': 100,

  namespaces: [
    'NX.coreui_legacy'
  ],

  requires: [
    'NX.coreui_legacy.app.PluginStrings'
  ],

  controllers: [
    {
      id: 'NX.coreui_legacy.controller.HealthCheckRepositorySettings',
      active: function () {
        return NX.app.Application.pluginActive('com.sonatype.nexus.plugins:nexus-healthcheck-oss-plugin')
          || NX.app.Application.pluginActive('com.sonatype.nexus.plugins:nexus-clm-plugin');
      }
    },
    {
      id: 'NX.coreui_legacy.controller.HealthCheckRepositoryColumn',
      active: function () {
        return NX.app.Application.pluginActive('com.sonatype.nexus.plugins:nexus-healthcheck-oss-plugin')
            || NX.app.Application.pluginActive('com.sonatype.nexus.plugins:nexus-clm-plugin');
      }
    },
    {
      id: 'NX.coreui_legacy.controller.LegacyRepositories',
      active: function () {
        return NX.app.Application.pluginActive('org.sonatype.nexus.plugins:nexus-coreui_legacy-plugin');
      }
    },
    {
      id: 'NX.coreui_legacy.controller.RepositoryTargets',
      active: function () {
        return NX.app.Application.pluginActive('org.sonatype.nexus.plugins:nexus-coreui_legacy-plugin');
      }
    },
    {
      id: 'NX.coreui_legacy.controller.RepositoryRoutes',
      active: function () {
        return NX.app.Application.pluginActive('org.sonatype.nexus.plugins:nexus-coreui_legacy-plugin');
      }
    },
    {
      id: 'NX.coreui_legacy.controller.RoutingRepositorySettings',
      active: function () {
        return NX.app.Application.pluginActive('org.sonatype.nexus.plugins:nexus-coreui_legacy-plugin');
      }
    }
  ]
});
