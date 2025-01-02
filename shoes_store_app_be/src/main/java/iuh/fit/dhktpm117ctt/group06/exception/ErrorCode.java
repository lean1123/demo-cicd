package iuh.fit.dhktpm117ctt.group06.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    // User
    PASSWORD_INVALID(1002, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1003, "Email invalid", HttpStatus.BAD_REQUEST),
    FIRSTNAME_INVALID(1004, "First Name contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    LASTNAME_INVALID(1005, "Last Name contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    GENDER_INVALID(1006,"Gender invalid", HttpStatus.BAD_REQUEST),
    AVATAR_INVALID(1007, "Up load avatar failed", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1111, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1099, "User not found", HttpStatus.NOT_FOUND),
    USER_NOT_AUTHORIZED(1401, "User not authorized", HttpStatus.UNAUTHORIZED),
    // Address
    HOME_NUMBER_INVALID(2101, "Home number contains only letters and numbers, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    STREET_INVALID(2102, "Street contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    WARD_INVALID(2103, "Ward contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    DISTRICT_INVALID(2104, "District contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    CITY_INVALID(2105, "City contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    // Room
    ROOM_NAME_INVALID(3001, "Room name contains only letters and spaces, {min}-{max} characters long", HttpStatus.BAD_REQUEST),
    
    //Product
    PRODUCT_NOT_FOUND(4404, "Product not found!", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED(4405, "Product existed!", HttpStatus.BAD_REQUEST),
    PRODUCT_INVALID(4406, "Product invalid!", HttpStatus.BAD_REQUEST),
    
    //ProductItem
    PRODUCT_ITEM_NOT_FOUND(4504, "Product item not found!", HttpStatus.NOT_FOUND),
    PRODUCT_ITEM_EXISTED(4505, "Product item existed!", HttpStatus.BAD_REQUEST),
    PRODUCT_ITEM_INVALID(4506, "Product item invalid!", HttpStatus.BAD_REQUEST),
    PRODUCT_ITEM_EXISTED_IN_ORDER_DETAILS(4507, "Product item existed in order details!", HttpStatus.BAD_REQUEST),
    PRODUCT_ITEM_NOT_ENOUGH(4508, "Product item not enough!", HttpStatus.BAD_REQUEST),
    QTY_INVALID(4509, "Product item quantity invalid!", HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_NOT_FOUND(4510, "Order detail not found!", HttpStatus.NOT_FOUND),
    
    ADDRESS_NOT_FOUND(4604, "Address not found!", HttpStatus.NOT_FOUND),
    ;
	

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
