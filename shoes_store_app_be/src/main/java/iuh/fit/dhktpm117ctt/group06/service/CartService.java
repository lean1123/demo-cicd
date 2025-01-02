package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.entities.Cart;

public interface CartService {
    Cart findCartByUser(String userId);
    Cart saveSessionItemsToDatabase(String sessionId, String userId);
	<S extends Cart> S save(S entity);
}
