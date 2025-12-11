package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(EPerson.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class EPerson_ extends org.dspace.content.DSpaceObject_ {

	
	/**
	 * @see org.dspace.eperson.EPerson#salt
	 **/
	public static volatile SingularAttribute<EPerson, String> salt;
	
	/**
	 * @see org.dspace.eperson.EPerson#lastActive
	 **/
	public static volatile SingularAttribute<EPerson, Instant> lastActive;
	
	/**
	 * @see org.dspace.eperson.EPerson#sessionSalt
	 **/
	public static volatile SingularAttribute<EPerson, String> sessionSalt;
	
	/**
	 * @see org.dspace.eperson.EPerson#netid
	 **/
	public static volatile SingularAttribute<EPerson, String> netid;
	
	/**
	 * @see org.dspace.eperson.EPerson#requireCertificate
	 **/
	public static volatile SingularAttribute<EPerson, Boolean> requireCertificate;
	
	/**
	 * @see org.dspace.eperson.EPerson#groups
	 **/
	public static volatile ListAttribute<EPerson, Group> groups;
	
	/**
	 * @see org.dspace.eperson.EPerson#digestAlgorithm
	 **/
	public static volatile SingularAttribute<EPerson, String> digestAlgorithm;
	
	/**
	 * @see org.dspace.eperson.EPerson#selfRegistered
	 **/
	public static volatile SingularAttribute<EPerson, Boolean> selfRegistered;
	
	/**
	 * @see org.dspace.eperson.EPerson#canLogIn
	 **/
	public static volatile SingularAttribute<EPerson, Boolean> canLogIn;
	
	/**
	 * @see org.dspace.eperson.EPerson#password
	 **/
	public static volatile SingularAttribute<EPerson, String> password;
	
	/**
	 * @see org.dspace.eperson.EPerson#legacyId
	 **/
	public static volatile SingularAttribute<EPerson, Integer> legacyId;
	
	/**
	 * @see org.dspace.eperson.EPerson
	 **/
	public static volatile EntityType<EPerson> class_;
	
	/**
	 * @see org.dspace.eperson.EPerson#email
	 **/
	public static volatile SingularAttribute<EPerson, String> email;

	public static final String SALT = "salt";
	public static final String LAST_ACTIVE = "lastActive";
	public static final String SESSION_SALT = "sessionSalt";
	public static final String NETID = "netid";
	public static final String REQUIRE_CERTIFICATE = "requireCertificate";
	public static final String GROUPS = "groups";
	public static final String DIGEST_ALGORITHM = "digestAlgorithm";
	public static final String SELF_REGISTERED = "selfRegistered";
	public static final String CAN_LOG_IN = "canLogIn";
	public static final String PASSWORD = "password";
	public static final String LEGACY_ID = "legacyId";
	public static final String EMAIL = "email";

}

