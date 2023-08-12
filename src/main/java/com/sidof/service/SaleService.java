package com.sidof.service;

import com.sidof.model.Sale;
import com.sidof.repo.SaleRepo;
import com.sidof.service.interfaceService.SaleDao;
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
public class SaleService implements SaleDao {
    private final SaleRepo saleRepo;

    @Override
    public Sale addSale(Sale sale) {
        return saleRepo.save(sale);
    }

    @Override
    public Sale updateSale(Sale sale) {
        return saleRepo.save(sale);
    }

    @Override
    public Sale getSale(Long saleId) {
        return saleRepo.findById(saleId).get();
    }

    @Override
    public void deleteSale(Long saleIdToDelete) {
        saleRepo.deleteById(saleIdToDelete);
    }

    @Override
    public List<Sale> SALES() {
        return saleRepo.findAll();
    }
}
