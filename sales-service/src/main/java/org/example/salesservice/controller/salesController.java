package org.example.salesservice.controller;

import org.example.salesservice.model.Sale;
import org.example.salesservice.repository.IUserAPI;
import org.example.salesservice.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class salesController {

    @Autowired
    private ISalesService salesService;
    @Autowired
    private IUserAPI userAPI;


    @PostMapping("/create/{id_user}")
    public ResponseEntity<Sale> createSale (@PathVariable Long id_user) {

        Sale sale = salesService.createSale(id_user);

        if (id_user == null || sale == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(sale);
        }
    }

    @GetMapping("/listOfSales")
    public ResponseEntity<List<Sale>> getSales() {

        List<Sale> sales = salesService.getAllSales();

        if (sales.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(sales);
        }
    }

    @GetMapping("/getSaleById/{id_sale}")
    public ResponseEntity<Sale> getSaleById (@PathVariable Long id_sale) {

        Sale sale = salesService.findSaleById(id_sale);

        if (sale == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(sale);
        }
    }

    @DeleteMapping("/deleteSaleById/{id_sale}")
    public ResponseEntity<Sale> deleteSale (@PathVariable Long id_sale) {

        Sale sale = salesService.findSaleById(id_sale);

        if (sale == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            salesService.deleteSale(id_sale);
            return ResponseEntity.status(HttpStatus.OK).body(sale);
        }
    }
}
