package iuh.fit.dhktpm117ctt.group06.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDetailRequest {
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String cartId;
    private String productId;
    private int quantity;
}
