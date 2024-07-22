package org.example.salesservice.service;

import org.example.salesservice.model.Sale;

import java.util.List;

public interface ISalesService {

    Sale createSale(Long id_user);
    List<Sale> getAllSales();
    Sale findSaleById(Long id_sale);
    void deleteSale(Long id_sale);

}
