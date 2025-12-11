package org.dspace.app.ldn;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(NotifyServiceInboundPattern.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class NotifyServiceInboundPattern_ {

	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern#pattern
	 **/
	public static volatile SingularAttribute<NotifyServiceInboundPattern, String> pattern;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern#automatic
	 **/
	public static volatile SingularAttribute<NotifyServiceInboundPattern, Boolean> automatic;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern#constraint
	 **/
	public static volatile SingularAttribute<NotifyServiceInboundPattern, String> constraint;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern#id
	 **/
	public static volatile SingularAttribute<NotifyServiceInboundPattern, Integer> id;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern
	 **/
	public static volatile EntityType<NotifyServiceInboundPattern> class_;
	
	/**
	 * @see org.dspace.app.ldn.NotifyServiceInboundPattern#notifyService
	 **/
	public static volatile SingularAttribute<NotifyServiceInboundPattern, NotifyServiceEntity> notifyService;

	public static final String PATTERN = "pattern";
	public static final String AUTOMATIC = "automatic";
	public static final String CONSTRAINT = "constraint";
	public static final String ID = "id";
	public static final String NOTIFY_SERVICE = "notifyService";

}

