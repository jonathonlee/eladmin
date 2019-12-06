package me.zhengjie.modules.system.repository;


import me.zhengjie.modules.system.domain.PartyConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author lyx
* @date 2019-11-16
*/
public interface PartyConfigRepository extends JpaRepository<PartyConfig, String>, JpaSpecificationExecutor {
}