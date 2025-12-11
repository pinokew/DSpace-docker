package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.Group;

@StaticMetamodel(Community.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Community_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.Community#subCommunities
	 **/
	public static volatile SetAttribute<Community, Community> subCommunities;
	
	/**
	 * @see org.dspace.content.Community#collections
	 **/
	public static volatile SetAttribute<Community, Collection> collections;
	
	/**
	 * @see org.dspace.content.Community#parentCommunities
	 **/
	public static volatile SetAttribute<Community, Community> parentCommunities;
	
	/**
	 * @see org.dspace.content.Community#legacyId
	 **/
	public static volatile SingularAttribute<Community, Integer> legacyId;
	
	/**
	 * @see org.dspace.content.Community#logo
	 **/
	public static volatile SingularAttribute<Community, Bitstream> logo;
	
	/**
	 * @see org.dspace.content.Community
	 **/
	public static volatile EntityType<Community> class_;
	
	/**
	 * @see org.dspace.content.Community#admins
	 **/
	public static volatile SingularAttribute<Community, Group> admins;

	public static final String SUB_COMMUNITIES = "subCommunities";
	public static final String COLLECTIONS = "collections";
	public static final String PARENT_COMMUNITIES = "parentCommunities";
	public static final String LEGACY_ID = "legacyId";
	public static final String LOGO = "logo";
	public static final String ADMINS = "admins";

}

