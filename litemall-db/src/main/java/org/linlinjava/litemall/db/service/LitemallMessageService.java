package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallMessageMapper;
import org.linlinjava.litemall.db.domain.LitemallMessage;
import org.linlinjava.litemall.db.domain.LitemallMessageExample;
import org.linlinjava.litemall.db.domain.LitemallOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallMessageService {

    @Resource
    private LitemallMessageMapper messageMapper;

    public List<LitemallMessage> query(int offset, int limit) {
        LitemallMessageExample example = new LitemallMessageExample();
        example.or().andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return messageMapper.selectByExample(example);
    }

    public int add(LitemallMessage message) {
        message.setAddTime(LocalDateTime.now());
        message.setUpdateTime(LocalDateTime.now());
        return messageMapper.insertSelective(message);
    }
}
