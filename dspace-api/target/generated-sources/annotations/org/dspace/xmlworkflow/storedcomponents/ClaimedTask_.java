package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.EPerson;

@StaticMetamodel(ClaimedTask.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class ClaimedTask_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#owner
	 **/
	public static volatile SingularAttribute<ClaimedTask, EPerson> owner;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#stepId
	 **/
	public static volatile SingularAttribute<ClaimedTask, String> stepId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#actionId
	 **/
	public static volatile SingularAttribute<ClaimedTask, String> actionId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#id
	 **/
	public static volatile SingularAttribute<ClaimedTask, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#workflowItem
	 **/
	public static volatile SingularAttribute<ClaimedTask, XmlWorkflowItem> workflowItem;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask
	 **/
	public static volatile EntityType<ClaimedTask> class_;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.ClaimedTask#workflowId
	 **/
	public static volatile SingularAttribute<ClaimedTask, String> workflowId;

	public static final String OWNER = "owner";
	public static final String STEP_ID = "stepId";
	public static final String ACTION_ID = "actionId";
	public static final String ID = "id";
	public static final String WORKFLOW_ITEM = "workflowItem";
	public static final String WORKFLOW_ID = "workflowId";

}

