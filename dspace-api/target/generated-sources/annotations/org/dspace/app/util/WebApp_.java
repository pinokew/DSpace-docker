package org.dspace.app.util;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(WebApp.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class WebApp_ {

	
	/**
	 * @see org.dspace.app.util.WebApp#appName
	 **/
	public static volatile SingularAttribute<WebApp, String> appName;
	
	/**
	 * @see org.dspace.app.util.WebApp#started
	 **/
	public static volatile SingularAttribute<WebApp, Instant> started;
	
	/**
	 * @see org.dspace.app.util.WebApp#id
	 **/
	public static volatile SingularAttribute<WebApp, Integer> id;
	
	/**
	 * @see org.dspace.app.util.WebApp
	 **/
	public static volatile EntityType<WebApp> class_;
	
	/**
	 * @see org.dspace.app.util.WebApp#url
	 **/
	public static volatile SingularAttribute<WebApp, String> url;
	
	/**
	 * @see org.dspace.app.util.WebApp#isui
	 **/
	public static volatile SingularAttribute<WebApp, Integer> isui;

	public static final String APP_NAME = "appName";
	public static final String STARTED = "started";
	public static final String ID = "id";
	public static final String URL = "url";
	public static final String ISUI = "isui";

}

