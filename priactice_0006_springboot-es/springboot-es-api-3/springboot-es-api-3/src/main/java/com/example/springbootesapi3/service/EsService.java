package com.example.springbootesapi3.service;

import com.example.springbootesapi3.model.EsArchivesModel;
import com.example.springbootesapi3.util.StringChineseUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class EsService {


//    private final ElasticsearchRestTemplate restTemplate;
//    private final EsArchivesRepository repository;
//
//    @Autowired

//    public EsService(EsArchivesRepository repository,
//                             ElasticsearchRestTemplate restTemplate,
//                             ImageFeginApiImpl imageFeginApi) {
//        this.repository = repository;
//        this.restTemplate = restTemplate;
//    }

    @Autowired
    private ElasticsearchRestTemplate restTemplate;


    public SearchHits<EsArchivesModel> queryArchives(String clinicId, String searchText, Integer createTime, Integer updateTime, Integer visits, int page, int size, Integer tranStatus, Integer healthReportStatus, String[] oralProblems) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("clinicId", clinicId));
        if (!StringUtils.isEmpty(searchText)) {
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            //精确查询
            BoolQueryBuilder termBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.wildcardQuery("patientName.keyword", "*"+searchText+"*"))
                    .should(QueryBuilders.termQuery("phone", searchText))
                    .should(QueryBuilders.termQuery("complains.keyword", searchText));
            //拼音(全拼)查询
            MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("patientName", searchText).analyzer("ik_max_word");
            // 拼音简写包含匹配，如 njdl可以查
            QueryBuilder pingYinSampleQueryBuilder = QueryBuilders.matchQuery("patientName.SPY", "*"+searchText+"*");
            //拼音(全拼)查询
            WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("patientName.SPY", "*"+searchText+"*").boost(0.8f);
            MatchPhraseQueryBuilder pinyinMatchQueryBuilder = QueryBuilders.matchPhraseQuery("patientName", searchText+"*");
            //是否有中文判断
            if (StringChineseUtils.ifContainChinese(searchText)) {
                builder.should(termBuilder).should(matchQueryBuilder);
            } else {
                BoolQueryBuilder termBuilderPinyin = QueryBuilders.boolQuery()
                        .should(QueryBuilders.wildcardQuery("patientName", searchText+"*"))
                        .should(QueryBuilders.termQuery("phone", searchText))
                        .should(QueryBuilders.termQuery("complains.keyword", searchText));

                builder.should(termBuilderPinyin).should(pingYinSampleQueryBuilder).should(pinyinMatchQueryBuilder);
            }
            queryBuilder.must(builder);
        }
        if (tranStatus != null) {
            queryBuilder.must(QueryBuilders.termQuery("tranStatus", tranStatus));
        }
        //健康报告状态
        if (healthReportStatus != null) {
            queryBuilder.must(QueryBuilders.termQuery("healthReportStatus", healthReportStatus));
        }
        //口腔问题以及现有修复
        if (oralProblems != null && oralProblems.length > 0) {
            for (String oralProblem : oralProblems) {
                BoolQueryBuilder  oralBuilder= QueryBuilders.boolQuery().should(QueryBuilders.termQuery("oralProblems", oralProblem));
                queryBuilder.must(oralBuilder);
            }
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(queryBuilder);
        if(ObjectUtils.isEmpty(createTime) && ObjectUtils.isEmpty(updateTime) && ObjectUtils.isEmpty(visits)){
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("updateTime").order(SortOrder.DESC));
        }else {
            if (createTime != null) {
                if (createTime.equals(0)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC));
                } else if (createTime.equals(1)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("createTime").order(SortOrder.ASC));
                }
            }
            if (updateTime != null) {
                if (updateTime.equals(0)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("updateTime").order(SortOrder.DESC));
                } else if (updateTime.equals(1)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("updateTime").order(SortOrder.ASC));
                }
            }
            if (visits != null) {
                if (visits.equals(0)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("visits").order(SortOrder.DESC));
                } else if (visits.equals(1)) {
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("visits").order(SortOrder.ASC));
                }
            }
        }
        if (page > 0) {
            nativeSearchQueryBuilder.withPageable(PageRequest.of(page - 1, size));
        }
        SearchHits<EsArchivesModel> hits = this.restTemplate.search(nativeSearchQueryBuilder.build(), EsArchivesModel.class);
        return hits;
    }
}
