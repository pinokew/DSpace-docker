package org.dspace.content;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BitstreamFormat.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class BitstreamFormat_ {

	
	/**
	 * @see org.dspace.content.BitstreamFormat#fileExtensions
	 **/
	public static volatile ListAttribute<BitstreamFormat, String> fileExtensions;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#internal
	 **/
	public static volatile SingularAttribute<BitstreamFormat, Boolean> internal;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#description
	 **/
	public static volatile SingularAttribute<BitstreamFormat, String> description;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#mimetype
	 **/
	public static volatile SingularAttribute<BitstreamFormat, String> mimetype;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#id
	 **/
	public static volatile SingularAttribute<BitstreamFormat, Integer> id;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#shortDescription
	 **/
	public static volatile SingularAttribute<BitstreamFormat, String> shortDescription;
	
	/**
	 * @see org.dspace.content.BitstreamFormat#supportLevel
	 **/
	public static volatile SingularAttribute<BitstreamFormat, Integer> supportLevel;
	
	/**
	 * @see org.dspace.content.BitstreamFormat
	 **/
	public static volatile EntityType<BitstreamFormat> class_;

	public static final String FILE_EXTENSIONS = "fileExtensions";
	public static final String INTERNAL = "internal";
	public static final String DESCRIPTION = "description";
	public static final String MIMETYPE = "mimetype";
	public static final String ID = "id";
	public static final String SHORT_DESCRIPTION = "shortDescription";
	public static final String SUPPORT_LEVEL = "supportLevel";

}

