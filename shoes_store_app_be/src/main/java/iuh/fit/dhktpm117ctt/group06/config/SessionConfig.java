//package iuh.fit.dhktpm117ctt.group06.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import iuh.fit.dhktpm117ctt.group06.entities.CartDetailPK;
//import org.springframework.beans.factory.BeanClassLoaderAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.support.GenericConversionService;
//import org.springframework.core.serializer.Deserializer;
//import org.springframework.core.serializer.Serializer;
//import org.springframework.core.serializer.support.DeserializingConverter;
//import org.springframework.core.serializer.support.SerializingConverter;
//import org.springframework.lang.NonNull;
//import org.springframework.security.jackson2.SecurityJackson2Modules;
//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//@Configuration
//@EnableJdbcHttpSession
//public class SessionConfig implements BeanClassLoaderAware {
//
//    private ClassLoader classLoader;
//
//    @Bean("springSessionConversionService")
//    public GenericConversionService springSessionConversionService(ObjectMapper objectMapper) {
//        ObjectMapper copy = objectMapper.copy();
//        // Đăng ký module của Spring Security
//        copy.registerModules(SecurityJackson2Modules.getModules(this.classLoader));
//
//        // Tạo dịch vụ chuyển đổi
//        GenericConversionService converter = new GenericConversionService();
//
//        // Chuyển đổi CartDetailPK sang byte[]
//        converter.addConverter(CartDetailPK.class, byte[].class, new SerializingConverter(new JsonSerializer(copy)));
//
//        // Chuyển đổi từ byte[] sang Object (bao gồm cả CartDetailPK)
//        converter.addConverter(byte[].class, Object.class, new DeserializingConverter(new JsonDeserializer(copy)));
//
//        // Chuyển đổi từ Object sang byte[]
//        converter.addConverter(Object.class, byte[].class, new SerializingConverter(new JsonSerializer(copy)));
//
//        return converter;
//    }
//
//    @Override
//    public void setBeanClassLoader(@NonNull ClassLoader classLoader) {
//        this.classLoader = classLoader;
//    }
//
//    /**
//     * Serializer sử dụng ObjectMapper
//     */
//    static class JsonSerializer implements Serializer<Object> {
//
//        private final ObjectMapper objectMapper;
//
//        JsonSerializer(ObjectMapper objectMapper) {
//            this.objectMapper = objectMapper;
//        }
//
//        @Override
//        public void serialize(@NonNull Object object, @NonNull OutputStream outputStream) throws IOException {
//            // Loại bỏ các trường không cần thiết nếu là CartDetailPK
//            if (object instanceof CartDetailPK) {
//                CartDetailPK cartDetailPK = (CartDetailPK) object;
//                objectMapper.writeValue(outputStream, new (cartDetailPK));
//            } else {
//                objectMapper.writeValue(outputStream, object);
//            }
//        }
//    }
//
//
//    static class JsonDeserializer implements Deserializer<Object> {
//
//        private final ObjectMapper objectMapper;
//
//        JsonDeserializer(ObjectMapper objectMapper) {
//            this.objectMapper = objectMapper;
//        }
//
//        @Override
//        @NonNull
//        public Object deserialize(@NonNull InputStream inputStream) throws IOException {
//            return objectMapper.readValue(inputStream, Object.class);
//        }
//
//    }
//
//}
//
