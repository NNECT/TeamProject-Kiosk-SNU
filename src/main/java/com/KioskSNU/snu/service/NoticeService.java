package com.KioskSNU.snu.service;

import com.KioskSNU.snu.dto.NoticeDTO;

import java.util.List;

public interface NoticeService {
    NoticeDTO insert(NoticeDTO noticeDTO);
    NoticeDTO update(NoticeDTO noticeDTO);
    boolean delete(NoticeDTO noticeDTO);
    NoticeDTO getById(int id);
    List<NoticeDTO> getAll();
    List<NoticeDTO> getAllByOutside(boolean outside);
    List<NoticeDTO> getAllByActive(boolean active);
}
