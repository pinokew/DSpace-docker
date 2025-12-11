package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(WorkspaceItem.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class WorkspaceItem_ {

	
	/**
	 * @see org.dspace.content.WorkspaceItem#item
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Item> item;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#multipleTitles
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Boolean> multipleTitles;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#workspaceItemId
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Integer> workspaceItemId;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#collection
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Collection> collection;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#stageReached
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Integer> stageReached;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#pageReached
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Integer> pageReached;
	
	/**
	 * @see org.dspace.content.WorkspaceItem
	 **/
	public static volatile EntityType<WorkspaceItem> class_;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#publishedBefore
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Boolean> publishedBefore;
	
	/**
	 * @see org.dspace.content.WorkspaceItem#multipleFiles
	 **/
	public static volatile SingularAttribute<WorkspaceItem, Boolean> multipleFiles;

	public static final String ITEM = "item";
	public static final String MULTIPLE_TITLES = "multipleTitles";
	public static final String WORKSPACE_ITEM_ID = "workspaceItemId";
	public static final String COLLECTION = "collection";
	public static final String STAGE_REACHED = "stageReached";
	public static final String PAGE_REACHED = "pageReached";
	public static final String PUBLISHED_BEFORE = "publishedBefore";
	public static final String MULTIPLE_FILES = "multipleFiles";

}

