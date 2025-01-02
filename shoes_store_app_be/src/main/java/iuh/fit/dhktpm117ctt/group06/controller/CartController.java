package iuh.fit.dhktpm117ctt.group06.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.dhktpm117ctt.group06.dto.response.CartDetailSessionDTO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import iuh.fit.dhktpm117ctt.group06.dto.request.CartDetailRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.CartDetailResponse;
import iuh.fit.dhktpm117ctt.group06.dto.response.UserResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Cart;
import iuh.fit.dhktpm117ctt.group06.entities.CartDetail;
import iuh.fit.dhktpm117ctt.group06.entities.CartDetailPK;
import iuh.fit.dhktpm117ctt.group06.entities.Product;
import iuh.fit.dhktpm117ctt.group06.entities.ProductItem;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.service.CartDetailService;
import iuh.fit.dhktpm117ctt.group06.service.CartService;
import iuh.fit.dhktpm117ctt.group06.service.ProductItemService;
import iuh.fit.dhktpm117ctt.group06.service.ProductService;
import iuh.fit.dhktpm117ctt.group06.service.UserService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

//	@PostMapping("/addToCart")
//	public ResponseEntity<?> addToCart(HttpSession httpSession, @RequestBody CartDetailRequest cartDetailRequest) {
//		Map<String, Object> response = new LinkedHashMap<>();
//
//		List<CartDetail> cartDetails = getCartFromSession(httpSession);
//
//
//		try {
//			handleCartDetail(cartDetails, cartDetailRequest, httpSession);
//		} catch (IllegalArgumentException e) {
//			// TODO: handle exception
//			response.put("status", HttpStatus.BAD_REQUEST.value());
//			response.put("data", "Error in add to cart!");
//			return ResponseEntity.badRequest().body(response);
//		}
//		httpSession.setAttribute("cart", cartDetails);
//
//		try {
//			syncCartWithDatabase(cartDetails);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		List<CartDetailResponse> cartDetailResponses = new ArrayList<>();
//
//		for (CartDetail cartDetail : cartDetails) {
//			Product product = productService.findById(cartDetail.getProductItem().getProduct().getId()).get();
//			cartDetailResponses.add(CartDetailResponse.builder().cartDetailPK(cartDetail.getCartDetailPK())
//					.productItem(cartDetail.getProductItem()).quantity(cartDetail.getQuantity())
//					.product(product).build());
//		}
//
//		response.put("status", HttpStatus.OK.value());
//		response.put("data", cartDetailResponses);
//		return ResponseEntity.ok(response);
//	}

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(HttpSession httpSession, @RequestBody CartDetailRequest cartDetailRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<CartDetail> cartDetails = getCartFromSession(httpSession);

        try {
            handleCartDetail(cartDetails, cartDetailRequest, httpSession);
        } catch (IllegalArgumentException e) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Error in add to cart!");
            return ResponseEntity.badRequest().body(response);
        }

        try {
//            ObjectMapper objectMapper = new ObjectMapper();
            String cartDetailsJson = objectMapper.writeValueAsString(cartDetails);
            httpSession.setAttribute("cart", cartDetailsJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            syncCartWithDatabase(cartDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CartDetailResponse> cartDetailResponses = new ArrayList<>();
        for (CartDetail cartDetail : cartDetails) {

            ProductItem productItem = productItemService.findById(cartDetail.getProductItem().getId()).get();

            Product product = productService.findById(productItem.getProduct().getId()).get();



            cartDetailResponses.add(CartDetailResponse.builder().cartDetailPK(cartDetail.getCartDetailPK())
                    .productItem(productItem).quantity(cartDetail.getQuantity())
                    .product(product).build());
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", cartDetailResponses);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<?> updateQuantity(HttpSession session, @RequestBody CartDetailRequest cartDetailRequest) {

        System.out.println("updateQuantity: " + cartDetailRequest.toString());

        Map<String, Object> response = new LinkedHashMap();
//
        List<CartDetail> cartDetails = getCartFromSession(session);



        if (cartDetails == null) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Cart is empty");
            return ResponseEntity.badRequest().body(response);
        }


        Optional<ProductItem> productItemOptional = productItemService.findById(cartDetailRequest.getProductId());

        if (productItemOptional.isEmpty()) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Error in update quantity!");
            return ResponseEntity.badRequest().body(response);
        }

        ProductItem item = productItemOptional.get();

        for (CartDetail cartDetail : cartDetails) {
            if (cartDetail.getProductItem().getId().equals(cartDetailRequest.getProductId())) {
                if (cartDetailRequest.getQuantity() > item.getQuantity()) {
                    response.put("status", HttpStatus.BAD_REQUEST.value());
                    response.put("data", "Quantity must be less than current quantity");
                    return ResponseEntity.badRequest().body(response);
                }
                cartDetail.setQuantity(cartDetailRequest.getQuantity());
                break;
            }
        }

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String cartDetailsJson = objectMapper.writeValueAsString(cartDetails);
            session.setAttribute("cart", cartDetailsJson);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Failed to update quantity");
            return ResponseEntity.badRequest().body(response);
        }


        try {
            syncCartWithDatabase(cartDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<CartDetailResponse> cartDetailResponses = new ArrayList<>();

        for (CartDetail cartDetail : cartDetails) {

            ProductItem productItem = productItemService.findById(cartDetail.getProductItem().getId()).get();
            Product product = productService.findById(productItem.getProduct().getId()).get();

            cartDetailResponses.add(CartDetailResponse.builder().cartDetailPK(cartDetail.getCartDetailPK())
                    .productItem(cartDetail.getProductItem()).quantity(cartDetail.getQuantity())
                    .product(product).build());
        }


        response.put("status", HttpStatus.OK.value());
        response.put("data", cartDetailResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteCartDetail(@RequestParam String productId, HttpSession session) {
        Map<String, Object> response = new LinkedHashMap<>();

        Optional<ProductItem> optionalProductItem = productItemService.findById(productId);

        if (optionalProductItem.isEmpty()) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Product not found");
            return ResponseEntity.badRequest().body(response);
        }

//	    List<CartDetail> cartDetails = (List<CartDetail>) session.getAttribute("cart");

        String cartDetailsJson = (String) session.getAttribute("cart");

        List<CartDetail> cartDetails = new ArrayList<>();

        if (cartDetailsJson != null && !cartDetailsJson.isEmpty()) {
            try {
//                ObjectMapper objectMapper = new ObjectMapper();
                cartDetails = objectMapper.readValue(cartDetailsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CartDetail.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (cartDetails == null || cartDetails.isEmpty()) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Cart is empty");
            return ResponseEntity.badRequest().body(response);
        }


        Iterator<CartDetail> iterator = cartDetails.iterator();
        while (iterator.hasNext()) {
            CartDetail cartDetail = iterator.next();
            if (cartDetail.getProductItem().getId().equals(productId)) {
                iterator.remove();
                break;
            }
        }


        if (!cartDetails.isEmpty()) {
            session.setAttribute("cart", cartDetails);
        }


//	    var context = SecurityContextHolder.getContext();
//	    String emailString = context.getAuthentication().getName();
//	    if (emailString != null) {
//	        Optional<UserResponse> optionalUser = userService.findByEmail(emailString);
//	        if (optionalUser.isPresent()) {
//	            UserResponse user = optionalUser.get();
//	            Cart cart = cartService.findCartByUser(user.getId());
//
//
//	            CartDetailPK cartDetailPK = new CartDetailPK(cart.getId(), productId);
//	            cartDetailService.deleteById(cartDetailPK);
//	        }
//	    }
        try {
            syncCartWithDatabase(cartDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<CartDetailResponse> cartDetailResponses = new ArrayList<>();
        for (CartDetail cartDetail : cartDetails) {

            ProductItem productItem = productItemService.findById(cartDetail.getProductItem().getId()).get();

            Product product = productService.findById(productItem.getProduct().getId()).get();

            cartDetailResponses.add(CartDetailResponse.builder()
                    .cartDetailPK(cartDetail.getCartDetailPK())
                    .productItem(productItem)
                    .quantity(cartDetail.getQuantity())
                    .product(product)
                    .build());
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", cartDetailResponses);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> viewCart(HttpSession session) {
        Map<String, Object> response = new LinkedHashMap();

        String cartDetailsJson = (String) session.getAttribute("cart");

        List<CartDetail> cartDetails = new ArrayList<>();

        if (cartDetailsJson != null && !cartDetailsJson.isEmpty()) {
            try {
                System.out.println(cartDetailsJson);
                ObjectMapper objectMapper = new ObjectMapper();
                cartDetails = objectMapper.readValue(cartDetailsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CartDetail.class));
            } catch (Exception e) {
                e.printStackTrace();
                response.put("status", HttpStatus.BAD_REQUEST.value());
                response.put("data", "Error in view cart");
                return ResponseEntity.badRequest().body(response);
            }
        }

        if (cartDetails == null) {
            response.put("status", HttpStatus.OK.value());
            response.put("data", "Cart is empty");
            return ResponseEntity.ok(response);
        }

        try {
            syncCartWithDatabase(cartDetails);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Loi tai day");
            e.printStackTrace();
        }

        List<CartDetailResponse> cartDetailResponses = new ArrayList<>();

        for (CartDetail cartDetail : cartDetails) {

            ProductItem productItem = productItemService.findById(cartDetail.getProductItem().getId()).get();

            Product product = productService.findById(productItem.getProduct().getId()).get();

            cartDetailResponses.add(CartDetailResponse.builder().cartDetailPK(cartDetail.getCartDetailPK())
                    .productItem(productItem).quantity(cartDetail.getQuantity())
                    .product(product).build());
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", cartDetailResponses);
        return ResponseEntity.ok(response);
    }


//	@SuppressWarnings("unchecked")
//	private List<CartDetail> getCartFromSession(HttpSession httpSession) {
//		List<CartDetail> cartDetails = null;
//		try {
//			cartDetails = (List<CartDetail>) httpSession.getAttribute("cart");
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return cartDetails == null || cartDetails.isEmpty() ? new ArrayList<>() : cartDetails;
//	}

    @SuppressWarnings("unchecked")
    private List<CartDetail> getCartFromSession(HttpSession httpSession) {
        List<CartDetail> cartDetails = new ArrayList<>();
        try {
//            ObjectMapper objectMapper = new ObjectMapper();
            String cartDetailsJson = (String) httpSession.getAttribute("cart");

            if (cartDetailsJson != null && !cartDetailsJson.isEmpty()) {
                cartDetails = objectMapper.readValue(cartDetailsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CartDetail.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartDetails;
    }

    private void handleCartDetail(List<CartDetail> cartDetails, CartDetailRequest cartDetailRequest, HttpSession httpSession)
            throws IllegalArgumentException {

        Optional<ProductItem> productItemResponseOpt = productItemService.findById(cartDetailRequest.getProductId());

        if (productItemResponseOpt.isEmpty()) {
            throw new IllegalArgumentException("Product not found");
        }

        ProductItem productItem = productItemResponseOpt.get();

        for (CartDetail detail : cartDetails) {
            if (detail.getProductItem().getId().equals(cartDetailRequest.getProductId())) {
                int currentQty = detail.getQuantity() + cartDetailRequest.getQuantity();
                if (currentQty > productItem.getQuantity()) {
                    throw new IllegalArgumentException("Quantity must be less than current quantity");
                }

                detail.setQuantity(currentQty);
                return;
            }
        }

        CartDetail newCartDetail = new CartDetail();

        if (productItem.getQuantity() < cartDetailRequest.getQuantity()) {
            throw new IllegalArgumentException("Quantity must be less than current quantity");
        }

        newCartDetail.setQuantity(cartDetailRequest.getQuantity());
        newCartDetail.setProductItem(productItem);

        CartDetailPK cartDetailPK = new CartDetailPK();
        cartDetailPK.setCartId(httpSession.getId());
        cartDetailPK.setProductItemId(productItem.getId());

        newCartDetail.setCartDetailPK(cartDetailPK);

        cartDetails.add(newCartDetail);
    }

    private void syncCartWithDatabase(List<CartDetail> cartDetails) throws IllegalArgumentException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (email == null)
            return;

        Optional<UserResponse> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty())
            return;

        UserResponse user = userOpt.get();
        Cart cart = cartService.findCartByUser(user.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userService.getUserById(user.getId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found")));
            cartService.save(cart);
        }

        for (CartDetail cartDetail : cartDetails) {
            if (cartDetail.getCart() == null) {
                CartDetailPK cartDetailPK = new CartDetailPK(cart.getId(), cartDetail.getProductItem().getId());
                cartDetail.setCartDetailPK(cartDetailPK);
                cartDetail.setCart(cart);
                cartDetailService.save(cartDetail);
            }
        }
    }

}
