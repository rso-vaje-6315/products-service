package si.rso.products.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.security.annotations.Secure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.rso.products.api.config.AuthRole;
import si.rso.products.services.ProductService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Log
@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Secure
public class ProductRESTEndpoint {

    @Inject
    private ProductService productService;

    @PUT
    @Timed(name = "image-upload-time")
    @Path("/image/{productId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({AuthRole.ADMIN, AuthRole.SELLER})
    @Operation(description = "Updates products' image.",
            summary = "Updates products' image.", tags = "product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Updates image.")
            })
    public Response updateImage(@PathParam("productId") String productId, @FormDataParam("file") FormDataBodyPart body) {
        try {
//            ContentDisposition fileMetadata = body.getContentDisposition();
            byte[] imageBytes = body.getEntityAs(InputStream.class).readAllBytes();
            String mimeType = body.getMediaType().toString();
            productService.updateImage(productId, imageBytes, mimeType);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
