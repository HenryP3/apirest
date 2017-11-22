package br.com.cddit.apirest.repository.common;

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

	public int getFirstResult() {
		return firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public String getOrderField() {
		return orderField;
	}

	public OrderMode getOrderMode() {
		return orderMode;
	}

	public boolean isAscending() {
		return OrderMode.ASCENDING.equals(orderMode);
	}

	@Override
	public String toString() {
		return "PaginationData [firstResult=" + firstResult + ", maxResults=" + maxResults + ", orderField="
				+ orderField + ", orderMode=" + orderMode + "]";
	}

}