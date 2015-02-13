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
package org.sonatype.nexus.security.user;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.configuration.validation.InvalidConfigurationException;
import org.sonatype.nexus.security.SecuritySystem;
import org.sonatype.security.authorization.AuthorizationException;
import org.sonatype.sisu.goodies.common.ComponentSupport;

/**
 * Provides functionality to read and update basic user data.
 *
 * @since 2.1
 */
@Named
@Singleton
public class UserAccountManagerImpl
    extends ComponentSupport
    implements UserAccountManager
{
  private final SecuritySystem securitySystem;

  @Inject
  public UserAccountManagerImpl(final SecuritySystem securitySystem) {
    this.securitySystem = securitySystem;
  }

  public User readAccount(String userId) throws UserNotFoundException, AuthorizationException {
    checkPermission(userId);

    return securitySystem.getUser(userId);
  }

  public User updateAccount(User user)
      throws InvalidConfigurationException, UserNotFoundException, NoSuchUserManagerException, AuthorizationException
  {
    checkPermission(user.getUserId());

    return securitySystem.updateUser(user);
  }

  private void checkPermission(String userId) throws AuthorizationException {
    if (securitySystem.getSubject().getPrincipal().equals(userId)) {
      return;
    }

    securitySystem.checkPermission(securitySystem.getSubject().getPrincipals(), "security:users");
  }
}
