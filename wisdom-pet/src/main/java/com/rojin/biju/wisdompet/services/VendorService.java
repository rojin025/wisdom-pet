package com.rojin.biju.wisdompet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rojin.biju.wisdompet.data.entities.VendorEntity;
import com.rojin.biju.wisdompet.data.repositories.VendorRepository;
import com.rojin.biju.wisdompet.web.errors.NotFoundException;
import com.rojin.biju.wisdompet.web.models.Vendor;

@Service
public class VendorService {
    public final VendorRepository vendorRepository;
    
    public VendorService(VendorRepository vendorRepository){
        this.vendorRepository = vendorRepository;
    }

    public List<Vendor> getVendors(){
        Iterable<VendorEntity> vendorEntities = this.vendorRepository.findAll();
        List<Vendor> vendors = new ArrayList<>();
        vendorEntities.forEach(vendorEntity->{
            vendors.add(this.translateDbToWeb(vendorEntity));
        });

        return vendors; 
    }

    public Vendor getVendor(long id){
        Optional<VendorEntity> optional =this.vendorRepository.findById(id);
        
        if(optional.isEmpty()){
            throw new NotFoundException("Vendor entity not found by id: " + id);
        }

        return this.translateDbToWeb(optional.get());
    }

    public Vendor createOrUpdateVendor(Vendor vendor){
        VendorEntity entity = this.translateWebToDb(vendor);
        entity = this.vendorRepository.save(entity);
        return this.translateDbToWeb(entity);
    }

    public  void deleteVendor(long id){
        this.vendorRepository.deleteById(id);
    }


    private Vendor translateDbToWeb(VendorEntity entity){
        return new Vendor(
            entity.getId(),
            entity.getName(),
            entity.getContact(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getAddress()
        );
    }

    private VendorEntity translateWebToDb(Vendor vendor){
        VendorEntity entity = new VendorEntity();

        entity.setId(vendor.getVendorId() == null? 0: vendor.getVendorId());
        entity.setName(vendor.getName());
        entity.setContact(vendor.getContact());
        entity.setPhone(vendor.getPhone());
        entity.setEmail(vendor.getEmail());
        entity.setAddress(vendor.getAddress());

        return entity;
    }
}
