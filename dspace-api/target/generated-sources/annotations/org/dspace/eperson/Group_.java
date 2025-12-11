package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Group.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Group_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.eperson.Group#epeople
	 **/
	public static volatile ListAttribute<Group, EPerson> epeople;
	
	/**
	 * @see org.dspace.eperson.Group#parentGroups
	 **/
	public static volatile ListAttribute<Group, Group> parentGroups;
	
	/**
	 * @see org.dspace.eperson.Group#permanent
	 **/
	public static volatile SingularAttribute<Group, Boolean> permanent;
	
	/**
	 * @see org.dspace.eperson.Group#name
	 **/
	public static volatile SingularAttribute<Group, String> name;
	
	/**
	 * @see org.dspace.eperson.Group#legacyId
	 **/
	public static volatile SingularAttribute<Group, Integer> legacyId;
	
	/**
	 * @see org.dspace.eperson.Group#groups
	 **/
	public static volatile ListAttribute<Group, Group> groups;
	
	/**
	 * @see org.dspace.eperson.Group
	 **/
	public static volatile EntityType<Group> class_;

	public static final String EPEOPLE = "epeople";
	public static final String PARENT_GROUPS = "parentGroups";
	public static final String PERMANENT = "permanent";
	public static final String NAME = "name";
	public static final String LEGACY_ID = "legacyId";
	public static final String GROUPS = "groups";

}

