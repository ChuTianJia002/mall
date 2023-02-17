package com.neutron.edge.commons.constants.defined;

/**
 * @author Liu JiaJia
 * @version 1.0
 * @description TODO
 * @date 2023/2/14 16:36
 */
public class DeviceDefined {
    
    //***********************摄像头设备命令缓存前缀*************************//
    /**
     * 心跳key
     */
    public static final String HEART_BEAT = "heartBeat_";
    
    /**
     * 人脸库操作前缀
     */
    public static final String OPT_FrmFaceDatabase = "frmFaceDatabase_";
    public static final String OPT_FrmFaceDatabase_Working = "摄像头正在进行人脸库相关操作，请稍后再试";
    
    /**
     * 人脸模板
     */
    public static final String OPT_frmFacePicture = "frmFacePicture_";
    public static final String OPT_frmFacePicture_Working = "摄像头正在进行人脸模板相关操作，请稍后再试";
    
    /**
     * 人员通行记录
     */
    public static final String OPT_frmTrafficPeople = "frmTrafficPeople_";
    public static final String OPT_frmTrafficPeople_Working = "摄像头正在进行人员通行查询相关操作，请稍后再试";
    
    /**
     * 摄像头配置
     */
    public static final String OPT_frmCameraOption = "frmCameraOption_";
    public static final String OPT_frmCameraOption_Working = "摄像头正在进行配置相关操作，请稍后再试";
    
    
    /**
     * 人脸调度
     */
    public static final String OPT_frmFaceSchedule = "frmFaceSchedule_";
    public static final String OPT_frmFaceScheduleon_Working = "摄像头正在进行人脸调度相关操作，请稍后再试";
    
    
    /**
     * 人脸识别参数
     */
    public static final String OPT_frmVideoFaceRecognitionPara = "frmVideoFaceRecognitionPara_";
    public static final String OPT_frmVideoFaceRecognitionPara_Working = "摄像头正在进行人脸识别参数相关操作，请稍后";
    
    
    /**
     * 设备升级
     */
    public static final String OPT_frmSysUpdate = "frmSysUpdate_";
    public static final String OPT_frmSysUpdate_Working = "摄像头正在进行升级相关操作，请稍后";
    
    /**
     * 获取设备版本号
     */
    public static final String OPT_frmSysVersion = "frmGetFactoryInfo";
    public static final String OPT_frmSysVersion_Working = "摄像头正在进行升级相关操作，请稍后";
    
}
