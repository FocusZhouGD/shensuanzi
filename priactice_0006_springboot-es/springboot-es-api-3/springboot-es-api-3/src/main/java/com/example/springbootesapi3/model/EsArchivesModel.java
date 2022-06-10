package com.example.springbootesapi3.model;

import com.deepcare.archive.dto.EsCountImageTypeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: dental_ai_backend
 * @Package: com.deepcare.archive.model
 * @ClassName: EsArchivesModel
 * @Descripion: java类作用描述
 * @Author: zt
 * @CreateDate: 2021/10/28 2:06 下午
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/28 2:06 下午
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Setting(settingPath = "elastivsearch_settings.json")
@Mapping(mappingPath = "elasticsearch_mapping.json")
@Document(indexName = "index_archives_order")
public class EsArchivesModel implements Serializable {

    private static final long serialVersionUID =-1L;

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String clinicId;
    @Field(type = FieldType.Text)
    private String archiveId;
    @Field(type = FieldType.Text)
    private String patientName;
    @Field(type = FieldType.Text)
    private String phone;
    @Field(type = FieldType.Text,index = false)
    private Integer visits;
    @Field(type = FieldType.Text,index = false)
    private List<EsCountImageTypeDto> archiveInfos;
    //主诉
    @Field(type = FieldType.Text)
    private List<String> complains;
    //口腔问题
    @Field(type = FieldType.Text)
    private List<String> oralProblems;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    public Date createTime;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time, fielddata = true)
    public Date updateTime;
    /** 成交状态*/
    @Field(type = FieldType.Text,index = false)
    private Integer tranStatus;
    /** 健康报告状态*/
    @Field(type = FieldType.Text,index = false)
    private Integer healthReportStatus;

}
