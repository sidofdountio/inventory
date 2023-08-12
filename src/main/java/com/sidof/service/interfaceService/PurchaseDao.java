package com.sidof.service.interfaceService;

import com.sidof.model.Purchase;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface PurchaseDao {
    Purchase addPurchase(Purchase purchase);
    Purchase updatePurchase(Purchase purchase);
    Purchase getPurchase(Long purchaseId);
    void deletePurchase(Long purchaseIdToDelete);
    List<Purchase>PURCHASES();
}
