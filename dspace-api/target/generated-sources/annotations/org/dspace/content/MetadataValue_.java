package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MetadataValue.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class MetadataValue_ {

	
	/**
	 * @see org.dspace.content.MetadataValue#authority
	 **/
	public static volatile SingularAttribute<MetadataValue, String> authority;
	
	/**
	 * @see org.dspace.content.MetadataValue#confidence
	 **/
	public static volatile SingularAttribute<MetadataValue, Integer> confidence;
	
	/**
	 * @see org.dspace.content.MetadataValue#dSpaceObject
	 **/
	public static volatile SingularAttribute<MetadataValue, DSpaceObject> dSpaceObject;
	
	/**
	 * @see org.dspace.content.MetadataValue#language
	 **/
	public static volatile SingularAttribute<MetadataValue, String> language;
	
	/**
	 * @see org.dspace.content.MetadataValue#id
	 **/
	public static volatile SingularAttribute<MetadataValue, Integer> id;
	
	/**
	 * @see org.dspace.content.MetadataValue#place
	 **/
	public static volatile SingularAttribute<MetadataValue, Integer> place;
	
	/**
	 * @see org.dspace.content.MetadataValue
	 **/
	public static volatile EntityType<MetadataValue> class_;
	
	/**
	 * @see org.dspace.content.MetadataValue#metadataField
	 **/
	public static volatile SingularAttribute<MetadataValue, MetadataField> metadataField;
	
	/**
	 * @see org.dspace.content.MetadataValue#value
	 **/
	public static volatile SingularAttribute<MetadataValue, String> value;

	public static final String AUTHORITY = "authority";
	public static final String CONFIDENCE = "confidence";
	public static final String D_SPACE_OBJECT = "dSpaceObject";
	public static final String LANGUAGE = "language";
	public static final String ID = "id";
	public static final String PLACE = "place";
	public static final String METADATA_FIELD = "metadataField";
	public static final String VALUE = "value";

}

