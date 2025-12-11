package org.dspace.versioning;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VersionHistory.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class VersionHistory_ {

	
	/**
	 * @see org.dspace.versioning.VersionHistory#versions
	 **/
	public static volatile ListAttribute<VersionHistory, Version> versions;
	
	/**
	 * @see org.dspace.versioning.VersionHistory#id
	 **/
	public static volatile SingularAttribute<VersionHistory, Integer> id;
	
	/**
	 * @see org.dspace.versioning.VersionHistory
	 **/
	public static volatile EntityType<VersionHistory> class_;

	public static final String VERSIONS = "versions";
	public static final String ID = "id";

}

