package com.hc.nacoscompare.service.parser

import cn.hutool.core.lang.Dict
import cn.hutool.setting.yaml.YamlUtil
import com.hc.nacoscompare.domain.DataIdType
import com.hc.nacoscompare.util.DictUtil
import java.io.PrintWriter
import java.io.StringReader
import java.io.StringWriter

class YmlPaser : Parser {
    override fun parse(content: String): Dict {
        return YamlUtil.load(StringReader(content));
    }

    override fun format(content: Dict): String {
        val  foldDict=DictUtil.fold(content,DataIdType.YAML)
        val writer = StringWriter()
        YamlUtil.dump(foldDict, PrintWriter(writer))
        return writer.toString()
    }
}

fun main() {
    val ymlPaser = YmlPaser()
    val map = ymlPaser.parse("orgId: 101010000319")
    val rs=ymlPaser.format(map)
    println(rs)
}