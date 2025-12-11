package org.dspace.app.ldn;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Item;

@StaticMetamodel(NotifyPatternToTrigger.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class NotifyPatternToTrigger_ {

	
	/**
	 * @see org.dspace.app.ldn.NotifyPatternToTrigger#item
	 **/
	public static volatile SingularAttribute<NotifyPatternToTrigger, Item> item;
	
	/**
	 * @see org.dspace.app.ldn.NotifyPatternToTrigger#pattern
	 **/
	public static volatile SingularAttribute<NotifyPatternToTrigger, String> pattern;
	
	/**
	 * @see org.dspace.app.ldn.NotifyPatternToTrigger#id
	 **/
	public static volatile SingularAttribute<NotifyPatternToTrigger, Integer> id;
	
	/**
	 * @see org.dspace.app.ldn.NotifyPatternToTrigger
	 **/
	public static volatile EntityType<NotifyPatternToTrigger> class_;
	
	/**
	 * @see org.dspace.app.ldn.NotifyPatternToTrigger#notifyService
	 **/
	public static volatile SingularAttribute<NotifyPatternToTrigger, NotifyServiceEntity> notifyService;

	public static final String ITEM = "item";
	public static final String PATTERN = "pattern";
	public static final String ID = "id";
	public static final String NOTIFY_SERVICE = "notifyService";

}

