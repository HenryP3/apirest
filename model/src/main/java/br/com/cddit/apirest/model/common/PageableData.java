package br.com.cddit.apirest.model.common;

import java.io.Serializable;
import java.util.List;

public class PageableData<T extends Serializable> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3222174050460258035L;
	private final int numberOfRows;
	private final List<T> rows;

	public PageableData(final int numberOfRows, final List<T> rows) {
		this.numberOfRows = numberOfRows;
		this.rows = rows;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public List<T> getRows() {
		return rows;
	}

	public T getRow(final int index) {
		if (index >= rows.size()) {
			return null;
		}
		return rows.get(index);
	}

	@Override
	public String toString() {
		return "PaginatedData [numberOfRows=" + numberOfRows + ", rows=" + rows + "]";
	}

}