package com.hc.nacoscompare.service.parser

import cn.hutool.core.lang.Dict

class TextParser : Parser {
    override fun parse(content: String): Dict {
        return Dict.of("content" to content)
    }

    override fun format(content: Dict): String {
        if (content.containsKey("content")){
            return content.getStr("content")
        }
        return ""
    }
}