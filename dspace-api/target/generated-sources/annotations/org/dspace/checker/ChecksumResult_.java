package org.dspace.checker;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ChecksumResult.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ChecksumResult_ {

	
	/**
	 * @see org.dspace.checker.ChecksumResult#resultCode
	 **/
	public static volatile SingularAttribute<ChecksumResult, ChecksumResultCode> resultCode;
	
	/**
	 * @see org.dspace.checker.ChecksumResult#resultDescription
	 **/
	public static volatile SingularAttribute<ChecksumResult, String> resultDescription;
	
	/**
	 * @see org.dspace.checker.ChecksumResult
	 **/
	public static volatile EntityType<ChecksumResult> class_;

	public static final String RESULT_CODE = "resultCode";
	public static final String RESULT_DESCRIPTION = "resultDescription";

}

