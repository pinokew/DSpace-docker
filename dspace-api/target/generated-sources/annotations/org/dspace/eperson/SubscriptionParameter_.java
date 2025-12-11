package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SubscriptionParameter.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SubscriptionParameter_ {

	
	/**
	 * @see org.dspace.eperson.SubscriptionParameter#name
	 **/
	public static volatile SingularAttribute<SubscriptionParameter, String> name;
	
	/**
	 * @see org.dspace.eperson.SubscriptionParameter#id
	 **/
	public static volatile SingularAttribute<SubscriptionParameter, Integer> id;
	
	/**
	 * @see org.dspace.eperson.SubscriptionParameter#subscription
	 **/
	public static volatile SingularAttribute<SubscriptionParameter, Subscription> subscription;
	
	/**
	 * @see org.dspace.eperson.SubscriptionParameter
	 **/
	public static volatile EntityType<SubscriptionParameter> class_;
	
	/**
	 * @see org.dspace.eperson.SubscriptionParameter#value
	 **/
	public static volatile SingularAttribute<SubscriptionParameter, String> value;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String SUBSCRIPTION = "subscription";
	public static final String VALUE = "value";

}

