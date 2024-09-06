package com.hc.nacoscompare.service

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.domain.Config
import com.hc.nacoscompare.domain.ConfigData
import com.hc.nacoscompare.domain.DataIdType
import com.hc.nacoscompare.service.parser.PropertiesParser
import com.hc.nacoscompare.service.parser.TextParser
import com.hc.nacoscompare.service.parser.YmlPaser

class ConfigParser {
    companion object {
        private val parsers = mutableMapOf(
            DataIdType.YAML to YmlPaser(),
            DataIdType.YML to YmlPaser(),
            DataIdType.PROPERTIES to PropertiesParser(),
            DataIdType.TXT to TextParser()
        )

        fun canParse(config: Config): Boolean {
            return if (DataIdTypeJudge.getType(config) == DataIdType.XML) false else true
        }

    }


    fun parse(config: Config): Dict {
        if (!canParse(config)) {
            return Dict.create()
        }
        return parsers[DataIdTypeJudge.getType(config)]?.parse(config.content) ?: Dict.create()
    }

    fun format(configData: ConfigData): String {
        return configData.data?.let { parsers[configData.dataIdType]?.format(it) } ?: ""
    }
}