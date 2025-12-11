package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(RegistrationData.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RegistrationData_ {

	
	/**
	 * @see org.dspace.eperson.RegistrationData#expires
	 **/
	public static volatile SingularAttribute<RegistrationData, Instant> expires;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#metadata
	 **/
	public static volatile SetAttribute<RegistrationData, RegistrationDataMetadata> metadata;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#registrationType
	 **/
	public static volatile SingularAttribute<RegistrationData, RegistrationTypeEnum> registrationType;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#netId
	 **/
	public static volatile SingularAttribute<RegistrationData, String> netId;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#id
	 **/
	public static volatile SingularAttribute<RegistrationData, Integer> id;
	
	/**
	 * @see org.dspace.eperson.RegistrationData
	 **/
	public static volatile EntityType<RegistrationData> class_;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#email
	 **/
	public static volatile SingularAttribute<RegistrationData, String> email;
	
	/**
	 * @see org.dspace.eperson.RegistrationData#token
	 **/
	public static volatile SingularAttribute<RegistrationData, String> token;

	public static final String EXPIRES = "expires";
	public static final String METADATA = "metadata";
	public static final String REGISTRATION_TYPE = "registrationType";
	public static final String NET_ID = "netId";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String TOKEN = "token";

}

