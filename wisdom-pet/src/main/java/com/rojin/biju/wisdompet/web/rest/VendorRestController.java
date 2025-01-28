package com.rojin.biju.wisdompet.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.rojin.biju.wisdompet.services.VendorService;
import com.rojin.biju.wisdompet.web.errors.BadRequestException;
import com.rojin.biju.wisdompet.web.models.Vendor;

import java.util.List;





@RestController
@RequestMapping("/api/vendors")
public class VendorRestController {

    private final VendorService vendorService;

    public VendorRestController(VendorService vendorService){
        this.vendorService = vendorService;
    }

    @GetMapping
    public List<Vendor> getVendors() {
        return this.vendorService.getVendors();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return this.vendorService.createOrUpdateVendor(vendor);
    }
    
    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable("id")  long id) {
        return this.vendorService.getVendor(id);
    }
    
    @PutMapping("/{id}")
    public Vendor updatVendor(@PathVariable long id, @RequestBody Vendor vendor) {
        if(id != vendor.getVendorId()){
            throw new BadRequestException("Incoming Id doesnot match "+ id);
        }
        
        return this.vendorService.createOrUpdateVendor(vendor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteVendor(@PathVariable("id") long id){
        this.vendorService.deleteVendor(id);
    }
}
