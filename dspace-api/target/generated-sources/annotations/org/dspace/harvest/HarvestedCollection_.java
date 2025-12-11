package org.dspace.harvest;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Collection;

@StaticMetamodel(HarvestedCollection.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class HarvestedCollection_ {

	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#lastHarvested
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Instant> lastHarvested;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#oaiSource
	 **/
	public static volatile SingularAttribute<HarvestedCollection, String> oaiSource;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#harvestStatus
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Integer> harvestStatus;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#metadataConfigId
	 **/
	public static volatile SingularAttribute<HarvestedCollection, String> metadataConfigId;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#harvestMessage
	 **/
	public static volatile SingularAttribute<HarvestedCollection, String> harvestMessage;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#harvestStartTime
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Instant> harvestStartTime;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#id
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Integer> id;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#collection
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Collection> collection;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#oaiSetId
	 **/
	public static volatile SingularAttribute<HarvestedCollection, String> oaiSetId;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection
	 **/
	public static volatile EntityType<HarvestedCollection> class_;
	
	/**
	 * @see org.dspace.harvest.HarvestedCollection#harvestType
	 **/
	public static volatile SingularAttribute<HarvestedCollection, Integer> harvestType;

	public static final String LAST_HARVESTED = "lastHarvested";
	public static final String OAI_SOURCE = "oaiSource";
	public static final String HARVEST_STATUS = "harvestStatus";
	public static final String METADATA_CONFIG_ID = "metadataConfigId";
	public static final String HARVEST_MESSAGE = "harvestMessage";
	public static final String HARVEST_START_TIME = "harvestStartTime";
	public static final String ID = "id";
	public static final String COLLECTION = "collection";
	public static final String OAI_SET_ID = "oaiSetId";
	public static final String HARVEST_TYPE = "harvestType";

}

