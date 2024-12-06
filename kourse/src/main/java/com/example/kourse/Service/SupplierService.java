package com.example.kourse.Service;

import com.example.kourse.Models.Supplier;
import com.example.kourse.Repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Метод для получения всех поставщиков
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // Метод для добавления нового поставщика
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Метод для обновления данных поставщика
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
        if (existingSupplier.isPresent()) {
            Supplier supplier = existingSupplier.get();
            supplier.setName(updatedSupplier.getName());
            supplier.setContactInfo(updatedSupplier.getContactInfo());
            supplier.setAddress(updatedSupplier.getAddress());
            return supplierRepository.save(supplier);
        } else {
            throw new RuntimeException("Supplier with ID " + id + " not found.");
        }
    }

    // Метод для удаления поставщика
    public void deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("Supplier with ID " + id + " not found.");
        }
    }
}
