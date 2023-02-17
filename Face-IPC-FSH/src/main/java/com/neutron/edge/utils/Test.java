package com.neutron.edge.utils;

import com.neutron.edge.utils.device.entity.CommandResult;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * @author LiuJiaJia
 */
public class Test {
    
    public static void main(String[] args) {
        CommandResult school = new CommandResult();
        school.setReturnCode(0);
        school.setReturnStr("OK");
        school.setPushEventInfo(true);
        school.setPushEventPic(true);
        JSONObject jsonObject = new JSONObject();
        System.out.println();
    }
    
    // public static void filyCopy2(File one,File two){
    //     try (FileInputStream fileInput = new FileInputStream(one);
    //          FileOutputStream fileOutput = new FileOutputStream(two);)
    //     {
    //         byte[] b = new byte[1024];
    //         int len = 0;
    //         while((len = fileInput.read(b)) != -1){
    //             fileOutput.write(b, 0, len);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    
}
