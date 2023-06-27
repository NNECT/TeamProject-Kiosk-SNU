package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    AdminDTO insert(AdminDTO adminDTO);
    AdminDTO update(AdminDTO adminDTO);
    boolean delete(AdminDTO adminDTO);
    AdminDTO getById(int id);
    AdminDTO getByUsername(String username);
    List<AdminDTO> getAll();
}
