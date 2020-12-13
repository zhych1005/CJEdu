package io.renren.modules.app.utils;

import io.renren.modules.app.config.ProjectParamConfig;
import io.renren.modules.app.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class MessageUtils {


    private WxMpService wxMpService;

    @Autowired
    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    private static MessageUtils messageUtils;

    private final ProjectParamConfig projectParamConfig;

    @Autowired
    public MessageUtils(ProjectParamConfig projectParamConfig) {
        this.projectParamConfig = projectParamConfig;
    }

    SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @PostConstruct
    public void init() {
        messageUtils = this;
        messageUtils.wxMpService = this.wxMpService;
    }

    public synchronized WxMpTemplateMessage wxTemplate(String openid, String notifyTempTemplateId, String Url) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(notifyTempTemplateId);
        templateMessage.setToUser(openid);
        templateMessage.setUrl(Url);
        return templateMessage;
    }

    /**
     * 派单消息
     * @param openid 消息接收人
     * @param messageVo 消息内容
     */
    public synchronized void assignNotify(String openid, MessageVO messageVo) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.assignNotifyTemplateId, projectParamConfig.getAssignOrderUrl());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您有一个订单<" + messageVo.getOrderId() + ">需要分派！", "#E1B741"),
                new WxMpTemplateData("keyword1", messageVo.getCustomerName(), "#E1B741"),
                new WxMpTemplateData("keyword2", messageVo.getCustomerMobile(), "#E1B741"),
                new WxMpTemplateData("keyword3", messageVo.getCustomerAddress(), "#E1B741"),
                new WxMpTemplateData("keyword4", messageVo.getProductName(), "#E1B741"),
                new WxMpTemplateData("remark", messageVo.getDescription(), "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【派单消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【派单消息】发送失败，{}", e);
        }
    }

    /**
     * 司机消息
     * @param openid 消息接收人
     * @param messageVo 消息内容
     */
    public synchronized void driverNotify(String openid, MessageVO messageVo) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.getNewOrderTemplateId(), projectParamConfig.getDriverOrderUrl());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您有一个新订单需要配送！", "#E1B741"),
                new WxMpTemplateData("keyword1", String.valueOf(messageVo.getOrderId()), "#E1B741"),
                new WxMpTemplateData("keyword2", messageVo.getProductName(), "#E1B741"),
                new WxMpTemplateData("keyword3", messageVo.getCustomerName(), "#E1B741"),
                new WxMpTemplateData("keyword4", messageVo.getCustomerMobile(), "#E1B741"),
                new WxMpTemplateData("keyword5", messageVo.getCustomerAddress(), "#E1B741"),
                new WxMpTemplateData("remark", messageVo.getDescription(), "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【司机新订单消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【司机新订单消息】发送失败，{}", e);
        }
    }

    /**
     * @param openid 司机的openid
     * @param messageVo 消息参数
     * @param remark 改派说明
     */
    public synchronized void modifyDriverNotify(String openid, MessageVO messageVo, String remark) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.getModifyDriverTemplateId(), projectParamConfig.getDriverOrderUrl());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "订单派送变更，保持电话畅通！", "#E1B741"),
                new WxMpTemplateData("keyword1", String.valueOf(messageVo.getOrderId()), "#E1B741"),
                new WxMpTemplateData("keyword2", sbf.format(new Date()), "#E1B741"),
                new WxMpTemplateData("remark", remark, "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【司机改派消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【司机改派消息】发送失败，{}", e);
        }
    }



    /**
     * 订单配送完成(向派单发送通知)
     * @param openid 消息接收人
     * @param messageVo 消息内容
     */
    public synchronized void OrderFinishNotify(String openid, MessageVO messageVo, Date time, BigDecimal proceeds) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.getDeliveryOkTemplateId(), projectParamConfig.assignOrderUrl);
        String keyword1 = "￥" + messageVo.getProductAmount() + "-实付:" + proceeds;
        if (messageVo.getAgencyFund() == 2) {
            keyword1 = "无需代收款！";
        }
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "订单配送完成！", "#E1B741"),
                new WxMpTemplateData("keyword1", keyword1, "#E1B741"),
                new WxMpTemplateData("keyword2", messageVo.getProductName(), "#E1B741"),
                new WxMpTemplateData("keyword3", messageVo.getCustomerAddress(), "#E1B741"),
                new WxMpTemplateData("keyword4", sbf.format(time), "#E1B741"),
                new WxMpTemplateData("keyword5", String.valueOf(messageVo.getOrderId()), "#E1B741"),
                new WxMpTemplateData("remark", messageVo.getDescription(), "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【订单配送完成消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【订单配送完成消息】发送失败，{}", e);
        }
    }

    /**
     * 司机接单成功
     * @param openid 消息接收人
     * @param messageVo 消息内容
     */
    public synchronized void takeOrderOK(String openid, MessageVO messageVo) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.getTakeOrderOK(), projectParamConfig.assignOrderUrl);
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "接单成功！", "#E1B741"),
                new WxMpTemplateData("keyword1", String.valueOf(messageVo.getOrderId()), "#E1B741"),
                new WxMpTemplateData("keyword2", messageVo.getProductName(), "#E1B741"),
                new WxMpTemplateData("keyword3", messageVo.getCustomerName(), "#E1B741"),
                new WxMpTemplateData("keyword4", messageVo.getCustomerAddress(), "#E1B741"),
                new WxMpTemplateData("keyword5", "派送中", "#E1B741"),
                new WxMpTemplateData("remark", messageVo.getDescription(), "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【订单配送完成消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【订单配送完成消息】发送失败，{}", e);
        }
    }

    /**
     * 维修员模板消息
     * @param openid 维修员openid
     * @param messageVo 消息内容
     */
    public synchronized void serviceOrderNotify(String openid, MessageVO messageVo, String problemDescription, Date time) {
        WxMpTemplateMessage templateMessage = wxTemplate(openid, projectParamConfig.getServiceTemplateId(), projectParamConfig.serviceOrderUrl);
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "您有一个维修订单<" + messageVo.getOrderId() + ">需要处理！", "#E1B741"),
                new WxMpTemplateData("keyword1", messageVo.getProductName(), "#E1B741"),
                new WxMpTemplateData("keyword2", messageVo.getCustomerAddress(), "#E1B741"),
                new WxMpTemplateData("keyword3", sbf.format(time), "#E1B741"),
                new WxMpTemplateData("keyword4", messageVo.getDescription(), "#E1B741"),
                new WxMpTemplateData("remark", problemDescription, "#E22018")
        );
        templateMessage.setData(data);
        try {
            messageUtils.wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.info("【维修消息】发送成功");
        } catch (WxErrorException e) {
            log.error("【维修消息】发送失败，{}", e);
        }
    }
}