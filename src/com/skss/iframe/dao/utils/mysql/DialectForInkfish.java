package com.skss.iframe.dao.utils.mysql;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;

public class DialectForInkfish extends MySQL5Dialect{
	public DialectForInkfish() {
		super();
		registerHibernateType(Types.LONGVARCHAR, 65535, "text");
	}
}
