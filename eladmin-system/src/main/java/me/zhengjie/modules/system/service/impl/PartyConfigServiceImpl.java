package me.zhengjie.modules.system.service.impl;

import cn.hutool.core.util.IdUtil;

import me.zhengjie.modules.system.domain.PartyConfig;
import me.zhengjie.modules.system.domain.Role;
import me.zhengjie.modules.system.repository.PartyConfigRepository;
import me.zhengjie.modules.system.service.PartyConfigService;
import me.zhengjie.modules.system.service.dto.PartyConfigDTO;
import me.zhengjie.modules.system.service.dto.PartyConfigQueryCriteria;
import me.zhengjie.modules.system.service.mapper.PartyConfigMapper;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author lyx
* @date 2019-11-16
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class PartyConfigServiceImpl implements PartyConfigService {

    @Autowired
    private PartyConfigRepository partyConfigRepository;

    @Autowired
    private PartyConfigMapper partyConfigMapper;

    @Override
    public Map<String,Object> queryAll(PartyConfigQueryCriteria criteria, Pageable pageable){
        Page<PartyConfig> page = partyConfigRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        List<PartyConfig> configList = page.getContent();
        Date date = new Date();
        String str = "yyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        for(PartyConfig config : configList){
            //处理展示的图片
            if("1".equals(config.getType())&&StringUtils.isNotEmpty(config.getWeekends())){
                String weeks=config.getWeekends().replace("1","周一").replace("2","周二").replace("3","周三").replace("4","周四").replace("5","周五").replace("6","周六").replace("7","周天");
                config.setPartyDateTime(weeks);
            }else{
                if(null!=config.getStartTime()&&null!=config.getEndTime()){
                    config.setPartyDateTime(sdf.format(config.getStartTime()).concat("至").concat(sdf.format(config.getEndTime())));
                }
            }
            Set<String> roles=new HashSet<String>();
            for(Role r:config.getRoles()){
                roles.add(r.getName());
            }
            //处理需要展示的角色
            String rolesStr= StringUtils.join(roles.toArray(), ",");
            config.setRoleStr(rolesStr);
        }
        return PageUtil.toPage(page.map(partyConfigMapper::toDto));
    }

    @Override
    public List<PartyConfigDTO> queryAll(PartyConfigQueryCriteria criteria){
        return partyConfigMapper.toDto(partyConfigRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public PartyConfigDTO findById(String id) {
        Optional<PartyConfig> partyConfig = partyConfigRepository.findById(id);
        ValidationUtil.isNull(partyConfig,"PartyConfig","id",id);
        return partyConfigMapper.toDto(partyConfig.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PartyConfigDTO create(PartyConfig resources) {
        resources.setId(IdUtil.simpleUUID()); 
        return partyConfigMapper.toDto(partyConfigRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(PartyConfig resources) {
        Optional<PartyConfig> optionalPartyConfig = partyConfigRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalPartyConfig,"PartyConfig","id",resources.getId());
        PartyConfig partyConfig = optionalPartyConfig.get();
        partyConfig.copy(resources);
        partyConfigRepository.save(partyConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        partyConfigRepository.deleteById(id);
    }
}