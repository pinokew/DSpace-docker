package org.dspace.checker;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Bitstream;

@StaticMetamodel(MostRecentChecksum.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class MostRecentChecksum_ {

	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#currentChecksum
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, String> currentChecksum;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#matchedPrevChecksum
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, Boolean> matchedPrevChecksum;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#expectedChecksum
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, String> expectedChecksum;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#toBeProcessed
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, Boolean> toBeProcessed;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#processEndDate
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, Instant> processEndDate;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#checksumResult
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, ChecksumResult> checksumResult;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#bitstream
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, Bitstream> bitstream;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#checksumAlgorithm
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, String> checksumAlgorithm;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum
	 **/
	public static volatile EntityType<MostRecentChecksum> class_;
	
	/**
	 * @see org.dspace.checker.MostRecentChecksum#processStartDate
	 **/
	public static volatile SingularAttribute<MostRecentChecksum, Instant> processStartDate;

	public static final String CURRENT_CHECKSUM = "currentChecksum";
	public static final String MATCHED_PREV_CHECKSUM = "matchedPrevChecksum";
	public static final String EXPECTED_CHECKSUM = "expectedChecksum";
	public static final String TO_BE_PROCESSED = "toBeProcessed";
	public static final String PROCESS_END_DATE = "processEndDate";
	public static final String CHECKSUM_RESULT = "checksumResult";
	public static final String BITSTREAM = "bitstream";
	public static final String CHECKSUM_ALGORITHM = "checksumAlgorithm";
	public static final String PROCESS_START_DATE = "processStartDate";

}

