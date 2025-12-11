package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Collection;
import org.dspace.eperson.Group;

@StaticMetamodel(CollectionRole.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class CollectionRole_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.CollectionRole#roleId
	 **/
	public static volatile SingularAttribute<CollectionRole, String> roleId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.CollectionRole#id
	 **/
	public static volatile SingularAttribute<CollectionRole, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.CollectionRole#collection
	 **/
	public static volatile SingularAttribute<CollectionRole, Collection> collection;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.CollectionRole
	 **/
	public static volatile EntityType<CollectionRole> class_;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.CollectionRole#group
	 **/
	public static volatile SingularAttribute<CollectionRole, Group> group;

	public static final String ROLE_ID = "roleId";
	public static final String ID = "id";
	public static final String COLLECTION = "collection";
	public static final String GROUP = "group";

}

