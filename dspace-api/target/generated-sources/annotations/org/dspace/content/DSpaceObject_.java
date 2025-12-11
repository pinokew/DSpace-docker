package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.util.UUID;
import org.dspace.authorize.ResourcePolicy;
import org.dspace.handle.Handle;

@StaticMetamodel(DSpaceObject.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.DSpaceObject#handles
	 **/
	public static volatile ListAttribute<DSpaceObject, Handle> handles;
	
	/**
	 * @see org.dspace.content.DSpaceObject#metadata
	 **/
	public static volatile ListAttribute<DSpaceObject, MetadataValue> metadata;
	
	/**
	 * @see org.dspace.content.DSpaceObject#resourcePolicies
	 **/
	public static volatile ListAttribute<DSpaceObject, ResourcePolicy> resourcePolicies;
	
	/**
	 * @see org.dspace.content.DSpaceObject#id
	 **/
	public static volatile SingularAttribute<DSpaceObject, UUID> id;
	
	/**
	 * @see org.dspace.content.DSpaceObject
	 **/
	public static volatile EntityType<DSpaceObject> class_;

	public static final String HANDLES = "handles";
	public static final String METADATA = "metadata";
	public static final String RESOURCE_POLICIES = "resourcePolicies";
	public static final String ID = "id";

}

