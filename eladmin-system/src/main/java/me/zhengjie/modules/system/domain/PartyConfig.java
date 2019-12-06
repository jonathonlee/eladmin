package me.zhengjie.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
* @author lyx
* @date 2019-11-16
*/
@Entity
@Data
@Table(name="party_config")
public class PartyConfig implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    // 聚会名称
    @Column(name = "name")
    private String name;

    // 0按部门，1按角色
    @Column(name = "type")
    private String type;

    // 备注
    @Column(name = "memo")
    private String memo;

    // 创建人
    @Column(name = "creator")
    private String creator;

    // 周间
    @Column(name = "weekends")
    private String weekends;

    // 创建时间
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    // 聚会开始时间
    @Column(name = "start_time")
    private Timestamp startTime;

    // 聚会结束时间
    @Column(name = "end_time")
    private Timestamp endTime;

    // 角色(岗位)的ids
    @ManyToMany
    @JoinTable(name = "party_roles", joinColumns = {@JoinColumn(name = "party_id",referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;


    @OneToOne
    @NotFound(action= NotFoundAction.IGNORE)
    @JoinColumn(name = "select_dept")
    private Dept dept;
    // 存放聚会时间的展示

    @Transient
    private String partyDateTime;
    @Transient
    private String roleStr;
    public void copy(PartyConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}