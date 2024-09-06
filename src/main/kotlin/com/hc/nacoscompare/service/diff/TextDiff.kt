package com.hc.nacoscompare.service.diff

import cn.hutool.core.lang.Dict

class TextDiff : Diff<Dict, Dict> {
    override fun diff(left: Dict, right: Dict) {
        KVDiff().diff(left, right)
    }
}