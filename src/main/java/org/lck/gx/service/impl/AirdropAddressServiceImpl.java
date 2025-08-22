package org.lck.gx.service.impl;

import org.lck.gx.entity.AirdropAddress;
import org.lck.gx.repo.AirdropAddressRepository;
import org.lck.gx.service.AirdropAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirdropAddressServiceImpl implements AirdropAddressService {

    @Autowired
    private AirdropAddressRepository airdropAddressRepository;

    @Override
    public List<AirdropAddress> getAllAddressInfo() {
        return airdropAddressRepository.findAll();

    }

}
