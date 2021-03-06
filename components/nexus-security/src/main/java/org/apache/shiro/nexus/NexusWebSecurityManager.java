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

package org.apache.shiro.nexus;

import javax.inject.Inject;
import javax.inject.Provider;

import org.sonatype.nexus.security.UserIdMdcHelper;
import org.sonatype.nexus.security.authc.AuthenticationEvent;
import org.sonatype.sisu.goodies.common.Time;
import org.sonatype.sisu.goodies.eventbus.EventBus;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Nexus customized {@link WebSecurityManager}.
 *
 * @since 2.7.2
 */
public class NexusWebSecurityManager
    extends DefaultWebSecurityManager
{
  private static final String DEFAULT_REMEMBER_ME_COOKIE_NAME = "NXREMEMBERME";

  private static final Time DEFAULT_REMEMBER_ME_COOKIE_MAX_AGE = Time.days(30);

  private final Provider<EventBus> eventBus;

  @Inject
  public NexusWebSecurityManager(final Provider<EventBus> eventBus) {
    this.eventBus = checkNotNull(eventBus);

    // TODO: Inject singleton?
    // customize remember-me configuration
    CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
    Cookie cookie = rememberMeManager.getCookie();
    // TODO: Expose for configuration, for now just use a more sane defaults
    cookie.setName(DEFAULT_REMEMBER_ME_COOKIE_NAME);
    cookie.setMaxAge(DEFAULT_REMEMBER_ME_COOKIE_MAX_AGE.toSecondsI());
    setRememberMeManager(rememberMeManager);
  }

  /**
   * After login set the userId MDC attribute.
   */
  @Override
  public Subject login(Subject subject, final AuthenticationToken token) throws AuthenticationException {
    try {
      subject = super.login(subject, token);
      UserIdMdcHelper.set(subject);
      eventBus.get().post(new AuthenticationEvent(token.getPrincipal().toString(), true));
      return subject;
    }
    catch (AuthenticationException e) {
      eventBus.get().post(new AuthenticationEvent(token.getPrincipal().toString(), false));
      throw e;
    }
  }

  /**
   * After logout unset the userId MDC attribute.
   */
  @Override
  public void logout(final Subject subject) {
    super.logout(subject);
    UserIdMdcHelper.unset();
  }
}
