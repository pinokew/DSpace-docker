package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MetadataField.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class MetadataField_ {

	
	/**
	 * @see org.dspace.content.MetadataField#metadataSchema
	 **/
	public static volatile SingularAttribute<MetadataField, MetadataSchema> metadataSchema;
	
	/**
	 * @see org.dspace.content.MetadataField#qualifier
	 **/
	public static volatile SingularAttribute<MetadataField, String> qualifier;
	
	/**
	 * @see org.dspace.content.MetadataField#id
	 **/
	public static volatile SingularAttribute<MetadataField, Integer> id;
	
	/**
	 * @see org.dspace.content.MetadataField
	 **/
	public static volatile EntityType<MetadataField> class_;
	
	/**
	 * @see org.dspace.content.MetadataField#element
	 **/
	public static volatile SingularAttribute<MetadataField, String> element;
	
	/**
	 * @see org.dspace.content.MetadataField#scopeNote
	 **/
	public static volatile SingularAttribute<MetadataField, String> scopeNote;

	public static final String METADATA_SCHEMA = "metadataSchema";
	public static final String QUALIFIER = "qualifier";
	public static final String ID = "id";
	public static final String ELEMENT = "element";
	public static final String SCOPE_NOTE = "scopeNote";

}

