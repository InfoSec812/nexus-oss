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
package org.sonatype.nexus.web;

import javax.servlet.ServletException;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Exception to be thrown by Servlets already prepared their error information.
 *
 * @since 2.8
 */
public class ErrorStatusException
    extends ServletException
{
  private final int responseCode;

  private final String reasonPhrase;

  public ErrorStatusException(final int responseCode,
                              final String reasonPhrase,
                              final String errorMessage,
                              final Exception cause)
  {
    super(errorMessage, cause);
    checkArgument(responseCode >= 400, "Not an error-status code: %s", responseCode);
    this.responseCode = responseCode;
    this.reasonPhrase = reasonPhrase;
  }

  public ErrorStatusException(final int responseCode, final String reasonPhrase, final String errorMessage) {
    this(responseCode, reasonPhrase, errorMessage, null);
  }

  public int getResponseCode() {
    return responseCode;
  }

  public String getReasonPhrase() {
    return reasonPhrase;
  }
}
