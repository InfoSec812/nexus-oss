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
 * Add maven proxy repository window.
 *
 * @since 3.0
 */
Ext.define('NX.coreui.view.repository.RepositoryAddProxyMaven', {
  extend: 'NX.coreui.view.repository.RepositoryAdd',
  alias: ['widget.nx-repository-add-proxy-maven1', 'widget.nx-repository-add-proxy-maven2'],
  requires: [
    'NX.I18n'
  ],

  initComponent: function() {
    var me = this;

    me.items = {
      xtype: 'nx-repository-settings-proxy-maven2-form',
      template: me.template,
      api: {
        submit: 'NX.direct.coreui_Repository.createProxyMaven'
      },
      settingsFormSuccessMessage: function(data) {
        return NX.I18n.get('ADMIN_REPOSITORIES_CREATE_MAVEN_PROXY_SUCCESS') + data['id'];
      }
    };

    me.callParent(arguments);

    me.down('#repositoryPolicy').hide();
  }

});
