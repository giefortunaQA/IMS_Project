package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IDomainDao<T> {
	T create(T t);
	
	List<T> readAll();

	T update(T t);

	int delete(long id);

	T modelFromResultSet(ResultSet resultSet) throws SQLException;
}
