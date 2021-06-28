package com.challenge.longlife.service;

import com.challenge.longlife.domain.model.Category;
import com.challenge.longlife.domain.model.Supplier;
import com.challenge.longlife.domain.repository.CategoryRepository;
import com.challenge.longlife.domain.repository.ProductRepository;
import com.challenge.longlife.domain.repository.SupplierRepository;
import com.challenge.longlife.domain.service.SupplierService;
import com.challenge.longlife.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class SupplierServiceImpl implements SupplierService {


    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Supplier> getAllSuppliers(Pageable pageable) {
        return this.supplierRepository.findAll(pageable);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(
                () -> new ResourceNotFoundException("Supplier", "Id", supplierId)
        );
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
            return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long supplierId, Supplier supplierRequest) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "Id", supplierId));
        return supplierRepository.save(
                supplier.setDescription(supplierRequest.getDescription())
                        .setEmail(supplierRequest.getEmail())
                        .setNumber(supplierRequest.getNumber())
        );
    }

    @Override
    public ResponseEntity<?> deleteSupplier(Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "Id", supplierId));
        supplierRepository.delete(supplier);
        return ResponseEntity.ok().build();
    }
}
