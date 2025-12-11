package org.dspace.scripts;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import org.dspace.content.Bitstream;
import org.dspace.content.ProcessStatus;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;

@StaticMetamodel(Process.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Process_ {

	
	/**
	 * @see org.dspace.scripts.Process#processStatus
	 **/
	public static volatile SingularAttribute<Process, ProcessStatus> processStatus;
	
	/**
	 * @see org.dspace.scripts.Process#creationTime
	 **/
	public static volatile SingularAttribute<Process, Instant> creationTime;
	
	/**
	 * @see org.dspace.scripts.Process#processId
	 **/
	public static volatile SingularAttribute<Process, Integer> processId;
	
	/**
	 * @see org.dspace.scripts.Process#ePerson
	 **/
	public static volatile SingularAttribute<Process, EPerson> ePerson;
	
	/**
	 * @see org.dspace.scripts.Process#name
	 **/
	public static volatile SingularAttribute<Process, String> name;
	
	/**
	 * @see org.dspace.scripts.Process#finishedTime
	 **/
	public static volatile SingularAttribute<Process, Instant> finishedTime;
	
	/**
	 * @see org.dspace.scripts.Process#groups
	 **/
	public static volatile ListAttribute<Process, Group> groups;
	
	/**
	 * @see org.dspace.scripts.Process#startTime
	 **/
	public static volatile SingularAttribute<Process, Instant> startTime;
	
	/**
	 * @see org.dspace.scripts.Process
	 **/
	public static volatile EntityType<Process> class_;
	
	/**
	 * @see org.dspace.scripts.Process#parameters
	 **/
	public static volatile SingularAttribute<Process, String> parameters;
	
	/**
	 * @see org.dspace.scripts.Process#bitstreams
	 **/
	public static volatile ListAttribute<Process, Bitstream> bitstreams;

	public static final String PROCESS_STATUS = "processStatus";
	public static final String CREATION_TIME = "creationTime";
	public static final String PROCESS_ID = "processId";
	public static final String E_PERSON = "ePerson";
	public static final String NAME = "name";
	public static final String FINISHED_TIME = "finishedTime";
	public static final String GROUPS = "groups";
	public static final String START_TIME = "startTime";
	public static final String PARAMETERS = "parameters";
	public static final String BITSTREAMS = "bitstreams";

}

