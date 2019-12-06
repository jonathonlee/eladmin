package me.zhengjie.modules.system.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


/**
* @author lyx
* @date 2019-11-16
*/
@Data
public class PartyConfigDTO implements Serializable {

    private String id;

    // 聚会类型
    private String name;

    // 0按部门，1按角色
    private String type;

    // 备注
    private String memo;

    // 创建人
    private String creator;

    // 创建时间
    private Timestamp createTime;

    private Timestamp startTime;

    private String weekends;
    // 聚会结束时间
    private Timestamp endTime;
    @ApiModelProperty(hidden = true)
    private Set<RoleSmallDTO> roles;
    private String partyDateTime;
    private DeptSmallDTO dept;
    private String roleStr;
    private Long deptId;
}