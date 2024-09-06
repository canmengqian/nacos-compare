package com.hc.nacoscompare.service

import com.hc.nacoscompare.domain.Config
import com.hc.nacoscompare.domain.DataIdType

/*
 文件类型鉴别器
 */
class DataIdTypeJudge {

    companion object {
        fun getType(config: Config): DataIdType {
            if (config.dataId.endsWith(".properties")) {
                return DataIdType.PROPERTIES
            }
            if (config.dataId.endsWith(".yaml")) {
                return DataIdType.YAML
            }
            if (config.dataId.endsWith(".yml")) {
                return DataIdType.YML
            }
            if (config.dataId.endsWith(".json")) {
                return DataIdType.JSON
            }
            if (config.dataId.endsWith(".xml")) {
                return DataIdType.XML
            }
            return DataIdType.TXT
        }
    }


}