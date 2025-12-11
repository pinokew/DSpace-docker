package org.dspace.orcid;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Item;

@StaticMetamodel(OrcidQueue.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrcidQueue_ {

	
	/**
	 * @see org.dspace.orcid.OrcidQueue#metadata
	 **/
	public static volatile SingularAttribute<OrcidQueue, String> metadata;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#recordType
	 **/
	public static volatile SingularAttribute<OrcidQueue, String> recordType;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#description
	 **/
	public static volatile SingularAttribute<OrcidQueue, String> description;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#id
	 **/
	public static volatile SingularAttribute<OrcidQueue, Integer> id;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue
	 **/
	public static volatile EntityType<OrcidQueue> class_;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#operation
	 **/
	public static volatile SingularAttribute<OrcidQueue, OrcidOperation> operation;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#profileItem
	 **/
	public static volatile SingularAttribute<OrcidQueue, Item> profileItem;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#entity
	 **/
	public static volatile SingularAttribute<OrcidQueue, Item> entity;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#putCode
	 **/
	public static volatile SingularAttribute<OrcidQueue, String> putCode;
	
	/**
	 * @see org.dspace.orcid.OrcidQueue#attempts
	 **/
	public static volatile SingularAttribute<OrcidQueue, Integer> attempts;

	public static final String METADATA = "metadata";
	public static final String RECORD_TYPE = "recordType";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String OPERATION = "operation";
	public static final String PROFILE_ITEM = "profileItem";
	public static final String ENTITY = "entity";
	public static final String PUT_CODE = "putCode";
	public static final String ATTEMPTS = "attempts";

}

