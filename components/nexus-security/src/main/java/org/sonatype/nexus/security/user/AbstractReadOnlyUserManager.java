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

import org.sonatype.nexus.common.throwables.ConfigurationException;

/**
 * An abstract UserManager, that just throws exceptions for all the write methods.
 *
 * Any call to theses methods should be checked by the <code>supportsWrite()</code> method,
 * so this should never be called.
 */
public abstract class AbstractReadOnlyUserManager
    extends AbstractUserManager
{
  @Override
  public boolean supportsWrite() {
    return false;
  }

  @Override
  public User addUser(User user, String password) throws ConfigurationException {
    throwException();
    return null;
  }

  @Override
  public void changePassword(String userId, String newPassword) throws UserNotFoundException {
    throwException();
  }

  @Override
  public void deleteUser(String userId) throws UserNotFoundException {
    throwException();
  }

  @Override
  public User updateUser(User user) throws UserNotFoundException, ConfigurationException {
    throwException();
    return null;
  }

  private void throwException() {
    throw new IllegalStateException("UserManager: '" + getSource() + "' does not support write operations.");
  }
}
