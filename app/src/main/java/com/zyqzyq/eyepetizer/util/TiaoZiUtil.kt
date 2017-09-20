package com.zyqzyq.eyepetizer.util

import android.widget.TextView
import java.util.regex.Pattern

class TiaoZiUtil(private val tv: TextView, private val s: String, private val time: Long = 700) {

    private val length: Int = s.length
    private var n = 0
    private var nn: Int = 0

    init {
        startTv(n)//开启线程
    }

        private fun startTv(n: Int) {

            Thread(
                    Runnable {
                        try {
//                            val stv = s.TiaoZiFormat(s,n)
                            val p = Pattern.compile("[0-9]*")

                            var stv = s.substring(0, n)//截取要填充的字符串
                            for (i in 0 until length-n) {
                                val m = p.matcher(s[i+n].toString())
                                when {
                                    m.matches() -> stv += "\u3000" //中文空格
                                    (s[i+n]+"").toByteArray().size == 1 -> stv += "\u0020" //英文空格
                                    else -> stv += "\u3000" //中文空格
                                }
                            }

                            tv.post { tv.text = stv }
                            Thread.sleep(time/length)//休息片刻
                            nn = n + 1//n+1；多截取一个
                            if (nn <= length) {//如果还有汉字，那么继续开启线程，相当于递归的感觉
                                startTv(nn)
                            }

                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
            ).start()
        }
}
//感觉可以用更优雅的姿势
/*text?.let {

                if (it.length > 0) {
                    val intervalTime = allTime / it.length
                    subscribe = Observable.interval(intervalTime.toLong(), TimeUnit.MILLISECONDS)
                            .take(it.length.toLong())
                            .io_main()
                            .subscribe({ i ->
                                content = content + it[i.toInt()]
                                realTextView?.text = content
                            }, { e -> e.printStackTrace() }, { isRun = false }
                            )
                }

            }*/