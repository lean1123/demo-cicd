package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.CartDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.CartDetailResponse;
import iuh.fit.dhktpm117ctt.group06.entities.CartDetail;
import iuh.fit.dhktpm117ctt.group06.entities.CartDetailPK;

import java.util.List;
import java.util.Optional;

public interface CartDetailService {
    List<CartDetailResponse> findAllCartDetailByCart(String cartId);
    Optional<CartDetailResponse> addToCart(CartDetailRequest cartDetailRequest);
    void deleteById(CartDetailPK cartDetailPK);
	Optional<CartDetail> updateQuantity(CartDetailPK cartDetailPK, int newQuantity);
	<S extends CartDetail> S save(S entity);
	Optional<CartDetail> findById(CartDetailPK id);
	
}
