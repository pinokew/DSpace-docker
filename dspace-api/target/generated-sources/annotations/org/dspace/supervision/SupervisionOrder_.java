package org.dspace.supervision;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.dspace.content.Item;
import org.dspace.eperson.Group;

@StaticMetamodel(SupervisionOrder.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class SupervisionOrder_ {

	
	/**
	 * @see org.dspace.supervision.SupervisionOrder#item
	 **/
	public static volatile SingularAttribute<SupervisionOrder, Item> item;
	
	/**
	 * @see org.dspace.supervision.SupervisionOrder#id
	 **/
	public static volatile SingularAttribute<SupervisionOrder, Integer> id;
	
	/**
	 * @see org.dspace.supervision.SupervisionOrder
	 **/
	public static volatile EntityType<SupervisionOrder> class_;
	
	/**
	 * @see org.dspace.supervision.SupervisionOrder#group
	 **/
	public static volatile SingularAttribute<SupervisionOrder, Group> group;

	public static final String ITEM = "item";
	public static final String ID = "id";
	public static final String GROUP = "group";

}

