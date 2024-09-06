package com.hc.nacoscompare.service.parser

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.util.DictUtil
import java.io.StringReader
import java.util.*

class PropertiesParser : Parser {
    override fun parse(content: String): Dict {
        val properties = Properties()
        properties.load(StringReader(content))
        val dict = Dict.create()
        val map = properties.map { it.key.toString() to it.value.toString() }.toMap()
        dict.putAll(map)
        return dict
    }

    override fun format(content: Dict): String {
        val dict = DictUtil.flatten(content, "")
        val props = mutableListOf<String>()
        dict.forEach { key, value ->
            props.add("$key=$value")
        }
        return props.joinToString("\n")
    }
}

fun main() {
    val propertiesParser = PropertiesParser()
    val map = propertiesParser.parse("a=1\nb=2")
    println(map)
    val rs = propertiesParser.format(map)
    println(rs)
}