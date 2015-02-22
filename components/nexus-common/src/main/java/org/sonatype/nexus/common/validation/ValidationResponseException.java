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
package org.sonatype.nexus.common.validation;

import java.util.List;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

// TODO: Consider extending javax.validation.ValidationException?

/**
 * Validation response exception.
 *
 * @since 3.0
 */
public class ValidationResponseException
    extends RuntimeException
{
  private ValidationResponse response;

  public ValidationResponseException(final String message, final ValidationResponse response) {
    super(message);
    this.response = checkNotNull(response);
  }

  public ValidationResponseException(final ValidationResponse response) {
    this(null, response);
  }

  @Nonnull
  public ValidationResponse getResponse() {
    return response;
  }

  @Nonnull
  public List<ValidationMessage> getErrors() {
    return response.getErrors();
  }

  @Nonnull
  public List<ValidationMessage> getWarnings() {
    return response.getWarnings();
  }

  @Override
  public String getMessage() {
    StringBuilder buff = new StringBuilder();
    if (super.getMessage() != null) {
      buff.append(super.getMessage());
    }
    buff.append(response);
    return buff.toString();
  }
}