package me.zhengjie.modules.system.service;

import me.zhengjie.modules.system.domain.PartyConfig;
import me.zhengjie.modules.system.service.dto.PartyConfigDTO;
import me.zhengjie.modules.system.service.dto.PartyConfigQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author lyx
* @date 2019-11-16
*/
//@CacheConfig(cacheNames = "partyConfig")
public interface PartyConfigService {

    /**
    * 查询数据分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable
    Map<String,Object> queryAll(PartyConfigQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria
    * @return
    */
    //@Cacheable
    List<PartyConfigDTO> queryAll(PartyConfigQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    PartyConfigDTO findById(String id);

    /**
     * 创建
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    PartyConfigDTO create(PartyConfig resources);

    /**
     * 编辑
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(PartyConfig resources);

    /**
     * 删除
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(String id);
}