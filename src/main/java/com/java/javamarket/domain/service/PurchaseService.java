package com.java.javamarket.domain.service;


import com.java.javamarket.domain.Purchase;
import com.java.javamarket.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    /**
     * {@inheritDoc}
     */
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }
    public Optional<List<Purchase>> getByClient( String clientId){
        return purchaseRepository.getByClient(clientId);
    }

    /**
     *
     * @param purchase
     * @return un objeto llamado @code{{@link Purchase}}
     */
    public Purchase save(Purchase purchase){
        return purchaseRepository.save(purchase);
    }
}
