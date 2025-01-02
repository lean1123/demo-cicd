package iuh.fit.dhktpm117ctt.group06.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListProductItemsPagegination {
	private int totalPage;
	private int currentPage;
	private long totalItems;
	private int pageSize;
	private List<ProductItemResponse> data;
}
