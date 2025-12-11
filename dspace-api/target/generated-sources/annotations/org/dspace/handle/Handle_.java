package org.dspace.handle;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.DSpaceObject;

@StaticMetamodel(Handle.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Handle_ {

	
	/**
	 * @see org.dspace.handle.Handle#dso
	 **/
	public static volatile SingularAttribute<Handle, DSpaceObject> dso;
	
	/**
	 * @see org.dspace.handle.Handle#resourceTypeId
	 **/
	public static volatile SingularAttribute<Handle, Integer> resourceTypeId;
	
	/**
	 * @see org.dspace.handle.Handle#handle
	 **/
	public static volatile SingularAttribute<Handle, String> handle;
	
	/**
	 * @see org.dspace.handle.Handle#id
	 **/
	public static volatile SingularAttribute<Handle, Integer> id;
	
	/**
	 * @see org.dspace.handle.Handle
	 **/
	public static volatile EntityType<Handle> class_;

	public static final String DSO = "dso";
	public static final String RESOURCE_TYPE_ID = "resourceTypeId";
	public static final String HANDLE = "handle";
	public static final String ID = "id";

}

