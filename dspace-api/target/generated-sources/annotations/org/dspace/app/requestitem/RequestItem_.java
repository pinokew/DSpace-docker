package org.dspace.app.requestitem;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Bitstream;
import org.dspace.content.Item;

@StaticMetamodel(RequestItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class RequestItem_ {

	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#item
	 **/
	public static volatile SingularAttribute<RequestItem, Item> item;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#expires
	 **/
	public static volatile SingularAttribute<RequestItem, Instant> expires;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#token
	 **/
	public static volatile SingularAttribute<RequestItem, String> token;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#access_expiry
	 **/
	public static volatile SingularAttribute<RequestItem, Instant> access_expiry;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#reqEmail
	 **/
	public static volatile SingularAttribute<RequestItem, String> reqEmail;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#decision_date
	 **/
	public static volatile SingularAttribute<RequestItem, Instant> decision_date;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#access_token
	 **/
	public static volatile SingularAttribute<RequestItem, String> access_token;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#reqName
	 **/
	public static volatile SingularAttribute<RequestItem, String> reqName;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#reqMessage
	 **/
	public static volatile SingularAttribute<RequestItem, String> reqMessage;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#request_date
	 **/
	public static volatile SingularAttribute<RequestItem, Instant> request_date;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#bitstream
	 **/
	public static volatile SingularAttribute<RequestItem, Bitstream> bitstream;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#allfiles
	 **/
	public static volatile SingularAttribute<RequestItem, Boolean> allfiles;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#accept_request
	 **/
	public static volatile SingularAttribute<RequestItem, Boolean> accept_request;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem
	 **/
	public static volatile EntityType<RequestItem> class_;
	
	/**
	 * @see org.dspace.app.requestitem.RequestItem#requestitem_id
	 **/
	public static volatile SingularAttribute<RequestItem, Integer> requestitem_id;

	public static final String ITEM = "item";
	public static final String EXPIRES = "expires";
	public static final String TOKEN = "token";
	public static final String ACCESS_EXPIRY = "access_expiry";
	public static final String REQ_EMAIL = "reqEmail";
	public static final String DECISION_DATE = "decision_date";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String REQ_NAME = "reqName";
	public static final String REQ_MESSAGE = "reqMessage";
	public static final String REQUEST_DATE = "request_date";
	public static final String BITSTREAM = "bitstream";
	public static final String ALLFILES = "allfiles";
	public static final String ACCEPT_REQUEST = "accept_request";
	public static final String REQUESTITEM_ID = "requestitem_id";

}

