package si.rso.products.api.endpoints;

import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRESTEndpoint {

    @Inject
    private ProductService productService;

//    @Context
//    protected UriInfo uriInfo;

    @GET
//    @Operation(description = "Retrieves accounts that match query.",
//            summary = "Returns account infos.", tags = "account",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Returns accounts.",
//                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
//            })
    public Response getProducts() {
        List<Product> products = productService.getProducts();

        return Response.ok(products).build();
    }
}
