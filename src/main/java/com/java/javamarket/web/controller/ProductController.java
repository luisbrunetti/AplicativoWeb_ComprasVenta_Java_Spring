package com.java.javamarket.web.controller;

import com.java.javamarket.domain.Product;
import com.java.javamarket.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <h1>Controller Product</h1>
 * COntrolasadadsdor para la clase de prodcutos all , id , categoria
 * Con tiene metodos como {
 * @code getAll()
 * } que llama a todos los productos
 * @code getProduct(){} Llama a un producto en particular
 */
@RestController // garantiza que esta clase serae un controlodaro de una API REST
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Obtiene todos los productos 
     * @return
     */
    @GetMapping("/all")
    @ApiOperation("Get all SUpermarket products")
    @ApiResponse(code = 200, message = "Ok")
    //public List<Product> getAll(){ return productService.getAll();}
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    /**
     * Obtiene los elementos por ID producto
     * @param productId
     * @return
     */

    @GetMapping("/{productId}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message="Ok"),
            @ApiResponse(code = 404, message = "Product Not Found")
    })
    public ResponseEntity <Product> getProduct(
            @ApiParam(value = "The id of the product", required = true, example ="7")
            @PathVariable("productId") int productId){
        return productService.getProduct(productId).map(product ->
                new ResponseEntity<>(product, HttpStatus.OK))
                .orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity <List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Se guarda un nuevo elemento en la base de datos
     * @see Product
     * @code{ @link}
     * @param product
     * @return
     */
    @PostMapping("/save")//Cuerpo de petición
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService
        .save(product), HttpStatus.CREATED);
    }

    /**
     * Borra un elemento de la base de datos
     * @param productId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if (productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
