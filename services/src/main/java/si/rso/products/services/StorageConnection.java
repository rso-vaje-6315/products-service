package si.rso.products.services;

import com.google.cloud.storage.*;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class StorageConnection {

    private Storage storage;

    private String BUCKET_NAME;

    private String API_URL;

    @PostConstruct
    private void init() {
        this.storage = StorageOptions.getDefaultInstance().getService();
        this.BUCKET_NAME = ConfigurationUtil.getInstance()
                .get("google.storage.bucket-name")
                .orElseThrow(() -> new RuntimeException("No configuration for Cloud Storage!"));
        this.API_URL = ConfigurationUtil.getInstance()
                .get("google.storage.url")
                .orElseThrow(() -> new RuntimeException("No configuration for Cloud Storage!"));
    }

    public String uploadFile(String productId, byte[] imageBytes, String mimeType) throws IOException {
        String fileName = "image_" + productId;

        BlobId blobId = BlobId.of(this.BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(mimeType).build();
        // TODO test if files are overwriting
        Blob blob = this.storage.create(blobInfo, imageBytes);

        return String.format("%s/%s/%s", this.API_URL, this.BUCKET_NAME, fileName);
    }

}
