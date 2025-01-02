package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.FeedBackRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.FeedBackResponse;
import iuh.fit.dhktpm117ctt.group06.entities.FeedBack;

import java.util.List;
import java.util.Optional;

public interface FeedBackService {
    Optional<FeedBackResponse> save(String userId, String productId, FeedBackRequest feedBackRequest);
    List<FeedBackResponse> findByProduct(String productId);
    Optional<FeedBackResponse> findByUser(String userId);
    Optional<FeedBackResponse> findByOrder(String orderId);
}
