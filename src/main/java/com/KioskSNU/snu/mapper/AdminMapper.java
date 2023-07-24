package com.KioskSNU.snu.mapper;

import com.KioskSNU.snu.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    int insert(AdminDTO adminDTO);
    int update(AdminDTO adminDTO);
    int delete(AdminDTO adminDTO);
    AdminDTO getById(int id);
    AdminDTO getByUsername(String username);
    List<AdminDTO> getAll();
}
