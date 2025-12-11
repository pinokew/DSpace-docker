package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.Group;

@StaticMetamodel(Collection.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Collection_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.Collection#template
	 **/
	public static volatile SingularAttribute<Collection, Item> template;
	
	/**
	 * @see org.dspace.content.Collection#legacyId
	 **/
	public static volatile SingularAttribute<Collection, Integer> legacyId;
	
	/**
	 * @see org.dspace.content.Collection#logo
	 **/
	public static volatile SingularAttribute<Collection, Bitstream> logo;
	
	/**
	 * @see org.dspace.content.Collection
	 **/
	public static volatile EntityType<Collection> class_;
	
	/**
	 * @see org.dspace.content.Collection#submitters
	 **/
	public static volatile SingularAttribute<Collection, Group> submitters;
	
	/**
	 * @see org.dspace.content.Collection#admins
	 **/
	public static volatile SingularAttribute<Collection, Group> admins;
	
	/**
	 * @see org.dspace.content.Collection#communities
	 **/
	public static volatile SetAttribute<Collection, Community> communities;

	public static final String TEMPLATE = "template";
	public static final String LEGACY_ID = "legacyId";
	public static final String LOGO = "logo";
	public static final String SUBMITTERS = "submitters";
	public static final String ADMINS = "admins";
	public static final String COMMUNITIES = "communities";

}

