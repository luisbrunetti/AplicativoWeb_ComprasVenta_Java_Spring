package com.java.javamarket.persistence;

import com.java.javamarket.domain.Product;
import com.java.javamarket.domain.repository.ProductRepository;
import com.java.javamarket.persistence.MAPPER.ProductMapper;
import com.java.javamarket.persistence.crud.ProductoCrudRepository;
import com.java.javamarket.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Implementar de {@link ProductRepository}
 * @see ProductRepository
 */
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }
/*
    public List<Producto> getByCategoria(int idCategoria){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria)
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }
//
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

*/
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    /**
     * {@inheritDoc}
     * Este metodo borra un product o
     *
     * @apiNote 33dsfsdfsdfsdf
     * @param idProducto
     * @return no deuvelve nada
     * @{code} descripcion de pruieba
     *
     */
    @Override
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
