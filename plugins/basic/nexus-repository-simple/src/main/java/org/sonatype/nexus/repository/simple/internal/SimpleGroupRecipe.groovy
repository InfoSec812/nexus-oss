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
package org.sonatype.nexus.repository.simple.internal

import org.sonatype.nexus.repository.Format
import org.sonatype.nexus.repository.RecipeSupport
import org.sonatype.nexus.repository.Repository
import org.sonatype.nexus.repository.Type
import org.sonatype.nexus.repository.group.GroupHandler
import org.sonatype.nexus.repository.http.HttpHandlers
import org.sonatype.nexus.repository.security.SecurityHandler
import org.sonatype.nexus.repository.types.GroupType
import org.sonatype.nexus.repository.view.ConfigurableViewFacet
import org.sonatype.nexus.repository.view.Route
import org.sonatype.nexus.repository.view.Router
import org.sonatype.nexus.repository.view.ViewFacet
import org.sonatype.nexus.repository.view.handlers.TimingHandler
import org.sonatype.nexus.repository.view.matchers.LiteralMatcher
import org.sonatype.nexus.repository.view.matchers.token.TokenMatcher

import javax.annotation.Nonnull
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

import static org.sonatype.nexus.repository.view.matchers.logic.LogicMatchers.or

/**
 * Simple group repository recipe.
 *
 * @since 3.0
 */
@Named(SimpleGroupRecipe.NAME)
@Singleton
class SimpleGroupRecipe
    extends RecipeSupport
{
  static final String NAME = 'simple-group'

  @Inject
  Provider<SimpleSecurityFacet> securityFacet

  @Inject
  Provider<ConfigurableViewFacet> viewFacet

  @Inject
  Provider<SimpleIndexHtmlFacet> indexHtmlFacet

  @Inject
  Provider<SimpleGroupFacet> groupFacet

  @Inject
  TimingHandler timingHandler

  @Inject
  SecurityHandler securityHandler

  @Inject
  SimpleIndexHtmlHandler indexHtmlHandler

  @Inject
  GroupHandler groupHandler

  @Inject
  SimpleGroupRecipe(@Named(GroupType.NAME) Type type,
                    @Named(SimpleFormat.NAME) Format format)
  {
    super(type, format)
  }

  @Override
  void apply(final @Nonnull Repository repository) {
    repository.attach(securityFacet.get())
    repository.attach(configure(viewFacet.get()))
    repository.attach(indexHtmlFacet.get())
    repository.attach(groupFacet.get())
  }

  /**
   * Configure {@link ViewFacet}.
   */
  private ViewFacet configure(final ConfigurableViewFacet facet) {
    Router.Builder builder = new Router.Builder()

    builder.route(new Route.Builder()
        .matcher(or(
        new LiteralMatcher('/'),
        new LiteralMatcher('/index.html')))
        .handler(timingHandler)
        .handler(securityHandler)
        .handler(indexHtmlHandler)
        .create())

    builder.route(new Route.Builder()
        .matcher(new TokenMatcher('/{name:.+}'))
        .handler(timingHandler)
        .handler(securityHandler)
        .handler(groupHandler)
        .create())

    builder.defaultHandlers(
        HttpHandlers.badRequest()
    )

    facet.configure(builder.create())

    return facet
  }
}
