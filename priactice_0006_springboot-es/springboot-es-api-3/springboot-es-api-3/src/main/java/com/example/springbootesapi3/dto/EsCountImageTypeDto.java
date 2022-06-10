package com.example.springbootesapi3.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ProjectName: dental_ai_backend
 * @Package: com.deepcare.archive.dto
 * @ClassName: EsCountImageTypeDto
 * @Descripion: java类作用描述
 * @Author: zt
 * @CreateDate: 2021/10/29 2:19 下午
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/29 2:19 下午
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class EsCountImageTypeDto implements Serializable {

    /** 影像｜报告类型编码 */
    private String imageTypeCode;
    /** 影像｜报告类型名称 */
    private String imageTypeName;
    /** 影像｜报告数量 */
    private Integer imageNum;
    /** 影像ID */
    private String imageId;
    /** 就诊状态ID */
    private String conId;
    /** AI服务影像ID */
    private String aiImageId;
    /** 健康报告地址(相对地址)*/
    private String healthReportPath;

}
