package com.sidof.operation;

import com.sidof.model.Inventory;
import com.sidof.model.Product;
import com.sidof.model.Purchase;
import com.sidof.model.Sale;
import com.sidof.service.InventoryService;
import com.sidof.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */
@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryOperation {

    private final ProductService productService;
    private final InventoryService inventoryService;

    public Inventory cmupForPurchase(String label, Purchase purchase, List<Inventory> inventoryList) {
        log.info("CHECK BEFORE SAVE  {}", purchase);
        final Product productById = productService.getProductById(purchase.getProduct().getId());

        final int quantity = purchase.getQuantity();
        final double price = purchase.getPrice();
        final double amount = quantity * price;

        final int newQuantity = purchase.getQuantity();
        final double newPrice = purchase.getPrice();
        final double newAmount = purchase.getAmount();

        Inventory inventoryToPucharse = new Inventory();

        final String productName = productById.getName();
        if (inventoryList.isEmpty()) {
            Inventory inventoryNew = new Inventory(
                    1L, LocalDateTime.now(), label, productName, true,
                    quantity,
                    price, amount,
                    quantity,
                    newPrice,
                    newAmount
            );
            //SOME OPERATION (CMUP) Cout Moyen Unitaire Pondere.
            inventoryToPucharse = inventoryNew;
        }
        int qty = 0;
        double amt = 0;
//        We check up product on our stock.
        final Long productId = purchase.getProduct().getId();
        if (!productService.existProduct(productId)) {
            log.error("The selected product was not found {}",productId);
            throw new IllegalStateException("error");
        }

        for (Inventory inventoryToUpdate : inventoryList) {
            if (inventoryToUpdate.isUp()
                    && productName.equalsIgnoreCase(inventoryToUpdate.getProductName())) {

                qty = inventoryToUpdate.getNewQuantity() + quantity; //calcul de la nouvel quatite.
                amt = inventoryToUpdate.getNewAmount() + amount; // calcul du nouveau montant.
                final double newPriceToAdd = amt / qty;
                Inventory inventoryToAdd = new Inventory();
//        Firstly set orld values.
                inventoryToAdd.setLabel(label);
                inventoryToAdd.setProductName(productName);
                inventoryToAdd.setDate(LocalDateTime.now());

                inventoryToAdd.setOrldQuantity(quantity);
                inventoryToAdd.setOrldPrice(price);
                inventoryToAdd.setOldAmount(amount);

                inventoryToAdd.setNewQuantity(qty);
                inventoryToAdd.setNewPrice(newPriceToAdd);
                inventoryToAdd.setNewAmount(amt);
                inventoryToAdd.setUp(true);
                inventoryToPucharse = inventoryToAdd;

                inventoryToUpdate.setUp(false);

                productById.setPrice(newPriceToAdd);
                productService.updateProduct(productById);

            }
           else{
                Inventory inventoryNew = new Inventory(
                        null, LocalDateTime.now(), label, productName, true,
                        quantity,
                        price,
                        amount,
                        quantity,
                        newPrice,
                        newAmount
                );
                inventoryToPucharse = inventoryNew;
                productById.setPrice(newPrice);
                productService.updateProduct(productById);
            }
        }
        return inventoryToPucharse;
    }

    public List<Inventory> cmupForSale(String label, List<Sale> sales, List<Inventory> inventoryList) {

        Inventory inventoryToAdd = new Inventory();
        List<Inventory> inventoryToCpuSale = new ArrayList<>();
//      Default values.
        final LocalDate date = now();

//      Last calculed quantity in our stock.
        int newQuantity = 0;
//      Get last pucharse price.
        double newPrice = 0;
        double oldPrice = 0;
        double newAmount = 0;
        double oldAmount = 0;

//      SOME OPERATION (CMUP) Cout Moyen Unitaire Pondere.
        int quantity = 0;
        double amount = 0;
//      Get the product name on product passed by user.
//      Quantity provider by user.
        int quantityProvideByUser = 0;
        Long productId = 0L;
        String productName = null;


//      Ensure that with have alredy purchase.
        if (inventoryList.isEmpty()) {
            throw new IllegalStateException("We can't perform this action. First purchase this product");
        }

//      Set Sale properties.
        for (Sale sale : sales) {
            quantityProvideByUser = sale.getQuantity();
            productId = sale.getProduct().getId();
            productName = sale.getProduct().getName();

            for (Inventory stockInventoty : inventoryList) {
//            stock product.
                if (stockInventoty.getNewQuantity() < quantityProvideByUser && stockInventoty.isUp()) {
                    throw new IllegalStateException("Quantity in store is less thant provider");
                }

                if (productName.equalsIgnoreCase(stockInventoty.getProductName()) && stockInventoty.isUp()) {
                    //            we manage only product that is up.
                    oldPrice = stockInventoty.getOrldPrice();
                    newPrice = stockInventoty.getNewPrice();//ancien prix calcule apres achat.
                    oldAmount = quantityProvideByUser * newPrice; // prix calcule

                    newQuantity = stockInventoty.getNewQuantity();
                    newAmount = stockInventoty.getNewAmount() - oldAmount;
                    quantity = newQuantity - quantityProvideByUser;

                    inventoryToAdd.setLabel(label);
                    inventoryToAdd.setOrldQuantity(quantityProvideByUser);
                    inventoryToAdd.setOrldPrice(newPrice);// ancien nouveau prix calcule apre achat.
                    inventoryToAdd.setOldAmount(oldAmount);

                    inventoryToAdd.setNewQuantity(quantity);
                    inventoryToAdd.setNewPrice(newPrice);
                    inventoryToAdd.setNewAmount(newAmount);
                    inventoryToAdd.setProductName(productName);
                    inventoryToAdd.setDate(LocalDateTime.now());
                    inventoryToAdd.setUp(true);
//
                    stockInventoty.setUp(false);
//                    Update change of inventory.
                    inventoryService.updateInventory(stockInventoty);

                    inventoryToCpuSale.add(inventoryToAdd);
                    log.info("inventory {}", stockInventoty);
//                log.error("You can't perform this action. please check thr product.")
                }
            }

        }
        return inventoryToCpuSale;

    }
}
