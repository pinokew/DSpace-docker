package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Bundle.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Bundle_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.Bundle#primaryBitstream
	 **/
	public static volatile SingularAttribute<Bundle, Bitstream> primaryBitstream;
	
	/**
	 * @see org.dspace.content.Bundle#legacyId
	 **/
	public static volatile SingularAttribute<Bundle, Integer> legacyId;
	
	/**
	 * @see org.dspace.content.Bundle
	 **/
	public static volatile EntityType<Bundle> class_;
	
	/**
	 * @see org.dspace.content.Bundle#bitstreams
	 **/
	public static volatile ListAttribute<Bundle, Bitstream> bitstreams;
	
	/**
	 * @see org.dspace.content.Bundle#items
	 **/
	public static volatile ListAttribute<Bundle, Item> items;

	public static final String PRIMARY_BITSTREAM = "primaryBitstream";
	public static final String LEGACY_ID = "legacyId";
	public static final String BITSTREAMS = "bitstreams";
	public static final String ITEMS = "items";

}

