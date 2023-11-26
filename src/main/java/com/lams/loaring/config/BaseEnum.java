package com.lams.loaring.config;


public interface BaseEnum<T> {

	String getKey();

	T getCode();

	String getComment();
}
