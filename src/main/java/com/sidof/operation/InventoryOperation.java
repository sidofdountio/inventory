package com.sidof.operation;

import com.sidof.model.Inventory;
import com.sidof.model.Product;
import com.sidof.model.Purchase;
import com.sidof.model.Sale;
import com.sidof.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

/**
 * @Author sidof
 * @Since 17/05/2023
 * @Version v1.0
 */
@Transactional
@Service
public class InventoryOperation {

    private final ProductService productService;

    public InventoryOperation(ProductService productService) {
        this.productService = productService;
    }

    public Inventory cmupForPurchase(String label, Purchase purchase, List<Inventory> inventoryList) {
        System.out.println("CHECK BEFORE SAVE  " + purchase);
        final Product productById = productService.getProductById(purchase.getProduct().getId());
        final LocalDate date = now();
        final int quantity = purchase.getQuantity();
        final double price = purchase.getPrice();
        final double amount = quantity * price;

        final int newQuantity = purchase.getQuantity();
        final double newPrice = purchase.getPrice();
        final double newAmount = purchase.getAmount();

        final String productName = productById.getName();
        if (inventoryList.isEmpty()) {
            Inventory inventoryToAdd = new Inventory(
                    1L, date, label, productName, true,
                    quantity,
                    price, amount,
                    quantity,
                    newPrice,
                    newAmount
            );
            return inventoryToAdd;
        }
        //Purchase value.
        Inventory inventoryToAdd = new Inventory();
//        Firstly set orld values.
        inventoryToAdd.setLabel(label);
        inventoryToAdd.setDate(date);
        inventoryToAdd.setOrldQuantity(quantity);
        inventoryToAdd.setOrldPrice(price);
        inventoryToAdd.setOldAmount(amount);
        inventoryToAdd.setUp(true);
        inventoryToAdd.setProductName(productName);
        //SOME OPERATION (CMUP).
        int qty = 0;
        double amt = 0;
        for (Inventory inventoryToUpdate : inventoryList) {
            final Long productId = purchase.getProduct().getId();
            if (productService.existProduct(productId)) {
                if (inventoryToUpdate.isUp() && productName.equalsIgnoreCase(inventoryToUpdate.getProductName())) {
                    qty = inventoryToUpdate.getNewQuantity() + quantity;
                    amt = inventoryToUpdate.getNewAmount() + amount;
                    inventoryToUpdate.setUp(false);
                }
                //If the quantity is less than 0 we use the purchase price.
                if (qty == 0) {
                    inventoryToAdd.setNewPrice(price);
                    inventoryToAdd.setNewAmount(amt);
                }

                inventoryToAdd.setNewQuantity(qty);
                final double newPriceToAdd = amt / qty;
                inventoryToAdd.setNewPrice(newPriceToAdd);
                inventoryToAdd.setNewAmount(amt);
//                return inventoryToAdd;
            } else {
                inventoryToAdd.setNewQuantity(quantity);
                inventoryToAdd.setNewPrice(price);
                inventoryToAdd.setNewAmount(amount);
//                return inventoryToAdd;
            }
        }
        return inventoryToAdd;
    }

    public Inventory cmupForSale(String label, Sale sale, List<Inventory> inventoryList) {

        final int quantity = sale.getQuantity();
        double orldAmt = 0;
        double newPreviousPrice = 0;
        int newPreviousQty = 0;
        int newQtyToadd = 0;
        double newPreviousAmount = 0;
        double newAmountToAdd = 0;

        final String productName = sale.getProduct().getName();
        for (Inventory inventoryToUpdate : inventoryList) {
            if (inventoryToUpdate.isUp() && productName.equalsIgnoreCase(inventoryToUpdate.getProductName())) {
                newPreviousPrice = inventoryToUpdate.getNewPrice();
                newPreviousQty = inventoryToUpdate.getNewQuantity();
                newPreviousAmount = inventoryToUpdate.getNewAmount();
                inventoryToUpdate.setUp(false);
                //TODO: to update from service.
//                inventoryService.updateInventory(inventoryToUpdate);
                if (newPreviousQty == 0) {
                    newQtyToadd = quantity;
                }
            }
        }
        newQtyToadd = newPreviousQty - quantity;
        // Amount of saling.
        orldAmt = quantity * newPreviousPrice;
        //TODO: Before process to this operation, on frondEnd we will firstly check if the remaining quntity it's not equant to 0.
        newAmountToAdd = newPreviousAmount - orldAmt;
        Inventory inventoryToSale = new Inventory(
                1L, now(), label, productName, true, quantity, newPreviousPrice, orldAmt, newQtyToadd, newPreviousPrice, newAmountToAdd
        );
        return inventoryToSale;

    }
}
