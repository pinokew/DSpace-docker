package org.dspace.xmlworkflow.storedcomponents;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.eperson.EPerson;

@StaticMetamodel(InProgressUser.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class InProgressUser_ {

	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.InProgressUser#ePerson
	 **/
	public static volatile SingularAttribute<InProgressUser, EPerson> ePerson;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.InProgressUser#finished
	 **/
	public static volatile SingularAttribute<InProgressUser, Boolean> finished;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.InProgressUser#id
	 **/
	public static volatile SingularAttribute<InProgressUser, Integer> id;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.InProgressUser#workflowItem
	 **/
	public static volatile SingularAttribute<InProgressUser, XmlWorkflowItem> workflowItem;
	
	/**
	 * @see org.dspace.xmlworkflow.storedcomponents.InProgressUser
	 **/
	public static volatile EntityType<InProgressUser> class_;

	public static final String E_PERSON = "ePerson";
	public static final String FINISHED = "finished";
	public static final String ID = "id";
	public static final String WORKFLOW_ITEM = "workflowItem";

}

