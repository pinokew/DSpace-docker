package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.eperson.EPerson;

@StaticMetamodel(QAEventProcessed.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class QAEventProcessed_ {

	
	/**
	 * @see org.dspace.content.QAEventProcessed#eventId
	 **/
	public static volatile SingularAttribute<QAEventProcessed, String> eventId;
	
	/**
	 * @see org.dspace.content.QAEventProcessed#eperson
	 **/
	public static volatile SingularAttribute<QAEventProcessed, EPerson> eperson;
	
	/**
	 * @see org.dspace.content.QAEventProcessed#item
	 **/
	public static volatile SingularAttribute<QAEventProcessed, Item> item;
	
	/**
	 * @see org.dspace.content.QAEventProcessed
	 **/
	public static volatile EntityType<QAEventProcessed> class_;
	
	/**
	 * @see org.dspace.content.QAEventProcessed#eventTimestamp
	 **/
	public static volatile SingularAttribute<QAEventProcessed, Instant> eventTimestamp;

	public static final String EVENT_ID = "eventId";
	public static final String EPERSON = "eperson";
	public static final String ITEM = "item";
	public static final String EVENT_TIMESTAMP = "eventTimestamp";

}

