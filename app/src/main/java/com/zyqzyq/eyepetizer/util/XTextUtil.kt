package com.zyqzyq.eyepetizer.util

import java.util.regex.Pattern

object XTextUtils {
    private val TAG = "XTextUtils"

    fun getContentList(content: String): ArrayList<String> {
        var content = content
        val list = ArrayList<String>()
        var s = ""
        var n = ""
        var sn = 0
        content += " "
        val len = content.length

        for (i in 0 until len) {

            val c = content[i]
            var c_1 = ' '
            if (i >= 1) {
                c_1 = content[i - 1]
            }
            if (isEnglish(c + "") || isEnglish(c_1 + "") && c + "" == "'") {
                s += c
                sn = 1
            } else if (isNumber(c + "") || isNumber(c_1 + "") && c + "" == ".") {
                n += c
                sn = 2
            } else {
                if (s != "" && (n != "") and (sn == 1)) {
                    list.add(n + s)

                } else if (s != "" && (n != "") and (sn == 2)) {
                    list.add(s + n)

                } else if (s != "" && n == "") {
                    list.add(s)
                } else if (n != "" && s == "") {
                    list.add(n)
                }
                list.add(c + "")
                sn = 0
                n = ""
                s = ""

            }
        }
        return list
    }

    fun isEnglish(s: String): Boolean {

        val p = Pattern.compile("^[a-zA-Z]*$")
        val m = p.matcher(s)
        return if (m.matches()) {
            true
        } else {
            false
        }
    }


    fun isNumber(s: String): Boolean {

        val p = Pattern.compile("^[0-9]*$")
        val m = p.matcher(s)
        return if (m.matches()) {
            true
        } else {
            false
        }
    }

    fun isChinese(s: String): Boolean {

        val p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]*$")
        val m = p.matcher(s)
        return if (m.matches()) {
            true
        } else {
            false
        }
    }
}

