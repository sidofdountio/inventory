package com.sidof.service;

import com.sidof.model.Purchase;
import com.sidof.repo.PurchaseRepo;
import com.sidof.service.interfaceService.PurchaseDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PurchaseService implements PurchaseDao {
    private final PurchaseRepo purchaseRepo;
    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return purchaseRepo.save(purchase);
    }

    @Override
    public Purchase getPurchase(Long purchaseId) {
        return purchaseRepo.findById(purchaseId).get();
    }

    @Override
    public void deletePurchase(Long purchaseIdToDelete) {
        purchaseRepo.deleteById(purchaseIdToDelete);
    }

    @Override
    public List<Purchase> PURCHASES() {
        return purchaseRepo.findAll();
    }
}
