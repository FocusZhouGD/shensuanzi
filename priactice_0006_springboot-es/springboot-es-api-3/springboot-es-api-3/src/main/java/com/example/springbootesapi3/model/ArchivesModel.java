package com.example.springbootesapi3.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.deepcare.common.entity.AbstractEntity;
import lombok.Data;

import java.util.Date;

/**
 * @ProjectName: dental_ai_backend
 * @Package: com.deepcare.archive.model
 * @ClassName: ArchivesModel
 * @Descripion: 档案信息表
 * @Author: zt
 * @CreateDate: 2021/10/14 11:37 上午
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/14 11:37 上午
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@TableName(value = "dc_archives")
public class ArchivesModel extends AbstractEntity {

    @TableField(value = "patient_id")
    private String patientId;
    @TableField(value = "clinic_id")
    private String clinicId;
    @TableField(value = "medical_no")
    private String medicalNo;
    @TableField(value = "patient_name")
    private String patientName;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "gender")
    private String gender;
    @TableField(value = "birth")
    private Date birth;
    @TableField(value = "health_status")
    private String healthStatus;
    @TableField(value = "health_detail")
    private String healthDetail;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "del_flag")
    private Integer delFlag;
    @TableField(value = "remark")
    private String remark;
    @TableField(value = "tran_status")
    private Integer tranStatus;
    @TableField(value = "create_user")
    private String createUser;
    @TableField(value = "update_user")
    private String updateUser;
}
