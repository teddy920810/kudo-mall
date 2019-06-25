package org.linlinjava.litemall.db.service;

import org.linlinjava.litemall.db.dao.LitemallSiteMapper;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.domain.LitemallSiteExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallSiteService {
    @Resource
    private LitemallSiteMapper siteMapper;

    public List<LitemallSite> queryAll() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andDeletedEqualTo(false);
        List<LitemallSite> siteList = siteMapper.selectByExample(example);
        return siteList;
    }

    public List<LitemallSite> listIndex() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_index_%").andDeletedEqualTo(false);
        List<LitemallSite> siteList = siteMapper.selectByExample(example);
        return siteList;
    }

    public List<LitemallSite> listCustomized() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_customized_%").andDeletedEqualTo(false);
        List<LitemallSite> siteList = siteMapper.selectByExample(example);
        return siteList;
    }

    public List<LitemallSite> listBrand() {
        LitemallSiteExample example = new LitemallSiteExample();
        example.or().andKeyLike("kudo_brand_%").andDeletedEqualTo(false);
        List<LitemallSite> siteList = siteMapper.selectByExample(example);
        return siteList;
    }

    public void updateSite(List<LitemallSite> data) {
        for (LitemallSite site : data) {
            LitemallSiteExample example = new LitemallSiteExample();
            example.or().andKeyEqualTo(site.getKey()).andDeletedEqualTo(false);
            site.setUpdateTime(LocalDateTime.now());
            siteMapper.updateByExampleSelective(site, example);
        }

    }
}
