package org.dspace.checker;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Bitstream;

@StaticMetamodel(ChecksumHistory.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ChecksumHistory_ {

	
	/**
	 * @see org.dspace.checker.ChecksumHistory#processEndDate
	 **/
	public static volatile SingularAttribute<ChecksumHistory, Instant> processEndDate;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#checksumExpected
	 **/
	public static volatile SingularAttribute<ChecksumHistory, String> checksumExpected;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#checksumCalculated
	 **/
	public static volatile SingularAttribute<ChecksumHistory, String> checksumCalculated;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#checksumResult
	 **/
	public static volatile SingularAttribute<ChecksumHistory, ChecksumResult> checksumResult;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#id
	 **/
	public static volatile SingularAttribute<ChecksumHistory, Long> id;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#bitstream
	 **/
	public static volatile SingularAttribute<ChecksumHistory, Bitstream> bitstream;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory
	 **/
	public static volatile EntityType<ChecksumHistory> class_;
	
	/**
	 * @see org.dspace.checker.ChecksumHistory#processStartDate
	 **/
	public static volatile SingularAttribute<ChecksumHistory, Instant> processStartDate;

	public static final String PROCESS_END_DATE = "processEndDate";
	public static final String CHECKSUM_EXPECTED = "checksumExpected";
	public static final String CHECKSUM_CALCULATED = "checksumCalculated";
	public static final String CHECKSUM_RESULT = "checksumResult";
	public static final String ID = "id";
	public static final String BITSTREAM = "bitstream";
	public static final String PROCESS_START_DATE = "processStartDate";

}

