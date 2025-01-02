package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.dto.request.FeedBackRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.FeedBackResponse;
import iuh.fit.dhktpm117ctt.group06.service.FeedBackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedBackService feedBackService;

    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<?> addNewFeedback(
            @PathVariable String userId,
            @PathVariable String productId,
            @Valid @RequestBody FeedBackRequest feedBackRequest,
            BindingResult bindingResult) {

        Map<String, Object> response = new LinkedHashMap<>();

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new LinkedHashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        var feedbackResponse = feedBackService.save(userId, productId, feedBackRequest);

        if (feedbackResponse.isEmpty()) {
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("data", "Unable to save feedback. User or Product might not exist.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", feedbackResponse.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getFeedbackByProduct(@PathVariable String productId) {
        Map<String, Object> response = new LinkedHashMap<>();
        var feedbackList = feedBackService.findByProduct(productId);

        if (feedbackList.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "No feedback found for the specified product.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", feedbackList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbackByUser(@PathVariable String userId) {
        Map<String, Object> response = new LinkedHashMap<>();
        var feedbackResponse = feedBackService.findByUser(userId);

        if (feedbackResponse.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "No feedback found for the specified user.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", feedbackResponse.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getFeedbackByOrder(@PathVariable String orderId) {
        Map<String, Object> response = new LinkedHashMap<>();
        var feedbackResponse = feedBackService.findByOrder(orderId);

        if (feedbackResponse.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("data", "No feedback found for the specified order.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("status", HttpStatus.OK.value());
        response.put("data", feedbackResponse.get());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
