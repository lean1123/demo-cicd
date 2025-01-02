package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.FeedBackRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.FeedBackResponse;
import iuh.fit.dhktpm117ctt.group06.entities.FeedBack;
import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.jwt.JwtProvider;
import iuh.fit.dhktpm117ctt.group06.repository.FeedBackRepository;
import iuh.fit.dhktpm117ctt.group06.repository.ProductRepository;
import iuh.fit.dhktpm117ctt.group06.repository.UserRepository;
import iuh.fit.dhktpm117ctt.group06.service.FeedBackService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    private FeedBackRepository feedBackRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private JwtProvider jwtProvider;
    private ModelMapper modelMapper= new ModelMapper();

    @Autowired
    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, JwtProvider jwtProvider, UserRepository userRepository, ProductRepository productRepository) {
        this.feedBackRepository = feedBackRepository;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    private FeedBackResponse mapToFeedBackResponse(FeedBack feedBack) {
        return modelMapper.map(feedBack, FeedBackResponse.class);
    }

    private FeedBack mapToFeedBack(FeedBackRequest feedBackRequest) {
        return modelMapper.map(feedBackRequest, FeedBack.class);
    }

    @Override
    public Optional<FeedBackResponse> save(String userId, String productId, FeedBackRequest feedBackRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        // Kiểm tra nếu không tìm thấy User hoặc Product, trả về Optional trống
        if (optionalUser.isEmpty() || optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        User user = optionalUser.get();
        Product product = optionalProduct.get();

        FeedBack feedBack = mapToFeedBack(feedBackRequest); // Chuyển từ FeedBackRequest sang FeedBack

        feedBack.setUser(user); // Gán User vào FeedBack
        feedBack.setProduct(product); // Gán Product vào FeedBack


        return Optional.of(mapToFeedBackResponse(feedBackRepository.save(feedBack)));
    }

    @Override
    public List<FeedBackResponse> findByProduct(String productId) {
        List<FeedBack> feedbackList = feedBackRepository.findByProductId(productId);

        // Map each FeedBack to FeedBackResponse
        return feedbackList.stream()
                .map(this::mapToFeedBackResponse)
                .toList();
    }

    @Override
    public Optional<FeedBackResponse> findByUser(String userId) {
        FeedBack feedBack= feedBackRepository.findFirstByUserId(userId).orElse(null);
        return Optional.of(mapToFeedBackResponse(feedBack));
    }

    @Override
    public Optional<FeedBackResponse> findByOrder(String orderId) {

        FeedBack feedBack= feedBackRepository.findFirstByOrderId(orderId).orElse(null);
        return Optional.of(mapToFeedBackResponse(feedBack));
    }
}
