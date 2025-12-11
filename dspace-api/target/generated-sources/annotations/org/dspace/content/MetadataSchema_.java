package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MetadataSchema.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class MetadataSchema_ {

	
	/**
	 * @see org.dspace.content.MetadataSchema#namespace
	 **/
	public static volatile SingularAttribute<MetadataSchema, String> namespace;
	
	/**
	 * @see org.dspace.content.MetadataSchema#name
	 **/
	public static volatile SingularAttribute<MetadataSchema, String> name;
	
	/**
	 * @see org.dspace.content.MetadataSchema#id
	 **/
	public static volatile SingularAttribute<MetadataSchema, Integer> id;
	
	/**
	 * @see org.dspace.content.MetadataSchema
	 **/
	public static volatile EntityType<MetadataSchema> class_;

	public static final String NAMESPACE = "namespace";
	public static final String NAME = "name";
	public static final String ID = "id";

}

