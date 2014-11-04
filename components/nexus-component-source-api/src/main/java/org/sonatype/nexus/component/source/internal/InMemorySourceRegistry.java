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
package org.sonatype.nexus.component.source.internal;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.component.source.api.ComponentSource;
import org.sonatype.nexus.component.source.api.ComponentSourceId;
import org.sonatype.nexus.component.source.api.ComponentSourceRegistry;
import org.sonatype.sisu.goodies.common.ComponentSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * A registry of the currently available component sources.
 *
 * @since 3.0
 */
@Named
@Singleton
public class InMemorySourceRegistry
    extends ComponentSupport
    implements ComponentSourceRegistry
{
  private final ConcurrentHashMap<ComponentSourceId, ComponentSource> sources = new ConcurrentHashMap<>();

  public void register(final ComponentSource source) {
    checkNotNull(source);

    final ComponentSource alreadyBound = sources.putIfAbsent(source.getId(), source);

    checkState(alreadyBound == null, "A source is already bound to name %s", source.getId());

    log.info("Registering component source {}", source);
  }

  public boolean unregister(ComponentSource source) {
    checkNotNull(source);

    final ComponentSource removed = sources.remove(source.getId());

    if (removed != null) {
      log.info("Unregistering source {}", source);
    }

    return removed != null;
  }

  public void update(ComponentSource source) {
    unregister(source);
    register(source);
  }

  @Override
  public <T extends ComponentSource> T getSource(String name) {
    return getSource(getIdByName(name));
  }

  @Nullable
  @Override
  public <T extends ComponentSource> T getSource(final ComponentSourceId sourceId) {
    final ComponentSource source = this.sources.get(sourceId);
    checkArgument(source != null, "No source found for source ID %s", sourceId);
    return (T) source;
  }

  private ComponentSourceId getIdByName(String sourceName) {
    for (ComponentSourceId id : sources.keySet()) {
      if (id.getName().equals(sourceName)) {
        return id;
      }
    }
    throw new IllegalArgumentException(String.format("No source found for name %s", sourceName));
  }

}
