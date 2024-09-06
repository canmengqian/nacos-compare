package com.hc.nacoscompare

import com.github.difflib.DiffUtils
import com.github.difflib.UnifiedDiffUtils
import com.github.difflib.patch.Patch
import com.github.difflib.text.DiffRow
import com.github.difflib.text.DiffRowGenerator
import com.hc.nacoscompare.common.logger
import org.junit.jupiter.api.Test


class DiffTextTest {
    @Test
    fun test_simple_list(): Unit {
        var s1 = mutableListOf("1", "2","4")
        var s2 = mutableListOf("1", "2", "3")
        val patch: Patch<String> = DiffUtils.diff(s1, s2)
        patch.deltas.forEach {
            it.logger().info(it.toString())
        }
       val diffs= UnifiedDiffUtils.generateUnifiedDiff("left","right",s1,patch,0)
        diffs.forEach {
            it.logger().info(it)
        }

    }
    @Test
    fun  test_simple_str(){
        val  s= "hello , world"
        val s2= "hello1"
        val patch:Patch<String> =DiffUtils.diffInline(s,s2)
        patch.deltas.forEach {
            it.logger().info(it.toString())
        }
        UnifiedDiffUtils.generateUnifiedDiff("left","right", listOf(s),patch,0).forEach {
            it.logger().info(it.toString())
        }
        val generator = DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            .oldTag { f: Boolean? -> "~" }
            .newTag { f: Boolean? -> "**" }
            .build()
        val rows: List<DiffRow> = generator.generateDiffRows(
            listOf("This is a test senctence.", "This is the second line.", "And here is the finish."),
            listOf("This is a test for diffutils.", "This is the second line.")
        )

        println("|original|new|")
        println("|--------|---|")
        for (row in rows) {
            println("|" + row.oldLine + "|" + row.newLine + "|")
        }
    }
}