package com.qa.ims.controller;

import java.util.List;

public interface ICrudController<T> {

	T create();
	
	List<T> readAll();

	T update();

	int delete();

}
