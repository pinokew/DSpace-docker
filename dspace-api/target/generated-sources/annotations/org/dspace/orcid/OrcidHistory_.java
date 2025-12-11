package org.dspace.orcid;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Item;

@StaticMetamodel(OrcidHistory.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrcidHistory_ {

	
	/**
	 * @see org.dspace.orcid.OrcidHistory#metadata
	 **/
	public static volatile SingularAttribute<OrcidHistory, String> metadata;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#recordType
	 **/
	public static volatile SingularAttribute<OrcidHistory, String> recordType;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#description
	 **/
	public static volatile SingularAttribute<OrcidHistory, String> description;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#id
	 **/
	public static volatile SingularAttribute<OrcidHistory, Integer> id;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#responseMessage
	 **/
	public static volatile SingularAttribute<OrcidHistory, String> responseMessage;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory
	 **/
	public static volatile EntityType<OrcidHistory> class_;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#operation
	 **/
	public static volatile SingularAttribute<OrcidHistory, OrcidOperation> operation;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#profileItem
	 **/
	public static volatile SingularAttribute<OrcidHistory, Item> profileItem;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#entity
	 **/
	public static volatile SingularAttribute<OrcidHistory, Item> entity;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#putCode
	 **/
	public static volatile SingularAttribute<OrcidHistory, String> putCode;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#timestamp
	 **/
	public static volatile SingularAttribute<OrcidHistory, Instant> timestamp;
	
	/**
	 * @see org.dspace.orcid.OrcidHistory#status
	 **/
	public static volatile SingularAttribute<OrcidHistory, Integer> status;

	public static final String METADATA = "metadata";
	public static final String RECORD_TYPE = "recordType";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String RESPONSE_MESSAGE = "responseMessage";
	public static final String OPERATION = "operation";
	public static final String PROFILE_ITEM = "profileItem";
	public static final String ENTITY = "entity";
	public static final String PUT_CODE = "putCode";
	public static final String TIMESTAMP = "timestamp";
	public static final String STATUS = "status";

}

