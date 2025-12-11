package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Relationship.LatestVersionStatus;

@StaticMetamodel(Relationship.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Relationship_ {

	
	/**
	 * @see org.dspace.content.Relationship#leftItem
	 **/
	public static volatile SingularAttribute<Relationship, Item> leftItem;
	
	/**
	 * @see org.dspace.content.Relationship#latestVersionStatus
	 **/
	public static volatile SingularAttribute<Relationship, LatestVersionStatus> latestVersionStatus;
	
	/**
	 * @see org.dspace.content.Relationship#relationshipType
	 **/
	public static volatile SingularAttribute<Relationship, RelationshipType> relationshipType;
	
	/**
	 * @see org.dspace.content.Relationship#leftwardValue
	 **/
	public static volatile SingularAttribute<Relationship, String> leftwardValue;
	
	/**
	 * @see org.dspace.content.Relationship#rightPlace
	 **/
	public static volatile SingularAttribute<Relationship, Integer> rightPlace;
	
	/**
	 * @see org.dspace.content.Relationship#rightwardValue
	 **/
	public static volatile SingularAttribute<Relationship, String> rightwardValue;
	
	/**
	 * @see org.dspace.content.Relationship#id
	 **/
	public static volatile SingularAttribute<Relationship, Integer> id;
	
	/**
	 * @see org.dspace.content.Relationship#rightItem
	 **/
	public static volatile SingularAttribute<Relationship, Item> rightItem;
	
	/**
	 * @see org.dspace.content.Relationship
	 **/
	public static volatile EntityType<Relationship> class_;
	
	/**
	 * @see org.dspace.content.Relationship#leftPlace
	 **/
	public static volatile SingularAttribute<Relationship, Integer> leftPlace;

	public static final String LEFT_ITEM = "leftItem";
	public static final String LATEST_VERSION_STATUS = "latestVersionStatus";
	public static final String RELATIONSHIP_TYPE = "relationshipType";
	public static final String LEFTWARD_VALUE = "leftwardValue";
	public static final String RIGHT_PLACE = "rightPlace";
	public static final String RIGHTWARD_VALUE = "rightwardValue";
	public static final String ID = "id";
	public static final String RIGHT_ITEM = "rightItem";
	public static final String LEFT_PLACE = "leftPlace";

}

