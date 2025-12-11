package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Group2GroupCache.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Group2GroupCache_ {

	
	/**
	 * @see org.dspace.eperson.Group2GroupCache#parent
	 **/
	public static volatile SingularAttribute<Group2GroupCache, Group> parent;
	
	/**
	 * @see org.dspace.eperson.Group2GroupCache
	 **/
	public static volatile EntityType<Group2GroupCache> class_;
	
	/**
	 * @see org.dspace.eperson.Group2GroupCache#child
	 **/
	public static volatile SingularAttribute<Group2GroupCache, Group> child;

	public static final String PARENT = "parent";
	public static final String CHILD = "child";

}

