package com.taiquan.controller;

import com.taiquan.domain.customer.Company;
import com.taiquan.service.AUDCustomerService;
import com.taiquan.service.QueryCustomerService;
import com.taiquan.service.SupplyService;
import com.taiquan.utils.AddressUtils;
import com.taiquan.utils.Corderinate;
import com.taiquan.utils.JSONUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.taiquan.utils.PrintUtil.println;

@Controller
@RequestMapping("/validate")
public class ValidateController {
    @Autowired
    private AUDCustomerService customerService;
    @Autowired
    private QueryCustomerService queryCustomerService;
    @Autowired
    private SupplyService supplyService;
    @RequestMapping("/address")
    public ResponseEntity<Boolean> validAddress(@RequestBody String addressString){
        Corderinate  co = AddressUtils.getGeocoderAngel(addressString);
        if (co != null){
            return new ResponseEntity(true,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(false,HttpStatus.BAD_GATEWAY);
        }
    }
    @RequestMapping("companyName")
    public ResponseEntity<Boolean> validateCompanyName(@RequestBody String name){
        boolean b = queryCustomerService.companyIsExisted(name);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @RequestMapping("supplierName")
    public ResponseEntity<Boolean> validateSupplierName(@RequestBody String name){
        boolean b =supplyService.supplierIsExisted(JSONUtils.removeQuot(name));
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
    @RequestMapping("simpleName")
    public ResponseEntity<Boolean> validateSupplierSimpleName(@RequestBody String name){
        boolean b = supplyService.supplierSimpleIsExisted(JSONUtils.removeQuot(name));
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
