package io.renren.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_subject")
public class SubjectEntity {

    private Integer subId;

    private Integer stuId;

    private String subName;

    private Integer cost;

    private Integer subTotal;

    private Integer subSurplus;

    private Integer subUse;

    private String level;
}