package com.lams.loaring.config;

/**
 *
 * @since 2023.11.13
 * @author vic
 * @since 1.0

 * @param <T> 입력 타입
 * @param <R> 리턴 타입
 *
 */
public interface BaseEncryption<T, R> {
	R encrypt(T value) throws Exception;

	T decrypt(R encryptedValue) throws Exception;

}
