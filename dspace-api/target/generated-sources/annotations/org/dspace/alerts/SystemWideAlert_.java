package org.dspace.alerts;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.ZonedDateTime;

@StaticMetamodel(SystemWideAlert.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SystemWideAlert_ {

	
	/**
	 * @see org.dspace.alerts.SystemWideAlert#countdownTo
	 **/
	public static volatile SingularAttribute<SystemWideAlert, ZonedDateTime> countdownTo;
	
	/**
	 * @see org.dspace.alerts.SystemWideAlert#allowSessions
	 **/
	public static volatile SingularAttribute<SystemWideAlert, String> allowSessions;
	
	/**
	 * @see org.dspace.alerts.SystemWideAlert#active
	 **/
	public static volatile SingularAttribute<SystemWideAlert, Boolean> active;
	
	/**
	 * @see org.dspace.alerts.SystemWideAlert#alertId
	 **/
	public static volatile SingularAttribute<SystemWideAlert, Integer> alertId;
	
	/**
	 * @see org.dspace.alerts.SystemWideAlert#message
	 **/
	public static volatile SingularAttribute<SystemWideAlert, String> message;
	
	/**
	 * @see org.dspace.alerts.SystemWideAlert
	 **/
	public static volatile EntityType<SystemWideAlert> class_;

	public static final String COUNTDOWN_TO = "countdownTo";
	public static final String ALLOW_SESSIONS = "allowSessions";
	public static final String ACTIVE = "active";
	public static final String ALERT_ID = "alertId";
	public static final String MESSAGE = "message";

}

