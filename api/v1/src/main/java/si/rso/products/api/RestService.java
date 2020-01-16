package si.rso.products.api;

import com.kumuluz.ee.discovery.annotations.RegisterService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import si.rso.products.api.config.AuthRole;
import si.rso.products.api.endpoints.CalcEndpoint;
import si.rso.products.api.endpoints.ProductRESTEndpoint;
import si.rso.products.api.endpoints.ProductRESTSecureEndpoint;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
@RegisterService
@DeclareRoles({AuthRole.SERVICE, AuthRole.ADMIN, AuthRole.SELLER, AuthRole.CUSTOMER})
@OpenAPIDefinition(
        info = @Info(title = "Products service", version = "1.0.0", contact = @Contact(name = "Matej Bizjak"),
                description = "Service for working with product entity.")
)
public class RestService extends ResourceConfig {

    public RestService() {
        // register jersey's multipart feature - required for file upload
        register(MultiPartFeature.class);
        // other
        register(AuthRole.class);
        register(ProductRESTSecureEndpoint.class);
        register(ProductRESTEndpoint.class);
        register(CalcEndpoint.class);
    }
}
