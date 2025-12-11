package org.dspace.app.ldn;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.DSpaceObject;

@StaticMetamodel(LDNMessageEntity.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class LDNMessageEntity_ {

	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#origin
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, NotifyServiceEntity> origin;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#inReplyTo
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, LDNMessageEntity> inReplyTo;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#message
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> message;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#type
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> type;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#target
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, NotifyServiceEntity> target;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#queueAttempts
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, Integer> queueAttempts;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#activityStreamType
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> activityStreamType;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#queueLastStartTime
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, Instant> queueLastStartTime;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#sourceIp
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> sourceIp;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#queueStatus
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, Integer> queueStatus;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#context
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, DSpaceObject> context;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#id
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> id;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity
	 **/
	public static volatile EntityType<LDNMessageEntity> class_;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#queueTimeout
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, Instant> queueTimeout;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#coarNotifyType
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, String> coarNotifyType;
	
	/**
	 * @see org.dspace.app.ldn.LDNMessageEntity#object
	 **/
	public static volatile SingularAttribute<LDNMessageEntity, DSpaceObject> object;

	public static final String ORIGIN = "origin";
	public static final String IN_REPLY_TO = "inReplyTo";
	public static final String MESSAGE = "message";
	public static final String TYPE = "type";
	public static final String TARGET = "target";
	public static final String QUEUE_ATTEMPTS = "queueAttempts";
	public static final String ACTIVITY_STREAM_TYPE = "activityStreamType";
	public static final String QUEUE_LAST_START_TIME = "queueLastStartTime";
	public static final String SOURCE_IP = "sourceIp";
	public static final String QUEUE_STATUS = "queueStatus";
	public static final String CONTEXT = "context";
	public static final String ID = "id";
	public static final String QUEUE_TIMEOUT = "queueTimeout";
	public static final String COAR_NOTIFY_TYPE = "coarNotifyType";
	public static final String OBJECT = "object";

}

