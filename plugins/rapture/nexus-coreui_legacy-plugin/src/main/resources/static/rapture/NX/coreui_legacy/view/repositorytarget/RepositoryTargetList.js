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
 * Repository target grid.
 *
 * @since 3.0
 */
Ext.define('NX.coreui_legacy.view.repositorytarget.RepositoryTargetList', {
  extend: 'NX.view.drilldown.Master',
  alias: 'widget.nx-coreui_legacy-repositorytarget-list',
  requires: [
    'NX.I18n'
  ],

  store: 'RepositoryTarget',

  columns: [
    {
      xtype: 'nx-iconcolumn',
      width: 36,
      iconVariant: 'x16',
      iconName: function () {
        return 'target-default';
      }
    },
    { header: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_NAME_COLUMN'), dataIndex: 'name', flex: 1 },
    { header: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_REPOSITORY_COLUMN'), dataIndex: 'format' },
    { header: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_PATTERNS_COLUMN'), dataIndex: 'patterns', flex: 1 }
  ],

  viewConfig: {
    emptyText: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_EMPTY_STATE'),
    deferEmptyText: false
  },

  tbar: [
    { xtype: 'button', text: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_NEW_BUTTON'), glyph: 'xf055@FontAwesome' /* fa-plus-circle */, action: 'new', disabled: true }
  ],

  plugins: [
    { ptype: 'gridfilterbox', emptyText: NX.I18n.get('LEGACY_ADMIN_TARGETS_LIST_FILTER_ERROR') }
  ]

});
