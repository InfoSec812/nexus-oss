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
package org.sonatype.nexus.component.services.adapter;

import javax.inject.Named;

import org.eclipse.sisu.BeanEntry;
import org.eclipse.sisu.Mediator;

/**
 * Manages {@link ComponentEntityAdapter} registrations via Sisu component mediation.
 *
 * @since 3.0
 */
@Named
public class ComponentEntityAdapterMediator
    implements Mediator<Named, ComponentEntityAdapter, ComponentEntityAdapterRegistry>
{
  @Override
  public void add(final BeanEntry<Named, ComponentEntityAdapter> entry, final ComponentEntityAdapterRegistry registry) {
    registry.registerAdapter(entry.getValue());
  }

  @Override
  public void remove(final BeanEntry<Named, ComponentEntityAdapter> entry, final ComponentEntityAdapterRegistry registry) {
    registry.unregisterAdapter(entry.getValue().getComponentClass());
  }
}
