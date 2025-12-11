package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.DSpaceObject;

@StaticMetamodel(Subscription.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Subscription_ {

	
	/**
	 * @see org.dspace.eperson.Subscription#subscriptionType
	 **/
	public static volatile SingularAttribute<Subscription, String> subscriptionType;
	
	/**
	 * @see org.dspace.eperson.Subscription#ePerson
	 **/
	public static volatile SingularAttribute<Subscription, EPerson> ePerson;
	
	/**
	 * @see org.dspace.eperson.Subscription#dSpaceObject
	 **/
	public static volatile SingularAttribute<Subscription, DSpaceObject> dSpaceObject;
	
	/**
	 * @see org.dspace.eperson.Subscription#subscriptionParameterList
	 **/
	public static volatile ListAttribute<Subscription, SubscriptionParameter> subscriptionParameterList;
	
	/**
	 * @see org.dspace.eperson.Subscription#id
	 **/
	public static volatile SingularAttribute<Subscription, Integer> id;
	
	/**
	 * @see org.dspace.eperson.Subscription
	 **/
	public static volatile EntityType<Subscription> class_;

	public static final String SUBSCRIPTION_TYPE = "subscriptionType";
	public static final String E_PERSON = "ePerson";
	public static final String D_SPACE_OBJECT = "dSpaceObject";
	public static final String SUBSCRIPTION_PARAMETER_LIST = "subscriptionParameterList";
	public static final String ID = "id";

}

