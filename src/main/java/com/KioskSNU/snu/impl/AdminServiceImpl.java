package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.AdminDTO;
import com.KioskSNU.snu.mapper.AdminMapper;
import com.KioskSNU.snu.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Qualifier("adminDAO")
    private final AdminMapper adminDAO;

    @Override
    public int insert(AdminDTO adminDTO) {
        return adminDAO.insert(adminDTO);
    }

    @Override
    public int update(AdminDTO adminDTO) {
        return adminDAO.update(adminDTO);
    }

    @Override
    public int delete(AdminDTO adminDTO) {
        return adminDAO.delete(adminDTO);
    }

    @Override
    public AdminDTO getById(int id) {
        return adminDAO.getById(id);
    }

    @Override
    public AdminDTO getByUsername(String username) {
        return adminDAO.getByUsername(username);
    }

    @Override
    public List<AdminDTO> getAll() {
        return adminDAO.getAll();
    }
}
