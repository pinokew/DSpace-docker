package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Bitstream.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Bitstream_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.content.Bitstream#storeNumber
	 **/
	public static volatile SingularAttribute<Bitstream, Integer> storeNumber;
	
	/**
	 * @see org.dspace.content.Bitstream#checksumAlgorithm
	 **/
	public static volatile SingularAttribute<Bitstream, String> checksumAlgorithm;
	
	/**
	 * @see org.dspace.content.Bitstream#collection
	 **/
	public static volatile SingularAttribute<Bitstream, Collection> collection;
	
	/**
	 * @see org.dspace.content.Bitstream#community
	 **/
	public static volatile SingularAttribute<Bitstream, Community> community;
	
	/**
	 * @see org.dspace.content.Bitstream#sequenceId
	 **/
	public static volatile SingularAttribute<Bitstream, Integer> sequenceId;
	
	/**
	 * @see org.dspace.content.Bitstream#sizeBytes
	 **/
	public static volatile SingularAttribute<Bitstream, Long> sizeBytes;
	
	/**
	 * @see org.dspace.content.Bitstream#internalId
	 **/
	public static volatile SingularAttribute<Bitstream, String> internalId;
	
	/**
	 * @see org.dspace.content.Bitstream#bitstreamFormat
	 **/
	public static volatile SingularAttribute<Bitstream, BitstreamFormat> bitstreamFormat;
	
	/**
	 * @see org.dspace.content.Bitstream#deleted
	 **/
	public static volatile SingularAttribute<Bitstream, Boolean> deleted;
	
	/**
	 * @see org.dspace.content.Bitstream#bundles
	 **/
	public static volatile ListAttribute<Bitstream, Bundle> bundles;
	
	/**
	 * @see org.dspace.content.Bitstream#checksum
	 **/
	public static volatile SingularAttribute<Bitstream, String> checksum;
	
	/**
	 * @see org.dspace.content.Bitstream#legacyId
	 **/
	public static volatile SingularAttribute<Bitstream, Integer> legacyId;
	
	/**
	 * @see org.dspace.content.Bitstream
	 **/
	public static volatile EntityType<Bitstream> class_;

	public static final String STORE_NUMBER = "storeNumber";
	public static final String CHECKSUM_ALGORITHM = "checksumAlgorithm";
	public static final String COLLECTION = "collection";
	public static final String COMMUNITY = "community";
	public static final String SEQUENCE_ID = "sequenceId";
	public static final String SIZE_BYTES = "sizeBytes";
	public static final String INTERNAL_ID = "internalId";
	public static final String BITSTREAM_FORMAT = "bitstreamFormat";
	public static final String DELETED = "deleted";
	public static final String BUNDLES = "bundles";
	public static final String CHECKSUM = "checksum";
	public static final String LEGACY_ID = "legacyId";

}

