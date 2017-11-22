package br.com.cddit.apirest.repository.common;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageableData<T extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 342235888621929898L;

	private static final int INITIAL_PAGE = 1;

	public static final MyPageableData<Serializable> FIRST_PAGE = new MyPageableData<Serializable>(1, 10);

	private List<T> data;

	private T filter;

	private long totalElements;
	private int totalPages;
	private boolean first;
	private boolean last;
	private int page;
	private int pageSize;
	private String column = "";
	private String order = "";

	public MyPageableData(final int page, final int pageSize) {
		this();
		this.page = page;
		this.pageSize = pageSize;
	}

	public MyPageableData(final List<T> data, final long totalElements, final int totalPages, final boolean first,
			final boolean last) {
		this.data = data;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.first = first;
		this.last = last;
	}

	// public static <T extends Serializable> PageableData<T> valueOf(final Page<T>
	// page) {
	// return new PageableData<T>(page.getContent(), page.getTotalElements(),
	// page.getTotalPages(), page.isFirst(),
	// page.isLast());
	// }

	// public PageRequest buildPageable() {
	// if (order == null || order.isEmpty() || column == null || column.isEmpty()) {
	// return new PageRequest(page - 1, pageSize);
	// } else {
	// return new PageRequest(page - 1, pageSize,
	// Sort.Direction.fromStringOrNull(order), column);
	// }
	// }

	public static MyPageableData newInstance(final List<? extends Serializable> bts) {

		return new MyPageableData(bts, bts.size(),
				INITIAL_PAGE,
				Boolean.TRUE,
				Boolean.TRUE);
	}
}
