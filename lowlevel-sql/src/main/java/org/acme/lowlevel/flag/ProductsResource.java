package org.acme.lowlevel.flag;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductsResource {





    //@PUT
    //@Path("{productId}")
    //@Transactional
    //public Product update(@PathParam("productId") Long productId, @Valid Product product) {
    //    Product existing = (Product) Product.<Product>findById(productId);
    //    if (existing == null) {
    //        throw new NotFoundException();
    //    } else {
    //        existing.name = product.name;
    //        existing.description = product.description;
    //        existing.price = product.price;
    //        existing.persistAndFlush();
    //        return existing;
    //    }
    //}


    @Inject
    Template catalogue;

    @Inject
    Template details;

    @GET
    public TemplateInstance products() {
        return catalogue.data("products", ProductDTO.all());
    }

    @GET
    @Path("{productId}")
    public TemplateInstance details(@PathParam("productId") Long productId) {
        Product product = ProductDTO.getById(productId);
        if(product != null) {
            return details.data("product", product);
        } else {
            // Let RESTEasy handle it for us. Of course, alternatively we could also render a custom 404 page.
            throw new NotFoundException("Product not found!");
        }
    }
}
