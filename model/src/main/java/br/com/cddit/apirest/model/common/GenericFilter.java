package br.com.cddit.apirest.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GenericFilter {
	private PageData pageData;

	public GenericFilter(final PageData paginationData) {
		this.pageData = paginationData;
	}

	public boolean hasPaginationData() {
		return pageData != null;
	}

	public boolean hasOrderField() {
		return hasPaginationData() && pageData.getOrderField() != null;
	}

}