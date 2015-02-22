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
package org.sonatype.nexus.email;

import org.sonatype.nexus.common.text.Strings2;

/**
 * SMTP configuration.
 *
 * @since 3.0
 */
public class SmtpConfiguration
{
  private String hostname;

  private int port;

  private String username;

  private String password;

  private String systemEmailAddress;

  private boolean debugMode;

  private boolean sslEnabled;

  private boolean tlsEnabled;

  public String getHostname() {
    return hostname;
  }

  public void setHostname(final String hostname) {
    this.hostname = hostname;
  }

  public int getPort() {
    return port;
  }

  public void setPort(final int port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getSystemEmailAddress() {
    return systemEmailAddress;
  }

  public void setSystemEmailAddress(final String systemEmailAddress) {
    this.systemEmailAddress = systemEmailAddress;
  }

  public boolean isDebugMode() {
    return debugMode;
  }

  public void setDebugMode(final boolean debugMode) {
    this.debugMode = debugMode;
  }

  public boolean isSslEnabled() {
    return sslEnabled;
  }

  public void setSslEnabled(final boolean sslEnabled) {
    this.sslEnabled = sslEnabled;
  }

  public boolean isTlsEnabled() {
    return tlsEnabled;
  }

  public void setTlsEnabled(final boolean tlsEnabled) {
    this.tlsEnabled = tlsEnabled;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "hostname='" + hostname + '\'' +
        ", port=" + port +
        ", username='" + username + '\'' +
        ", password='" + Strings2.mask(password) + '\'' +
        ", systemEmailAddress='" + systemEmailAddress + '\'' +
        ", debugMode=" + debugMode +
        ", sslEnabled=" + sslEnabled +
        ", tlsEnabled=" + tlsEnabled +
        '}';
  }
}
