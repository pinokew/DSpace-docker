package org.dspace.authorize;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import org.dspace.content.DSpaceObject;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;

@StaticMetamodel(ResourcePolicy.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ResourcePolicy_ {

	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#epersonGroup
	 **/
	public static volatile SingularAttribute<ResourcePolicy, Group> epersonGroup;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#rptype
	 **/
	public static volatile SingularAttribute<ResourcePolicy, String> rptype;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#eperson
	 **/
	public static volatile SingularAttribute<ResourcePolicy, EPerson> eperson;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#endDate
	 **/
	public static volatile SingularAttribute<ResourcePolicy, LocalDate> endDate;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#resourceTypeId
	 **/
	public static volatile SingularAttribute<ResourcePolicy, Integer> resourceTypeId;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#dSpaceObject
	 **/
	public static volatile SingularAttribute<ResourcePolicy, DSpaceObject> dSpaceObject;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#rpdescription
	 **/
	public static volatile SingularAttribute<ResourcePolicy, String> rpdescription;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#actionId
	 **/
	public static volatile SingularAttribute<ResourcePolicy, Integer> actionId;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#id
	 **/
	public static volatile SingularAttribute<ResourcePolicy, Integer> id;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy
	 **/
	public static volatile EntityType<ResourcePolicy> class_;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#startDate
	 **/
	public static volatile SingularAttribute<ResourcePolicy, LocalDate> startDate;
	
	/**
	 * @see org.dspace.authorize.ResourcePolicy#rpname
	 **/
	public static volatile SingularAttribute<ResourcePolicy, String> rpname;

	public static final String EPERSON_GROUP = "epersonGroup";
	public static final String RPTYPE = "rptype";
	public static final String EPERSON = "eperson";
	public static final String END_DATE = "endDate";
	public static final String RESOURCE_TYPE_ID = "resourceTypeId";
	public static final String D_SPACE_OBJECT = "dSpaceObject";
	public static final String RPDESCRIPTION = "rpdescription";
	public static final String ACTION_ID = "actionId";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String RPNAME = "rpname";

}

