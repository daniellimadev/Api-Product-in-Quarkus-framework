package com.guithub.daniellimadev.controller;

import com.guithub.daniellimadev.dto.ProductDTO;
import com.guithub.daniellimadev.service.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAllProducts() {
        return productService.getAllProduct();
    }

    @POST
    @Transactional
    public Response savaProduct(ProductDTO product) {
       try {
           productService.createNewProduct(product);
           return Response.ok().build();
       } catch (Exception e) {
           e.printStackTrace();
           return Response.serverError().build();
       }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduct(@PathParam("id") Long id, ProductDTO product) {
        try {
            productService.updateProduct(id,product);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProductById(@PathParam("id") Long id) {
        try {
            productService.deleteProductById(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
