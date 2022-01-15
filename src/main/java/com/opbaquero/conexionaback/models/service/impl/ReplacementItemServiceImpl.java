package com.opbaquero.conexionaback.models.service.impl;

import com.opbaquero.conexionaback.models.dao.IReplacementItemDao;
import com.opbaquero.conexionaback.models.entity.ReplacementItem;
import com.opbaquero.conexionaback.models.service.interfaces.IReplacementItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplacementItemServiceImpl implements IReplacementItemService {

    @Autowired
    private IReplacementItemDao replacementItemDao;

    @Override
    @Transactional
    public ReplacementItem save(ReplacementItem replacementItem) {
        return replacementItemDao.save(replacementItem);
    }
}
