package org.dspace.identifier;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.DSpaceObject;

@StaticMetamodel(DOI.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class DOI_ {

	
	/**
	 * @see org.dspace.identifier.DOI#resourceTypeId
	 **/
	public static volatile SingularAttribute<DOI, Integer> resourceTypeId;
	
	/**
	 * @see org.dspace.identifier.DOI#dSpaceObject
	 **/
	public static volatile SingularAttribute<DOI, DSpaceObject> dSpaceObject;
	
	/**
	 * @see org.dspace.identifier.DOI#id
	 **/
	public static volatile SingularAttribute<DOI, Integer> id;
	
	/**
	 * @see org.dspace.identifier.DOI
	 **/
	public static volatile EntityType<DOI> class_;
	
	/**
	 * @see org.dspace.identifier.DOI#doi
	 **/
	public static volatile SingularAttribute<DOI, String> doi;
	
	/**
	 * @see org.dspace.identifier.DOI#status
	 **/
	public static volatile SingularAttribute<DOI, Integer> status;

	public static final String RESOURCE_TYPE_ID = "resourceTypeId";
	public static final String D_SPACE_OBJECT = "dSpaceObject";
	public static final String ID = "id";
	public static final String DOI = "doi";
	public static final String STATUS = "status";

}

