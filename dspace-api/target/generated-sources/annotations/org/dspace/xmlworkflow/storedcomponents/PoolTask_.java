package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;

@StaticMetamodel(PoolTask.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class PoolTask_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#ePerson
	 **/
	public static volatile SingularAttribute<PoolTask, EPerson> ePerson;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#stepId
	 **/
	public static volatile SingularAttribute<PoolTask, String> stepId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#actionId
	 **/
	public static volatile SingularAttribute<PoolTask, String> actionId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#id
	 **/
	public static volatile SingularAttribute<PoolTask, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#workflowItem
	 **/
	public static volatile SingularAttribute<PoolTask, XmlWorkflowItem> workflowItem;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask
	 **/
	public static volatile EntityType<PoolTask> class_;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#workflowId
	 **/
	public static volatile SingularAttribute<PoolTask, String> workflowId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.PoolTask#group
	 **/
	public static volatile SingularAttribute<PoolTask, Group> group;

	public static final String E_PERSON = "ePerson";
	public static final String STEP_ID = "stepId";
	public static final String ACTION_ID = "actionId";
	public static final String ID = "id";
	public static final String WORKFLOW_ITEM = "workflowItem";
	public static final String WORKFLOW_ID = "workflowId";
	public static final String GROUP = "group";

}

