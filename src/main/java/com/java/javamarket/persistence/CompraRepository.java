package com.java.javamarket.persistence;


import com.java.javamarket.domain.Purchase;
import com.java.javamarket.domain.repository.PurchaseRepository;
import com.java.javamarket.persistence.MAPPER.PurchaseMapper;
import com.java.javamarket.persistence.crud.CompraCrudRepository;
import com.java.javamarket.persistence.entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId).map(compras->
                mapper.toPurchases(compras));
    }

    /**
     * <h1>Guardar un producto </h1>
     * @param purchase
     * @return
     */
    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        //Guardar en casacada
        // Estar seguros de que
        System.out.println("#######################"+ compra.getProductos());
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));

    }
}
