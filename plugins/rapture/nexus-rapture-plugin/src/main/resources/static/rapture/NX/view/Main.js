/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2014 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
/**
 * Main uber mode panel.
 *
 * @since 3.0
 */
Ext.define('NX.view.Main', {
  extend: 'Ext.panel.Panel',
  alias: 'widget.nx-main',
  requires: [
      'NX.view.header.QuickSearch'
  ],

  layout: 'border',

  items: [
    {
      xtype: 'nx-header-panel',
      region: 'north',
      collapsible: false
    },

    {
      xtype: 'nx-feature-menu',
      region: 'west',
      border: true,
      collapsible: true,
      collapsed: false,
      resizable: true,
      resizeHandles: 'e'
    },

    {
      xtype: 'nx-feature-content',
      region: 'center',
      border: true
    },

    {
      xtype: 'nx-message-panel',
      region: 'east',
      border: true,
      resizable: true,
      resizeHandles: 'w',

      // default to hidden, header button toggles
      hidden: true
    },

    {
      xtype: 'nx-footer',
      region: 'south',
      hidden: false
    },

    {
      xtype: 'nx-dev-panel',
      region: 'south',
      collapsible: true,
      collapsed: true,
      resizable: true,
      resizeHandles: 'n',

      // keep initial constraints to prevent huge panels
      height: 300,

      // default to hidden, only show if debug enabled
      hidden: true
    }
  ],

  initComponent: function () {
    var me = this;

    me.callParent();

    me.down('nx-header-panel>toolbar').add([
      ' ', ' ', // 2x pad
      { xtype: 'nx-header-dashboard-mode' },
      { xtype: 'nx-header-search-mode' },
      { xtype: 'nx-header-browse-mode' },
      { xtype: 'nx-header-admin-mode' },
      ' ',
      { xtype: 'nx-header-quicksearch' },
      '->',
      { xtype: 'nx-header-messages', ui: 'header' },
      { xtype: 'nx-header-refresh', ui: 'header' },
      { xtype: 'nx-header-login', ui: 'header' },
      { xtype: 'nx-header-user-mode', hidden: true },
      { xtype: 'nx-header-logout', ui: 'header' },
      { xtype: 'nx-header-help', ui: 'header' }
    ]);
  }

});