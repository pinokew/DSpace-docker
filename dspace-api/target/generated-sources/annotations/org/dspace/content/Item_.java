package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.eperson.EPerson;

@StaticMetamodel(Item.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Item_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.Item#submitter
	 **/
	public static volatile SingularAttribute<Item, EPerson> submitter;
	
	/**
	 * @see org.dspace.content.Item#owningCollection
	 **/
	public static volatile SingularAttribute<Item, Collection> owningCollection;
	
	/**
	 * @see org.dspace.content.Item#collections
	 **/
	public static volatile SetAttribute<Item, Collection> collections;
	
	/**
	 * @see org.dspace.content.Item#discoverable
	 **/
	public static volatile SingularAttribute<Item, Boolean> discoverable;
	
	/**
	 * @see org.dspace.content.Item#bundles
	 **/
	public static volatile ListAttribute<Item, Bundle> bundles;
	
	/**
	 * @see org.dspace.content.Item#withdrawn
	 **/
	public static volatile SingularAttribute<Item, Boolean> withdrawn;
	
	/**
	 * @see org.dspace.content.Item#legacyId
	 **/
	public static volatile SingularAttribute<Item, Integer> legacyId;
	
	/**
	 * @see org.dspace.content.Item#inArchive
	 **/
	public static volatile SingularAttribute<Item, Boolean> inArchive;
	
	/**
	 * @see org.dspace.content.Item#templateItemOf
	 **/
	public static volatile SingularAttribute<Item, Collection> templateItemOf;
	
	/**
	 * @see org.dspace.content.Item#lastModified
	 **/
	public static volatile SingularAttribute<Item, Instant> lastModified;
	
	/**
	 * @see org.dspace.content.Item
	 **/
	public static volatile EntityType<Item> class_;

	public static final String SUBMITTER = "submitter";
	public static final String OWNING_COLLECTION = "owningCollection";
	public static final String COLLECTIONS = "collections";
	public static final String DISCOVERABLE = "discoverable";
	public static final String BUNDLES = "bundles";
	public static final String WITHDRAWN = "withdrawn";
	public static final String LEGACY_ID = "legacyId";
	public static final String IN_ARCHIVE = "inArchive";
	public static final String TEMPLATE_ITEM_OF = "templateItemOf";
	public static final String LAST_MODIFIED = "lastModified";

}

