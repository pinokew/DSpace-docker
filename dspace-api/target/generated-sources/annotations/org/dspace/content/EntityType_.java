package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EntityType.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class EntityType_ {

	
	/**
	 * @see org.dspace.content.EntityType#id
	 **/
	public static volatile SingularAttribute<EntityType, Integer> id;
	
	/**
	 * @see org.dspace.content.EntityType#label
	 **/
	public static volatile SingularAttribute<EntityType, String> label;
	
	/**
	 * @see org.dspace.content.EntityType
	 **/
	public static volatile jakarta.persistence.metamodel.EntityType<EntityType> class_;

	public static final String ID = "id";
	public static final String LABEL = "label";

}

