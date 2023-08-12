package com.sidof.service.interfaceService;

import com.sidof.model.Sale;

import java.util.List;

/**
 * @Author sidof
 * @Since 20/05/2023
 */
public interface SaleDao {
    Sale addSale(Sale sale);
    Sale updateSale(Sale sale);
    Sale getSale(Long saleId);
    void deleteSale(Long saleIdToDelete);
    List<Sale>SALES();
}
