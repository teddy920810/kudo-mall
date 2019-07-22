package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallSiteMapper;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.domain.LitemallSiteExample;
import org.linlinjava.litemall.db.domain.LitemallSystem;
import org.linlinjava.litemall.db.domain.LitemallSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LitemallSiteService {
    @Resource
    private LitemallSiteMapper siteMapper;

    public Map<String, LitemallSite> queryAll() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andDeletedEqualTo(false);
        return getStringObjectMap(example);
    }

    private Map<String, LitemallSite> getStringObjectMap(LitemallSiteExample example) {
        List<LitemallSite> siteList = siteMapper.selectByExample(example);
        Map<String, LitemallSite> data = new HashMap<>();
        for (LitemallSite site : siteList) {
            data.put(site.getKey(), site);
        }
        return data;
    }

    public Map<String, LitemallSite> listIndex() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_index_%").andDeletedEqualTo(false);
        return getStringObjectMap(example);
    }

    public Map<String, LitemallSite> listCustomized() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_customized_%").andDeletedEqualTo(false);
        return getStringObjectMap(example);
    }

    public Map<String, LitemallSite> listBrand() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_brand_%").andDeletedEqualTo(false);
        return getStringObjectMap(example);
    }

    public void updateSite(Map<String, LitemallSite> data) {
        for (Map.Entry<String, LitemallSite> entry : data.entrySet()) {
            LitemallSiteExample example = new LitemallSiteExample();
            example.or().andKeyEqualTo(entry.getKey()).andDeletedEqualTo(false);

            LitemallSite site = entry.getValue();
            site.setUpdateTime(LocalDateTime.now());
            siteMapper.updateByExampleSelective(site, example);
        }
    }
}

