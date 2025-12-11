package org.dspace.orcid;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Item;
import org.dspace.eperson.EPerson;

@StaticMetamodel(OrcidToken.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class OrcidToken_ {

	
	/**
	 * @see org.dspace.orcid.OrcidToken#ePerson
	 **/
	public static volatile SingularAttribute<OrcidToken, EPerson> ePerson;
	
	/**
	 * @see org.dspace.orcid.OrcidToken#id
	 **/
	public static volatile SingularAttribute<OrcidToken, Integer> id;
	
	/**
	 * @see org.dspace.orcid.OrcidToken#accessToken
	 **/
	public static volatile SingularAttribute<OrcidToken, String> accessToken;
	
	/**
	 * @see org.dspace.orcid.OrcidToken
	 **/
	public static volatile EntityType<OrcidToken> class_;
	
	/**
	 * @see org.dspace.orcid.OrcidToken#profileItem
	 **/
	public static volatile SingularAttribute<OrcidToken, Item> profileItem;

	public static final String E_PERSON = "ePerson";
	public static final String ID = "id";
	public static final String ACCESS_TOKEN = "accessToken";
	public static final String PROFILE_ITEM = "profileItem";

}

