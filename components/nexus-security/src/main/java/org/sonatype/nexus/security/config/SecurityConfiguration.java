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
package org.sonatype.nexus.security.config;

import com.google.common.collect.Lists;

/**
 * Global security configuration.
 */
public class SecurityConfiguration
    implements org.sonatype.configuration.Configuration, java.io.Serializable
{
  private boolean anonymousAccessEnabled = false;

  private String anonymousUsername;

  private String anonymousPassword;

  private java.util.List<String> realms;

  public void addRealm(String string) {
    getRealms().add(string);
  }

  public String getAnonymousPassword() {
    return this.anonymousPassword;
  }

  public String getAnonymousUsername() {
    return this.anonymousUsername;
  }

  public java.util.List<String> getRealms() {
    if (this.realms == null) {
      this.realms = Lists.newArrayList();
    }

    return this.realms;
  }

  public boolean isAnonymousAccessEnabled() {
    return this.anonymousAccessEnabled;
  }

  public void removeRealm(String string) {
    getRealms().remove(string);
  }

  public void setAnonymousAccessEnabled(boolean anonymousAccessEnabled) {
    this.anonymousAccessEnabled = anonymousAccessEnabled;
  }

  public void setAnonymousPassword(String anonymousPassword) {
    this.anonymousPassword = anonymousPassword;
  }

  public void setAnonymousUsername(String anonymousUsername) {
    this.anonymousUsername = anonymousUsername;
  }

  public void setRealms(java.util.List<String> realms) {
    this.realms = realms;
  }
}
