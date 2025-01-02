package iuh.fit.dhktpm117ctt.group06.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryProvider {
    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile file, String folder, String fileName) throws IOException {
        Map uploadParams = ObjectUtils.asMap(
                "async", "auto",
                "folder", "ShoesShopApp/"+folder,
                "public_id", fileName
        );
        return cloudinary.uploader().upload(file.getBytes(), uploadParams);
    }

    public Map delete(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
    public List<Map> uploadFiles(MultipartFile[] files, String folder, String fileName) throws IOException {
        List<Map> uploadResults = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
            String uniqueFileName = fileName + "_" + uniqueSuffix;
            Map uploadParams = ObjectUtils.asMap(
                    "folder", "ShoesShopApp/" + folder,
                    "public_id", uniqueFileName
            );
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
                uploadResults.add(uploadResult);
                System.out.println("Uploaded URL: " + uploadResult.get("url"));
            } catch (Exception e) {
                System.err.println("Error uploading file: " + e.getMessage());
            }
        }
        return uploadResults;
    }

}
