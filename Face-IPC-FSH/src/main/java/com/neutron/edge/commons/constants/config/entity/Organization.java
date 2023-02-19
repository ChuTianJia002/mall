package com.neutron.edge.commons.constants.config.entity;

import lombok.Data;

import javax.annotation.security.DenyAll;
import java.util.Map;

/**
 * @version 1.0
 * @author Liu JiaJia
 * @description 项目信息组织实体类
 * @date 2023/2/17 17:10
 */
@Data
public class Organization {
    /**
     * 组织名称
     */
    private String name;
    
    /**
     * 组织角色
     */
    private String[] roles;
    
    /**
     * 组织成员
     */
    private Map<String,String> members;
}
