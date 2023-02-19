package com.neutron.edge.projectConfigTest;

import com.neutron.edge.commons.constants.config.ProjectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @version 1.0
 * @author Liu JiaJia
 * @description 项目信息读取测试类
 * @date 2023/2/17 16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectConfigTest {
    // 装配projectConfig
    @Autowired
    private ProjectConfig projectConfig;
    
    @Test
    public void getProjectConfig(){
        System.out.println(projectConfig.getName());
        System.out.println(projectConfig.getVersion());
        System.out.println(projectConfig.getOrganization().getName());
        System.out.println(Arrays.toString(projectConfig.getOrganization().getRoles()));
        System.out.println(projectConfig.getOrganization().getMembers());
        
    }


}
