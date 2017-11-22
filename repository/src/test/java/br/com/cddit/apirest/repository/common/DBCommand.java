package br.com.cddit.apirest.repository.common;

import org.junit.Ignore;

@Ignore
public interface DBCommand<T> {

	T execute();
}
