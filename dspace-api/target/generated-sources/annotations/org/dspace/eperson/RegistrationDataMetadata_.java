package org.dspace.eperson;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.MetadataField;

@StaticMetamodel(RegistrationDataMetadata.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RegistrationDataMetadata_ {

	
	/**
	 * @see org.dspace.eperson.RegistrationDataMetadata#id
	 **/
	public static volatile SingularAttribute<RegistrationDataMetadata, Integer> id;
	
	/**
	 * @see org.dspace.eperson.RegistrationDataMetadata#registrationData
	 **/
	public static volatile SingularAttribute<RegistrationDataMetadata, RegistrationData> registrationData;
	
	/**
	 * @see org.dspace.eperson.RegistrationDataMetadata
	 **/
	public static volatile EntityType<RegistrationDataMetadata> class_;
	
	/**
	 * @see org.dspace.eperson.RegistrationDataMetadata#metadataField
	 **/
	public static volatile SingularAttribute<RegistrationDataMetadata, MetadataField> metadataField;
	
	/**
	 * @see org.dspace.eperson.RegistrationDataMetadata#value
	 **/
	public static volatile SingularAttribute<RegistrationDataMetadata, String> value;

	public static final String ID = "id";
	public static final String REGISTRATION_DATA = "registrationData";
	public static final String METADATA_FIELD = "metadataField";
	public static final String VALUE = "value";

}

