package com.KioskSNU.snu.impl;

import com.KioskSNU.snu.dto.NoticeDTO;
import com.KioskSNU.snu.mapper.NoticeMapper;
import com.KioskSNU.snu.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    @Qualifier("noticeDAO")
    private final NoticeMapper noticeDAO;

    @Override
    public int insert(NoticeDTO noticeDTO) {
        return noticeDAO.insert(noticeDTO);
    }

    @Override
    public int update(NoticeDTO noticeDTO) {
        return noticeDAO.update(noticeDTO);
    }

    @Override
    public int delete(NoticeDTO noticeDTO) {
        return noticeDAO.delete(noticeDTO);
    }

    @Override
    public NoticeDTO getById(int id) {
        return noticeDAO.getById(id);
    }

    @Override
    public List<NoticeDTO> getAll() {
        return noticeDAO.getAll();
    }

    @Override
    public List<NoticeDTO> getAllByOutside(boolean outside) {
        return noticeDAO.getAllByOutside(outside);
    }

    @Override
    public List<NoticeDTO> getAllByActive(boolean active) {
        return noticeDAO.getAllByActive(active);
    }
}
