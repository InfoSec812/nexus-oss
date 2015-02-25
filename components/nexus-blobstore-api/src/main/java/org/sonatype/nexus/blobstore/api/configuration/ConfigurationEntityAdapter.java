package org.sonatype.nexus.blobstore.api.configuration;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.orient.OClassNameBuilder;
import org.sonatype.nexus.orient.entity.EntityAdapter;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.metadata.schema.OClass.INDEX_TYPE;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.record.impl.ODocument;

@Named
@Singleton
public class ConfigurationEntityAdapter
    extends EntityAdapter<BlobStoreConfiguration>
{

  private static final String DB_CLASS = new OClassNameBuilder()
      .prefix("blobstore")
      .type(BlobStoreConfiguration.class)
      .build();

  private static final String P_BLOBSTORE_NAME = "blobstore_name";

  private static final String P_BLOBSTORE_PATH = "blobstore_path";

  private static final String I_BLOBSTORE_NAME = "blobstore_name_idx";

  private static final String I_BLOBSTORE_PATH = "blobstore_path_idx";

  public ConfigurationEntityAdapter() {
    super(DB_CLASS);
  }

  @Override
  protected void defineType(final OClass type) {
    type.createProperty(P_BLOBSTORE_NAME, OType.STRING).setMandatory(true).setNotNull(true);
    type.createProperty(P_BLOBSTORE_PATH, OType.STRING).setMandatory(true).setNotNull(true);
    type.createIndex(I_BLOBSTORE_NAME, INDEX_TYPE.UNIQUE, P_BLOBSTORE_NAME);
    type.createIndex(I_BLOBSTORE_PATH, INDEX_TYPE.UNIQUE, P_BLOBSTORE_PATH);
  }

  @Override
  protected BlobStoreConfiguration newEntity() {
    return new BlobStoreConfiguration();
  }

  @Override
  protected void readFields(final ODocument document, final BlobStoreConfiguration entity) {
    String blobStoreName = document.field(P_BLOBSTORE_NAME, OType.STRING);
    String path = document.field(P_BLOBSTORE_PATH, OType.STRING);
    entity.setName(blobStoreName);
    entity.setPath(path);
  }

  @Override
  protected void writeFields(final ODocument document, final BlobStoreConfiguration entity) {
    document.field(P_BLOBSTORE_NAME, entity.getName());
    document.field(P_BLOBSTORE_PATH, entity.getPath());
  }
}
