package org.dspace.versioning;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Item;
import org.dspace.eperson.EPerson;

@StaticMetamodel(Version.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Version_ {

	
	/**
	 * @see org.dspace.versioning.Version#summary
	 **/
	public static volatile SingularAttribute<Version, String> summary;
	
	/**
	 * @see org.dspace.versioning.Version#item
	 **/
	public static volatile SingularAttribute<Version, Item> item;
	
	/**
	 * @see org.dspace.versioning.Version#versionHistory
	 **/
	public static volatile SingularAttribute<Version, VersionHistory> versionHistory;
	
	/**
	 * @see org.dspace.versioning.Version#ePerson
	 **/
	public static volatile SingularAttribute<Version, EPerson> ePerson;
	
	/**
	 * @see org.dspace.versioning.Version#id
	 **/
	public static volatile SingularAttribute<Version, Integer> id;
	
	/**
	 * @see org.dspace.versioning.Version
	 **/
	public static volatile EntityType<Version> class_;
	
	/**
	 * @see org.dspace.versioning.Version#versionDate
	 **/
	public static volatile SingularAttribute<Version, Instant> versionDate;
	
	/**
	 * @see org.dspace.versioning.Version#versionNumber
	 **/
	public static volatile SingularAttribute<Version, Integer> versionNumber;

	public static final String SUMMARY = "summary";
	public static final String ITEM = "item";
	public static final String VERSION_HISTORY = "versionHistory";
	public static final String E_PERSON = "ePerson";
	public static final String ID = "id";
	public static final String VERSION_DATE = "versionDate";
	public static final String VERSION_NUMBER = "versionNumber";

}

