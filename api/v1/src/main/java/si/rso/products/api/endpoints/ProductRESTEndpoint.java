package si.rso.products.api.endpoints;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.rso.products.lib.Product;
import si.rso.products.services.ProductService;
import si.rso.rest.common.HttpHeaders;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Log
@Path("/products")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductRESTEndpoint {

    @Inject
    private ProductService productService;
    
    @Context
    protected UriInfo uriInfo;
    
    @GET
    @Operation(description = "Get products via REST.",
        summary = "Get products via REST.", tags = "product",
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns a product list.",
                content = @Content(array = @ArraySchema(schema =@Schema(implementation = Product.class))))
        })
    public Response getProducts() {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Product> products = productService.queryProducts(queryParameters);
        long productsCount = productService.queryProductsCount(queryParameters);
        return Response.ok(products).header(HttpHeaders.X_TOTAL_COUNT, productsCount).build();
    }
    
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
