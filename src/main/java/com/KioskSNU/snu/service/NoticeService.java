package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeService {
    int insert(NoticeDTO noticeDTO);
    int update(NoticeDTO noticeDTO);
    int delete(NoticeDTO noticeDTO);
    NoticeDTO getById(int id);
    List<NoticeDTO> getAll();
    List<NoticeDTO> getAllByOutside(boolean outside);
    List<NoticeDTO> getAllByActive(boolean active);
}
