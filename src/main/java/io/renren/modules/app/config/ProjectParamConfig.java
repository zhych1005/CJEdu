package io.renren.modules.app.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application.mp")
@Component
public class ProjectParamConfig {

    /*公众号*/
    public String appId;

    public String appSecret;

    /*模板ID*/
    public String newOrderTemplateId;

    public String deliveryOkTemplateId;

    public String assignNotifyTemplateId;

    public String serviceTemplateId;

    private String modifyDriverTemplateId;

    private String takeOrderOK;

    private String classTemplateId;

    /*详情页*/
    public String assignOrderUrl;

    public String driverOrderUrl;

    public String serviceOrderUrl;

    /*列表页*/
    public String assignUrl;

    public String driverUrl;

    public String serviceUrl;

    /*回调*/
    public String uri;

    /*图片相关路径*/
    private  String deskUrl; //磁盘path

    private String picUrl; //虚拟路径
}