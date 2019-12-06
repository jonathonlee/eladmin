package me.zhengjie.modules.system.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;

import me.zhengjie.modules.monitor.service.RedisService;
import me.zhengjie.modules.security.security.JwtUser;
import me.zhengjie.modules.system.domain.PartyConfig;
import me.zhengjie.modules.system.service.PartyConfigService;
import me.zhengjie.modules.system.service.dto.PartyConfigQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
* @author lyx
* @date 2019-11-16
*/
@Api(tags = "PartyConfig管理")
@RestController
@RequestMapping("api")
public class PartyConfigController {

    @Autowired
    private PartyConfigService partyConfigService;
    @Autowired
    private RedisService redisService;

    @Log("查询PartyConfig")
    @ApiOperation(value = "查询PartyConfig")
    @GetMapping(value = "/partyConfig")
    @PreAuthorize("hasAnyRole('ADMIN','PARTYCONFIG_ALL','PARTYCONFIG_SELECT')")
    public ResponseEntity getPartyConfigs(PartyConfigQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(partyConfigService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增PartyConfig")
    @ApiOperation(value = "新增PartyConfig")
    @PostMapping(value = "/partyConfig")
    @PreAuthorize("hasAnyRole('ADMIN','PARTYCONFIG_ALL','PARTYCONFIG_CREATE')")
    public ResponseEntity create(@Validated @RequestBody PartyConfig resources){
        JwtUser user =(JwtUser)redisService.getObjectByKey("user");
        String userName="";
        if(null!=user){
            userName=user.getUsername();
        }
        resources.setCreator(userName);
        return new ResponseEntity(partyConfigService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改PartyConfig")
    @ApiOperation(value = "修改PartyConfig")
    @PutMapping(value = "/partyConfig")
    @PreAuthorize("hasAnyRole('ADMIN','PARTYCONFIG_ALL','PARTYCONFIG_EDIT')")
    public ResponseEntity update(@Validated @RequestBody PartyConfig resources){
        partyConfigService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除PartyConfig")
    @ApiOperation(value = "删除PartyConfig")
    @DeleteMapping(value = "/partyConfig/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PARTYCONFIG_ALL','PARTYCONFIG_DELETE')")
    public ResponseEntity delete(@PathVariable String id){
        partyConfigService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}