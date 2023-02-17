package com.neutron.edge.projectConfigTest;

import com.neutron.edge.commons.constants.config.ProjectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version 1.0
 * @author Liu JiaJia
 * @description 项目信息读取测试类
 * @date 2023/2/17 16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectConfigTest {
    // 装配Employee
    @Autowired
    private ProjectConfig projectConfig;
    
    @Test
    public void getProjectConfig(){
        System.out.println(projectConfig);
    }


}
