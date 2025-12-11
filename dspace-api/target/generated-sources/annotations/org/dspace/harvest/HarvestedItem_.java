package org.dspace.harvest;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Item;

@StaticMetamodel(HarvestedItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class HarvestedItem_ {

	
	/**
	 * @see org.dspace.harvest.HarvestedItem#lastHarvested
	 **/
	public static volatile SingularAttribute<HarvestedItem, Instant> lastHarvested;
	
	/**
	 * @see org.dspace.harvest.HarvestedItem#item
	 **/
	public static volatile SingularAttribute<HarvestedItem, Item> item;
	
	/**
	 * @see org.dspace.harvest.HarvestedItem#id
	 **/
	public static volatile SingularAttribute<HarvestedItem, Integer> id;
	
	/**
	 * @see org.dspace.harvest.HarvestedItem#oaiId
	 **/
	public static volatile SingularAttribute<HarvestedItem, String> oaiId;
	
	/**
	 * @see org.dspace.harvest.HarvestedItem
	 **/
	public static volatile EntityType<HarvestedItem> class_;

	public static final String LAST_HARVESTED = "lastHarvested";
	public static final String ITEM = "item";
	public static final String ID = "id";
	public static final String OAI_ID = "oaiId";

}

