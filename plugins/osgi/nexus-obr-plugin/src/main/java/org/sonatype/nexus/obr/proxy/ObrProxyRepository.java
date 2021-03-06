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
package org.sonatype.nexus.obr.proxy;

import org.sonatype.nexus.proxy.repository.ProxyRepository;

public interface ObrProxyRepository
    extends ProxyRepository
{
  /**
   * Returns the path <b>within OBR repository</b> for OBR metadata. Concatenating remoteUrl with this gives you full
   * URL of the OBR metadata.
   */
  String getObrPath();

  /**
   * Sets the OBR metadata path.
   */
  void setObrPath(String obrPath);

  /**
   * Returns the max age of the OBR metadata. Default is 1440 (1 day).
   */
  int getMetadataMaxAge();

  /**
   * Sets the max age of the OBR metadata in minutes. Default is 1440 (1 day).
   */
  void setMetadataMaxAge(int metadataMaxAge);
}
