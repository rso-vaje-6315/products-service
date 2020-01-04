package si.rso.products.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRESTEndpoint {

    @Inject
    private ProductService productService;

    @GET
    @Path("/{productId}")
    @Operation(description = "Get product's details via REST.",
            summary = "Get product's details via REST.", tags = "product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Returns an product.",
                            content = @Content(schema = @Schema(implementation = Product.class)))
            })
    public Response getProduct(@PathParam("productId") String productId) {
        return Response.ok(productService.getProduct(productId)).build();
    }
}
