package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;

@StaticMetamodel(WorkflowItemRole.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class WorkflowItemRole_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole#roleId
	 **/
	public static volatile SingularAttribute<WorkflowItemRole, String> roleId;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole#ePerson
	 **/
	public static volatile SingularAttribute<WorkflowItemRole, EPerson> ePerson;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole#id
	 **/
	public static volatile SingularAttribute<WorkflowItemRole, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole#workflowItem
	 **/
	public static volatile SingularAttribute<WorkflowItemRole, XmlWorkflowItem> workflowItem;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole
	 **/
	public static volatile EntityType<WorkflowItemRole> class_;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.WorkflowItemRole#group
	 **/
	public static volatile SingularAttribute<WorkflowItemRole, Group> group;

	public static final String ROLE_ID = "roleId";
	public static final String E_PERSON = "ePerson";
	public static final String ID = "id";
	public static final String WORKFLOW_ITEM = "workflowItem";
	public static final String GROUP = "group";

}

