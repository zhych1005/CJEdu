package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("tb_stu")
public class StuEntity {

        private Integer stuId;

        private String stuName;

        private String openId;

        private String mobile;

        private Integer gender;

        private String address;

        private Integer age;

        private String parent;

        private Integer type;

        private String description;
}
