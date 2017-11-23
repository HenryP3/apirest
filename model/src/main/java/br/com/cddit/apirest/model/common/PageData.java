package br.com.cddit.apirest.model.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageData {
	private final int firstResult;
	private final int maxResults;
	private final String orderField;
	private final OrderMode orderMode;

	public enum OrderMode {
		ASCENDING, DESCENDING
	}

	public PageData(final int firstResult, final int maxResults, final String orderField,
			final OrderMode orderMode) {
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.orderField = orderField;
		this.orderMode = orderMode;
	}

	public boolean isAscending() {
		return OrderMode.ASCENDING.equals(orderMode);
	}
}