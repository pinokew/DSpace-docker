package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.RelationshipType.Tilted;

@StaticMetamodel(RelationshipType.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RelationshipType_ {

	
	/**
	 * @see org.dspace.content.RelationshipType#rightMaxCardinality
	 **/
	public static volatile SingularAttribute<RelationshipType, Integer> rightMaxCardinality;
	
	/**
	 * @see org.dspace.content.RelationshipType#copyToLeft
	 **/
	public static volatile SingularAttribute<RelationshipType, Boolean> copyToLeft;
	
	/**
	 * @see org.dspace.content.RelationshipType#leftType
	 **/
	public static volatile SingularAttribute<RelationshipType, EntityType> leftType;
	
	/**
	 * @see org.dspace.content.RelationshipType#leftMinCardinality
	 **/
	public static volatile SingularAttribute<RelationshipType, Integer> leftMinCardinality;
	
	/**
	 * @see org.dspace.content.RelationshipType#rightType
	 **/
	public static volatile SingularAttribute<RelationshipType, EntityType> rightType;
	
	/**
	 * @see org.dspace.content.RelationshipType#tilted
	 **/
	public static volatile SingularAttribute<RelationshipType, Tilted> tilted;
	
	/**
	 * @see org.dspace.content.RelationshipType#leftMaxCardinality
	 **/
	public static volatile SingularAttribute<RelationshipType, Integer> leftMaxCardinality;
	
	/**
	 * @see org.dspace.content.RelationshipType#leftwardType
	 **/
	public static volatile SingularAttribute<RelationshipType, String> leftwardType;
	
	/**
	 * @see org.dspace.content.RelationshipType#rightMinCardinality
	 **/
	public static volatile SingularAttribute<RelationshipType, Integer> rightMinCardinality;
	
	/**
	 * @see org.dspace.content.RelationshipType#rightwardType
	 **/
	public static volatile SingularAttribute<RelationshipType, String> rightwardType;
	
	/**
	 * @see org.dspace.content.RelationshipType#id
	 **/
	public static volatile SingularAttribute<RelationshipType, Integer> id;
	
	/**
	 * @see org.dspace.content.RelationshipType
	 **/
	public static volatile jakarta.persistence.metamodel.EntityType<RelationshipType> class_;
	
	/**
	 * @see org.dspace.content.RelationshipType#copyToRight
	 **/
	public static volatile SingularAttribute<RelationshipType, Boolean> copyToRight;

	public static final String RIGHT_MAX_CARDINALITY = "rightMaxCardinality";
	public static final String COPY_TO_LEFT = "copyToLeft";
	public static final String LEFT_TYPE = "leftType";
	public static final String LEFT_MIN_CARDINALITY = "leftMinCardinality";
	public static final String RIGHT_TYPE = "rightType";
	public static final String TILTED = "tilted";
	public static final String LEFT_MAX_CARDINALITY = "leftMaxCardinality";
	public static final String LEFTWARD_TYPE = "leftwardType";
	public static final String RIGHT_MIN_CARDINALITY = "rightMinCardinality";
	public static final String RIGHTWARD_TYPE = "rightwardType";
	public static final String ID = "id";
	public static final String COPY_TO_RIGHT = "copyToRight";

}

