package com.bracelet.socket.business.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bracelet.exception.BizException;
import com.bracelet.util.RespCode;
import com.bracelet.socket.business.IService;

/**
 * 业务类型工厂类,根据type返回对应的业务处理对象
 * 
 */
@Component
public class SocketBusinessFactory {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 根据名称注入对应的业务
     */
    @Resource
    private IService loginService;
    @Resource
    private IService heartCheck;
    @Resource
    private IService openLightService;
    @Resource
    private IService closeLightService;
    @Resource
    private IService infraredService;
    @Resource
    private IService getInfraredDeviceIdService;
    @Resource
    private IService startMatch;
    @Resource
    private IService nextMatch;
    @Resource
    private IService matchSuccess;
    @Resource
    private IService infraredController;
    @Resource
    private IService sendLableNum;
    @Resource
    private IService sendAllKey;
    @Resource
    private IService getJiHuoMa;
    
    public IService getService(int type) throws BizException {
        logger.info("*****type:" + type);
        switch (type) {
        case 1:
            // 登录
            return loginService;
        case 9:
            // 心跳
            return heartCheck;
        case 10:
            // 发送匹配型号总数
            return sendLableNum;
        case 11:
            // 下载码库成功，发送所有按键
            return sendAllKey;
        case 12:
            // 获取激活码
            return getJiHuoMa;
        case 30:
            // 开灯
            return openLightService;
        case 31:
            // 关灯
            return closeLightService;
        case 32:
            // 红外设置
            return infraredService;
        case 33:
            // 获取红外id
            return getInfraredDeviceIdService;
        case 34:
            // 开始匹配
            return startMatch;
        case 35:
            // 匹配下一个
            return nextMatch;
        case 36:
            // 匹配成功
            return matchSuccess;
        case 37:
            // 红外控制
            return infraredController;
       
        default:
            logger.info("找不到对应的类型:" + type);
            throw new BizException(RespCode.DEV_REQ_TYPE_ERR);
        }
    }
}
