package org.example.salesservice.service.impl;

import org.example.salesservice.dto.UserDTO;
import org.example.salesservice.model.Sale;
import org.example.salesservice.repository.IUserAPI;
import org.example.salesservice.repository.SaleRepository;
import org.example.salesservice.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private IUserAPI userAPI;
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale createSale(Long id_user) {

        Sale sale = new Sale();
        UserDTO user = userAPI.getUserById(id_user);

        sale.setSaleDate(LocalDate.now());
        sale.setUser(user);

        Sale savedSale = saleRepository.save(sale);

        userAPI.cleanShoppingCart(id_user);
        return savedSale;
    }

    @Override
    public List<Sale> getAllSales() {

        return saleRepository.findAll();
    }

    @Override
    public Sale findSaleById(Long id_sale) {

        return saleRepository.findById(id_sale).orElse(null);
    }

    @Override
    public void deleteSale(Long id_sale) {

        Sale sale = saleRepository.findById(id_sale).orElse(null);

        sale.setUser(null);

        saleRepository.deleteById(id_sale);
    }
}
