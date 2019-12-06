package me.zhengjie.modules.system.service.mapper;


import me.zhengjie.mapper.EntityMapper;
import me.zhengjie.modules.system.domain.PartyConfig;
import me.zhengjie.modules.system.service.dto.PartyConfigDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author lyx
* @date 2019-11-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PartyConfigMapper extends EntityMapper<PartyConfigDTO, PartyConfig> {

}