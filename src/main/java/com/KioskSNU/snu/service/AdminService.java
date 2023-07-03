package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface AdminService {
    int insert(AdminDTO adminDTO);
    int update(AdminDTO adminDTO);
    int delete(AdminDTO adminDTO);
    AdminDTO getById(int id);
    AdminDTO getByUsername(String username);
    List<AdminDTO> getAll();
}
