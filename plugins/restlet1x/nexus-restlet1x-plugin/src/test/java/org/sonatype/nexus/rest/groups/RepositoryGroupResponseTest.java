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
package org.sonatype.nexus.rest.groups;

import org.sonatype.nexus.rest.AbstractRestTestCase;
import org.sonatype.nexus.rest.model.RepositoryGroupListResourceResponse;
import org.sonatype.plexus.rest.representation.XStreamRepresentation;

import org.junit.Test;
import org.restlet.data.MediaType;

public class RepositoryGroupResponseTest
    extends AbstractRestTestCase
{

  @Test
  public void testRepoGroup()
      throws Exception
  {
    String jsonString = "{\"data\":[{\"id\":\"public-releases\",\"name\":\"public-releases11\"}]}";

    XStreamRepresentation representation =
        new XStreamRepresentation(xstream, jsonString, MediaType.APPLICATION_JSON);

    RepositoryGroupListResourceResponse response =
        (RepositoryGroupListResourceResponse) representation.getPayload(new RepositoryGroupListResourceResponse());
  }

}