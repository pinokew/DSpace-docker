package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Collection;
import org.dspace.content.Item;

@StaticMetamodel(XmlWorkflowItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class XmlWorkflowItem_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#item
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Item> item;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#multipleTitles
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Boolean> multipleTitles;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#id
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#collection
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Collection> collection;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem
	 **/
	public static volatile EntityType<XmlWorkflowItem> class_;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#publishedBefore
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Boolean> publishedBefore;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.XmlWorkflowItem#multipleFiles
	 **/
	public static volatile SingularAttribute<XmlWorkflowItem, Boolean> multipleFiles;

	public static final String ITEM = "item";
	public static final String MULTIPLE_TITLES = "multipleTitles";
	public static final String ID = "id";
	public static final String COLLECTION = "collection";
	public static final String PUBLISHED_BEFORE = "publishedBefore";
	public static final String MULTIPLE_FILES = "multipleFiles";

}

