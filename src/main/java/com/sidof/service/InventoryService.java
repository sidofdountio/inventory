package com.sidof.service;

import com.sidof.model.Inventory;
import com.sidof.repo.InventoryRepo;
import com.sidof.service.interfaceService.InventoryDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author sidof
 * @Since 20/05/2023
 * @Version v1.0
 */
@Service @Transactional @Slf4j @RequiredArgsConstructor
public class InventoryService implements InventoryDao {
    private final InventoryRepo inventoryRepo;
    @Override
    public Inventory addInventory(Inventory inventoryToSave) {
        return inventoryRepo.save(inventoryToSave);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    @Override
    public Inventory INVENTORY(Long inventoryId) {
        final boolean existsById = inventoryRepo.existsById(inventoryId);
        if (!existsById){
            throw  new IllegalStateException("Invotory with this id "+inventoryId+" exist");
        }
        return inventoryRepo.findById(inventoryId).get();
    }

    @Override
    public Optional<Inventory> INVENTORY_OPTIONAL(String productName, boolean isUp) {
        Optional<Inventory> optionalInventory = inventoryRepo.findByProductNameAndIsUp(productName,isUp);
        if(!optionalInventory.isPresent()){
            log.info("No up inventory with this {}",productName);
            throw new IllegalStateException("No up inventory with this "+productName);
        }
        log.info("Fecthing optional up inventory {}",productName);
        return optionalInventory;
    }

    @Override
    public List<Inventory> INVENTORIES() {
        log.info("Fetching Inventory ...");
        return inventoryRepo.findAll();
    }
}
