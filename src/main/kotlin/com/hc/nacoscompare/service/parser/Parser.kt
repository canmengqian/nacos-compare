package com.hc.nacoscompare.service.parser

import cn.hutool.core.lang.Dict

interface Parser {
    fun parse(content: String): Dict

    fun format(content: Dict): String
}