package com.neutron.edge.commons.constants.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @version 1.0
 * @author Liu JiaJia
 * @description TODO
 * @date 2023/2/17 16:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {
    
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目版本
     */
    private float version;
    /**
     * 开发组织
     */
    private Organization organization;
    
    


}
