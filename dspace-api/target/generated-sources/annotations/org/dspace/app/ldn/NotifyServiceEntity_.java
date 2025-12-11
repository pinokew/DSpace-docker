package org.dspace.app.ldn;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

@StaticMetamodel(NotifyServiceEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class NotifyServiceEntity_ {

	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#score
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, BigDecimal> score;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#inboundPatterns
	 **/
	public static volatile ListAttribute<NotifyServiceEntity, NotifyServiceInboundPattern> inboundPatterns;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#ldnUrl
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> ldnUrl;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#name
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> name;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#upperIp
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> upperIp;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#description
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> description;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#id
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, Integer> id;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity
	 **/
	public static volatile EntityType<NotifyServiceEntity> class_;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#usesActorEmailId
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, Boolean> usesActorEmailId;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#url
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> url;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#enabled
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, Boolean> enabled;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceEntity#lowerIp
	 **/
	public static volatile SingularAttribute<NotifyServiceEntity, String> lowerIp;

	public static final String SCORE = "score";
	public static final String INBOUND_PATTERNS = "inboundPatterns";
	public static final String LDN_URL = "ldnUrl";
	public static final String NAME = "name";
	public static final String UPPER_IP = "upperIp";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String USES_ACTOR_EMAIL_ID = "usesActorEmailId";
	public static final String URL = "url";
	public static final String ENABLED = "enabled";
	public static final String LOWER_IP = "lowerIp";

}

