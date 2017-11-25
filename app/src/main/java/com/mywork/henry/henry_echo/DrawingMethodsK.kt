package com.mywork.henry.henry_echo

import android.graphics.*
import android.os.Handler
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by henryyi on 11/24/17.
 */
class DrawingMethodsK {
    companion object Test {
        @Volatile
        var stopani = false
    }


     class drawPie : Runnable {
        var tempchart: Chart? = null
        var handler: Handler? = null
        var dataset: ArrayList<Data.dataset>? = null
        var time: Int = 0
        var startX: Float = 0.toFloat()
        val sleeptimes = 10
        val marginY = 25f
        var Color: Int = 0
        var name: String? = null
        var rectf: RectF? = null
        val drawingsave = ArrayList<Chart.setting>(100)
        val margin = 80f
        var segmentdegree: Float = 0.toFloat()
        var tempTextwidth: Float = 0.toFloat()
        val frameColor = -0x1
        var Yheightstartdrift: Float = 0.toFloat()
        var startdegree: Float = 0.toFloat()
        var pastdegree: Float = 0.toFloat()
        var degree: Float = 0.toFloat()
        var data: Float = 0.toFloat()
        var tempdegree: Float = 0.toFloat()
        val annotationmargin = 150f
        var dataRectF: RectF? = null
        var datacenterY: Float = 0.toFloat()
        var centerX: Float = 0.toFloat()
        var path: Path? = null
        val frameColor2 = -0x30100
        val linemargin = ArrayList<Float>()
        var align: Paint.Align? = null
        var textStartX: Float = 0.toFloat()
        override fun run() {
            val chart = tempchart
            var Yheight: Float
            try {
                Yheight = chart!!.height.toFloat();
                if (stopani) {

                    return
                }
                if (dataset!!.isEmpty()) {
                    return
                }
                //handler.post(new Runnable() {@Override public void run() {startwipe=true;chart.wipeout();startwipe=false;}});
                //if (stopani){HasForcedlyClosed=true;return;}
                //while (!startwipe||!Chart.haswiped){
                //    if (stopani){HasForcedlyClosed=true;return;}
                //}
                if (stopani) {

                    return
                }
                val ArcDrift = getArcdrift(chart)
                Yheight -= marginY + ArcDrift

                if (stopani) {

                    return
                }
                handler!!.post { chart.clear() }
                if (stopani) {

                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {

                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {

                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {

                    return
                }
                val segmenttime = time / dataset!!.size
                if (stopani) {

                    return
                }
                Yheightstartdrift = 2f * ArcDrift * (getYheightStartdrift(ArcDrift, chart) - 1).toFloat()
                if (stopani) {

                    return
                }
                Yheight -= Yheightstartdrift
                if (stopani) {

                    return
                }
                if (startX < chart.width / 2 + chart.x) {
                    if (stopani) {

                        return
                    }
                    startX = margin + linemargin[0]
                    var index = 1
                    startdegree = -135f
                    textStartX = 0f
                    align = Paint.Align.LEFT
                    for (temp in dataset!!) {
                        if (stopani) {

                            return
                        }
                        name = temp.nameortime
                        val temppaint = Paint()
                        Color = temp.Color
                        if (stopani) {

                            return
                        }
                        temppaint.textSize = 2 * ArcDrift
                        if (stopani) {

                            return
                        }
                        if (startX + 2 * ArcDrift + temppaint.measureText(name) - linemargin[index - 1] > chart.width - margin) {
                            if (stopani) {

                                return
                            }
                            Yheight += 2 * ArcDrift
                            startX = margin + linemargin[index]
                            index++
                        }
                        if (stopani) {

                            return
                        }
                        rectf = RectF(startX, Yheight - ArcDrift, startX + 2 * ArcDrift, Yheight + ArcDrift)
                        if (stopani) {

                            return
                        }
                        val finalColor = Color
                        val finalrectf = rectf
                        handler!!.post { chart.setArc(0F, 360F, finalColor, 10F, Paint.Style.FILL, null, finalrectf, 0F, 0F, 0F) }
                        if (stopani) {

                            return
                        }
                        savedrawing(Chart.setting(0F, 360F, Color, 10F, Paint.Style.FILL, null, rectf, 0F, 0F, 0F))
                        startX += 2 * ArcDrift
                        if (stopani) {

                            return
                        }
                        tempTextwidth = temppaint.measureText(name)
                        startX += tempTextwidth
                        if (stopani) {

                            return
                        }
                        val finalX = startX - tempTextwidth
                        val finalname = name
                        handler!!.post { chart.setText(finalX, Yheight, finalColor, Paint.Style.FILL, null, finalname, Paint.Align.LEFT, Chart.Text_Center, 2 * ArcDrift) }
                        if (stopani) {

                            return
                        }
                        savedrawing(Chart.setting(startX - tempTextwidth, Yheight, Color, Paint.Style.FILL, null, name, Paint.Align.LEFT, Chart.Text_Center, 2 * ArcDrift))
                        if (stopani) {

                            return
                        }
                        val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                        handler!!.post {
                            chart.clear()
                            chart.setArrayDrawing(tempdrawingsave)
                        }
                        if (stopani) {

                            return
                        }
                        try {
                            Thread.sleep((segmenttime / 2).toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {

                            return
                        }
                        try {
                            Thread.sleep((segmenttime / 2).toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {

                            return
                        }
                    }
                } else {
                    if (stopani) {

                        return
                    }
                    startX = chart.width - margin - linemargin[0]
                    var index = 1
                    startdegree = -45f
                    textStartX = chart.width.toFloat()
                    align = Paint.Align.RIGHT
                    if (stopani) {

                        return
                    }
                    for (temp in dataset!!) {
                        if (stopani) {

                            return
                        }
                        name = temp.nameortime
                        val temppaint = Paint()
                        Color = temp.Color
                        if (stopani) {

                            return
                        }
                        temppaint.textSize = 2 * ArcDrift
                        if (stopani) {

                            return
                        }
                        if (startX - 2 * ArcDrift - temppaint.measureText(name) + linemargin[index - 1] < margin) {
                            if (stopani) {

                                return
                            }
                            Yheight += 2 * ArcDrift
                            startX = chart.width - margin - linemargin[index]
                            index++
                        }
                        if (stopani) {

                            return
                        }
                        tempTextwidth = temppaint.measureText(name)
                        startX -= tempTextwidth
                        if (stopani) {

                            return
                        }
                        val finalX = startX + tempTextwidth
                        val finalColor = Color
                        val finalname = name
                        handler!!.post { chart.setText(finalX, Yheight, finalColor, Paint.Style.FILL, null, finalname, Paint.Align.RIGHT, Chart.Text_Center, 2 * ArcDrift) }
                        if (stopani) {

                            return
                        }
                        savedrawing(Chart.setting(startX + tempTextwidth, Yheight, Color, Paint.Style.FILL, null, name, Paint.Align.RIGHT, Chart.Text_Center, 2 * ArcDrift))
                        if (stopani) {

                            return
                        }
                        rectf = RectF(startX - 2 * ArcDrift, Yheight - ArcDrift, startX, Yheight + ArcDrift)
                        if (stopani) {

                            return
                        }
                        val finalrectf = rectf
                        handler!!.post { chart.setArc(0F, 360F, finalColor, 10F, Paint.Style.FILL, null, finalrectf, 0F, 0F, 0F) }
                        if (stopani) {

                            return
                        }
                        savedrawing(Chart.setting(0F, 360F, Color, 10F, Paint.Style.FILL, null, rectf, 0F, 0F, 0F))
                        if (stopani) {

                            return
                        }
                        val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                        handler!!.post {
                            chart.clear()
                            chart.setArrayDrawing(tempdrawingsave)
                        }
                        if (stopani) {

                            return
                        }
                        try {
                            Thread.sleep((segmenttime / 2).toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {

                            return
                        }
                        try {
                            Thread.sleep((segmenttime / 2).toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {

                            return
                        }
                        startX -= 2 * ArcDrift
                        if (stopani) {

                            return
                        }
                    }
                }
                if (stopani) {

                    return
                }
                val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                handler!!.post {
                    chart.clear()
                    chart.setArrayDrawing(tempdrawingsave)
                }
                if (stopani) {

                    return
                }
                var datasum = 0f
                if (stopani) {

                    return
                }
                for (temp in dataset!!) {
                    if (stopani) {

                        return
                    }
                    datasum += temp.data
                }
                if (stopani) {

                    return
                }
                centerX = (chart.width / 2).toFloat()
                datacenterY = (chart.height - annotationmargin - marginY) / 2
                if (stopani) {

                    return
                }
                dataRectF = RectF(centerX - datacenterY, 0f, centerX + datacenterY, datacenterY * 2)
                if (stopani) {

                    return
                }
                val radian = Math.toRadians(45.0)
                if (stopani) {

                    return
                }
                path = Path()
                path!!.moveTo(centerX, datacenterY)
                if (stopani) {

                    return
                }
                path!!.lineTo(centerX + datacenterY, datacenterY)
                segmentdegree = (sleeptimes * 360 / time).toFloat()
                if (stopani) {

                    return
                }
                var textsize = 50f
                if (textsize * dataset!!.size > chart.height - annotationmargin - 50) {
                    textsize = (chart.height - annotationmargin - 50) / dataset!!.size
                }
                val datatextUnit = (chart.height - annotationmargin - 50) / dataset!!.size
                var datatextY = 0f
                pastdegree = 0f
                for (temp in dataset!!) {
                    if (stopani) {

                        return
                    }
                    degree = temp.data / datasum * 360
                    name = temp.nameortime
                    data = temp.data
                    if (stopani) {

                        return
                    }
                    Color = temp.Color
                    if (stopani) {

                        return
                    }
                    drawdata(datatextY, textsize, chart)
                    if (stopani) {

                        return
                    }
                    if (pastdegree + degree < 359.999) {
                        pastdegree = pastdegree + degree
                    }
                    if (stopani) {

                        return
                    }
                    datatextY += datatextUnit
                    if (stopani) {

                        return
                    }
                }
            } finally {
                setDefault()
            }
        }

        private fun getYheightStartdrift(arcdrift: Float, chart: Chart?): Int {
            var Datawidth = margin
            var linenumber = 0
            var index = 1
            linemargin.clear()
            for (temp in dataset!!) {
                if (stopani) {
                    return 0
                }
                val measurename = temp.nameortime
                val temppaint = Paint()
                if (stopani) {
                    return 0
                }
                temppaint.textSize = 2 * arcdrift
                if (stopani) {
                    return 0
                }
                if (Datawidth + 2 * arcdrift + temppaint.measureText(measurename) > chart!!.width - margin) {
                    linenumber++
                    linemargin.add((chart.width - 2 * margin - (Datawidth - margin)) / 2)
                    Datawidth = margin
                }
                Datawidth += 2 * arcdrift
                if (stopani) {
                    return 0
                }
                Datawidth += temppaint.measureText(measurename)
                if (index == dataset!!.size) {
                    linemargin.add((chart.width - 2 * margin - (Datawidth - margin)) / 2)
                }
                index++
            }
            return linenumber + 1
        }

        private fun drawdata(dataY: Float, textsize: Float, chart: Chart?) {
            if (stopani) {
                return
            }
            tempdegree = 0f
            while (true) {
                if (stopani) {
                    return
                }
                if (tempdegree + segmentdegree < degree) {
                    tempdegree += segmentdegree
                    if (stopani) {
                        return
                    }
                    val finalstartdegree = startdegree
                    val finaltempdegree = tempdegree
                    val finalColor = Color
                    val finaldataRectF = dataRectF
                    val finalpastdegree = pastdegree
                    val finalcenterX = centerX
                    val finaldatacenterY = datacenterY
                    val finalname = name
                    val finaldata = data
                    val finalpath = path
                    handler!!.post {
                        chart!!.setArc(finalstartdegree, finaltempdegree, finalColor, 10F, Paint.Style.FILL, null, finaldataRectF, finalpastdegree, finalcenterX, finaldatacenterY)
                        chart.setTextonPath(0F, 16F, frameColor, Paint.Style.FILL, null, finalname + "·" + Math.round(finaldata).toString() + "·KWH",
                                Paint.Align.RIGHT, 20F, finalpath, finalpastdegree + finalstartdegree, finalcenterX, finaldatacenterY)
                    }
                    if (stopani) {
                        return
                    }

                    try {
                        Thread.sleep(sleeptimes.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (stopani) {
                        return
                    }
                } else {
                    Log.d("hasStop", "true")
                    if (stopani) {
                        return
                    }
                    val temppaint = Paint()
                    temppaint.textSize = textsize
                    val textwidth = temppaint.measureText(Math.round(data).toString() + "·KWH")
                    val colors = IntArray(3)
                    colors[0] = Color
                    colors[1] = Color
                    colors[2] = frameColor2
                    val positions = FloatArray(3)
                    positions[0] = 0f
                    positions[1] = 0.5f
                    positions[2] = 1f
                    if (stopani) {
                        return
                    }
                    savedrawing(Chart.setting(textStartX, dataY, Color, Paint.Style.FILL, LinearGradient(0f, dataY + 2 * textsize / 5, 0f, dataY + textsize + 2 * textsize / 5, colors, positions, Shader.TileMode.CLAMP), Math.round(data).toString() + "·KWH", align, Chart.Text_Top, textsize))
                    if (stopani) {
                        return
                    }
                    savedrawing(Chart.setting(startdegree, degree, Color, 10F, Paint.Style.FILL, null, dataRectF, pastdegree, centerX, datacenterY))
                    if (stopani) {
                        return
                    }
                    savedrawing(Chart.setting(0f, 16F, frameColor, Paint.Style.FILL, null, name + "·" + Math.round(data).toString() + "·KWH", Paint.Align.RIGHT, 20F, path, pastdegree + startdegree, centerX, datacenterY))
                    if (stopani) {
                        return
                    }
                    val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                    handler!!.post {
                        chart!!.clear()
                        chart.setArrayDrawing(tempdrawingsave)
                    }
                    if (stopani) {
                        return
                    }
                    try {
                        Thread.sleep(sleeptimes.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (stopani) {
                        return
                    }
                    break
                }
            }
        }

        //drawPie(Handler handler,Chart chart,int time,float startX, ArrayList dataset!!){
        //    this.configure(handler,chart,time,startX, dataset!!);

        //}

         fun configure(handler: Handler, chart: Chart, time: Int, startX: Float, dataset: ArrayList<*>): drawPie {
             this.handler = handler
             this.tempchart = chart
             this.time = time
             this.dataset = dataset.clone() as ArrayList<Data.dataset>
             this.startX = startX
             return this
         }

        private fun setDefault() {
            this.tempchart = null
            this.handler = null
            this.dataset!!.clear()
            this.time = 0
            this.startX = 0f
            this.Color = 0
            this.name = null
            this.rectf = null
            clearsaved()
            this.segmentdegree = 0f
            this.tempTextwidth = 0f
            this.Yheightstartdrift = 0f
            this.startdegree = 0f
            this.pastdegree = 0f
            this.degree = 0f
            this.data = 0f
            this.tempdegree = 0f
            this.dataRectF = null
            this.datacenterY = 0f
            this.centerX = 0f
            this.path = null
            this.linemargin.clear()
            this.align = null
            this.textStartX = 0f
        }

        private fun getArcdrift(chart: Chart?): Float {
            var temparcdrift = 50f
            var trytimes = 0
            var usedarcdrift = 0f
            var status = 0
            var usedstatus = 0
            var enough = false
            val ArcDrift: Float
            if (stopani) {
                return 0f
            }
            while (true) {
                if (stopani) {
                    return 0f
                }
                //Log.d("temparcdrift",String.valueOf(temparcdrift));
                if (usedarcdrift - temparcdrift > 0) {
                    usedstatus = status
                    status = 1
                } else if (usedarcdrift - temparcdrift < 0) {
                    usedstatus = status
                    status = 3
                }
                if (stopani) {
                    return 0f
                }
                //Log.d("continuationstatus",String.valueOf(Math.abs(usedstatus-continuationstatus)));
                //Log.d("theHeight",String.valueOf(getYheightStartdrift(temparcdrift)*2*temparcdrift));
                if (trytimes > 1 && Math.abs(usedstatus - status) == 2) {
                    if (stopani) {
                        return 0f
                    }
                    enough = true
                }
                if (stopani) {
                    return 0f
                }
                if (enough && getYheightStartdrift(temparcdrift, chart).toFloat() * 2f * temparcdrift < annotationmargin) {
                    if (stopani) {
                        return 0f
                    }
                    ArcDrift = temparcdrift
                    break
                } else if (getYheightStartdrift(temparcdrift, chart).toFloat() * 2f * temparcdrift > annotationmargin) {
                    if (stopani) {
                        return 0f
                    }
                    usedarcdrift = temparcdrift
                    temparcdrift = temparcdrift - 0.1f
                } else if (getYheightStartdrift(temparcdrift, chart).toFloat() * 2f * temparcdrift < 140) {
                    if (stopani) {
                        return 0f
                    }
                    usedarcdrift = temparcdrift
                    temparcdrift = temparcdrift + 0.2f
                } else {
                    if (stopani) {
                        return 0f
                    }
                    ArcDrift = temparcdrift
                    break
                }
                if (stopani) {
                    return 0f
                }
                trytimes++
            }
            if (stopani) {
                return 0f
            }
            Log.d("theHeight", (getYheightStartdrift(ArcDrift, chart).toFloat() * 2f * ArcDrift).toString())
            Log.d("theArcDrift", ArcDrift.toString())
            //Yheight-=marginY+ArcDrift;
            return ArcDrift
        }

        private fun clearsaved() {
            drawingsave.clear()
        }

        private fun savedrawing(setting: Chart.setting) {
            drawingsave.add(setting)
        }

        fun start(handler: Handler, chart: Chart, time: Int, startX: Float, dataset: ArrayList<*>) {
            this.configure(handler, chart, time, startX, dataset)
            run()

        }


    }


    internal class drawHistogram : Runnable {
        var tempchart: Chart? = null
        var handler: Handler? = null
        var time: Int = 0
        val margin = 53f
        var rawtargetwidth: Float = 0.toFloat()
        val sleeptimes = 48
        var scaleunitY: Float = 0.toFloat()
        var scaleunit: Int = 0
        var targetwidth: Float = 0.toFloat()
        val targetheight = 615f//targetheight = rawtargetheight
        var targetX: Float = 0.toFloat()
        var Color: Int = 0
        var Usedcolor: Int = 0
        var segmentX1: Float = 0.toFloat()
        var tempsegmentX1: Float = 0.toFloat()
        var Yheight: Float = 0.toFloat()
        var segmentX2: Float = 0.toFloat()
        var tempsegmentX2: Float = 0.toFloat()
        var tempscaleY: Float = 0.toFloat()
        var segmentY1: Float = 0.toFloat()
        var tempsegmentY1: Float = 0.toFloat()
        var scale: Int = 0
        var startscale: Int = 0
        var datastartX = 0f
        var dataX: Float = 0.toFloat()
        var tempdataX: Float = 0.toFloat()
        var dataY: Float = 0.toFloat()
        var rawData: Int = 0
        var UsedrawData: Int = 0
        var dataTime: String? = null
        var UseddataTime: String? = null
        val FrameColor = -0x1
        var dataColorset: ArrayList<Int>? = null
        var data: ArrayList<*>? = null
        var FrameColor2: Int = 0
        var FrameColor3: Int = 0
        val dataWidth = 50
        var useddataY: Float = 0.toFloat()
        var tempdataY: Float = 0.toFloat()
        var datasegmentX: Float = 0.toFloat()
        var datasegmentY: Float = 0.toFloat()
        var dataShader: Shader? = null
        val drawingsave = ArrayList<Chart.setting>(100)
        var dataShaderfinal: Shader? = null
        var dataYdrift: Float = 0.toFloat()
        var usedDataTimesetting: Chart.setting? = null
        override fun run() {
            val chart = tempchart
            try {
                rawtargetwidth = chart!!.width - margin
                Yheight = (chart.height - 5).toFloat()
                if (stopani) {
                    return
                }
                //handler.post(new Runnable() {@Override public void run() {startwipe=true;chart.wipeout();startwipe=false;}});
                //if (stopani){HasForcedlyClosed=true;return;}
                //while (!startwipe||!Chart.haswiped){
                //    if (stopani){HasForcedlyClosed=true;return;}
                //}
                if (data!!.isEmpty()) {
                    return
                }
                if (stopani) {
                    return
                }
                startscale = scale + scaleunit
                if (stopani) {
                    return
                }
                handler!!.post { chart.clear() }
                if (stopani) {
                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {
                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {
                    return
                }
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (stopani) {
                    return
                }
                targetwidth = rawtargetwidth + margin
                segmentX1 = sleeptimes * (targetwidth - targetX) / time
                tempsegmentX1 = 0f
                segmentX2 = sleeptimes * (targetX - margin) / time
                tempsegmentX2 = 0f
                //segmentY2 =12*(targetX-margin)/nameortime;tempsegmentY2 =0;
                if (stopani) {
                    return
                }
                while (true) {
                    if (stopani) {
                        return
                    }
                    if (targetX + tempsegmentX1 + segmentX1 < targetwidth && targetX - tempsegmentX2 - segmentX2 > margin) {
                        if (stopani) {
                            return
                        }
                        tempsegmentX1 = tempsegmentX1 + segmentX1//因为这一行离Post离得太近，所以导致Post过去的变量已经被这里改变了。所以这里不要用累计方法了，用重绘的方法好了。
                        tempsegmentX2 = tempsegmentX2 + segmentX2//因为这一行离Post离得太近，所以导致Post过去的变量已经被这里改变了。所以这里不要用累计方法了，用重绘的方法好了。
                        if (stopani) {
                            return
                        }
                        val finaltargetX = targetX
                        val finalYheight = Yheight
                        val finaltempsegmentX1 = tempsegmentX1
                        val finaltempsegmentX2 = tempsegmentX2
                        handler!!.post {
                            chart.setSimpleline(finaltargetX, finalYheight, finaltargetX + finaltempsegmentX1, finalYheight, FrameColor, Paint.Cap.SQUARE, 10F, false)
                            chart.setSimpleline(finaltargetX, finalYheight, finaltargetX - finaltempsegmentX2, finalYheight, FrameColor, Paint.Cap.SQUARE, 10F, false)
                        }
                        if (stopani) {
                            return
                        }
                        try {
                            Thread.sleep(sleeptimes.toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {
                            return
                        }
                    } else {
                        if (stopani) {
                            return
                        }
                        savedrawing(Chart.setting(margin, Yheight, targetwidth, Yheight, FrameColor, Paint.Cap.SQUARE, 10F))
                        if (stopani) {
                            return
                        }
                        val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                        handler!!.post {
                            chart.clear()
                            chart.setArrayDrawing(tempdrawingsave)
                            //chart.setSimpleline(targetX, Yheight, targetX + tempsegmentX1 + segmentX1, Yheight, 0xff33b5e5, Paint.Cap.ROUND, 10, 1, false);
                            //chart.setSimpleline(targetX, Yheight, targetX - tempsegmentX2 - segmentX2, Yheight, 0xff33b5e5, Paint.Cap.ROUND, 10, 1, false);
                        }
                        if (stopani) {
                            return
                        }
                        try {
                            Thread.sleep(sleeptimes.toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {
                            return
                        }
                        Log.d("hasstop1", "true")
                        break
                    }
                    if (stopani) {
                        return
                    }

                }
                if (stopani) {
                    return
                }
                segmentY1 = sleeptimes * targetheight / time
                tempsegmentY1 = 0f
                tempscaleY = Yheight + scaleunitY//这样赋值是为了防止第一次Post因为在执行前变量就被下面的“tempscaleY = tempscaleY - scaleunitY;scale=scale+scaleunit;”
                //赋值语句给修改，以至于，第一个刻度消失。
                while (true) {
                    if (stopani) {
                        return
                    }
                    if (Yheight - tempsegmentY1 - segmentY1 > 0) {
                        if (stopani) {
                            return
                        }
                        tempsegmentY1 = tempsegmentY1 + segmentY1
                        if (stopani) {
                            return
                        }
                        val finaltargetX = targetX
                        val finaltempsegmentX2 = tempsegmentX2
                        val finalsegmentX2 = segmentX2
                        val finalYheight = Yheight
                        val finaltempsegmentY1 = tempsegmentY1
                        handler!!.post {
                            chart.setSimpleline(margin,
                                    finalYheight, margin,
                                    finalYheight - finaltempsegmentY1, FrameColor, Paint.Cap.SQUARE, 10f, true)
                        }
                        if (stopani) {
                            return
                        }

                        if (Yheight - tempsegmentY1 <= tempscaleY - scaleunitY) { //Keep note here!!!
                            if (stopani) {
                                return
                            }
                            tempscaleY = tempscaleY - scaleunitY
                            scale = scale + scaleunit//由于这里相距post太近，所以这个语句在post后面和在post前面并没有区别。而且可能还不稳定（有可能在Post后面执行了）导致刻度丢失，所以最后把赋值语句放到Post之前。
                            if (stopani) {
                                return
                            }
                            if ((Math.abs(tempscaleY - Yheight) / scaleunitY).toInt() <= dataColorset!!.size - 1) {
                                Color = dataColorset!![(Math.abs(tempscaleY - Yheight) / scaleunitY).toInt()]
                            } else {
                                Color = FrameColor
                            }
                            if (stopani) {
                                return
                            }
                            val finaltempscaleY = tempscaleY
                            val finalColor = Color
                            val finalscale = scale
                            handler!!.post(Runnable {
                                if (stopani) {
                                    return@Runnable
                                }
                                chart.setText(margin, finaltempscaleY - dataWidth / 2,
                                        finalColor, Paint.Style.FILL, null, finalscale.toString() + " ", Paint.Align.RIGHT, Chart.Text_Center, 33f)
                            })
                            savedrawing(Chart.setting(margin, tempscaleY - dataWidth / 2, Color, Paint.Style.FILL, null, scale.toString() + " ", Paint.Align.RIGHT, Chart.Text_Center, 33f))
                            if (stopani) {
                                return
                            }
                        }
                        if (stopani) {
                            return
                        }
                        try {
                            Thread.sleep(sleeptimes.toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {
                            return
                        }
                    } else {
                        if (stopani) {
                            return
                        }
                        savedrawing(Chart.setting(margin, Yheight, margin, 0f, FrameColor, Paint.Cap.SQUARE, 10f))
                        if (stopani) {
                            return
                        }
                        val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                        handler!!.post {
                            chart.clear()
                            //chart.setPoint(50,50,FrameColor, Paint.Cap.ROUND,50,true);
                            chart.setArrayDrawing(tempdrawingsave)
                        }
                        if (stopani) {
                            return
                        }
                        try {
                            Thread.sleep(sleeptimes.toLong())
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                        if (stopani) {
                            return
                        }
                        Log.d("hasstop2", "true")
                        break
                    }
                    if (stopani) {
                        return
                    }
                }
                dataX = rawtargetwidth / (data!!.size + 1)
                datastartX = margin
                Log.d("DATAX", dataX.toString())
                if (stopani) {
                    return
                }
                if (!data!!.isEmpty()) {
                    if (stopani) {
                        return
                    }
                    for (temp in data!!) {
                        if (stopani) {
                            return
                        }
                        UsedrawData = rawData
                        if (stopani) {
                            return
                        }
                        rawData = (temp as Data.dataset).data.toInt()
                        if (stopani) {
                            return
                        }
                        dataY = (temp as Data.dataset).data * (scaleunitY / scaleunit) + -startscale * (scaleunitY / scaleunit)
                        if (stopani) {
                            return
                        }
                        UseddataTime = dataTime
                        if (stopani) {
                            return
                        }
                        dataTime = getDataTime((temp as Data.dataset).nameortime)
                        //dataTime = ((Data.dataset) temp).nameortime;
                        if (stopani) {
                            return
                        }
                        datastartX = datastartX + dataX
                        if (stopani) {
                            return
                        }
                        drawdata(chart)
                        if (stopani) {
                            return
                        }


                    }
                }
            } finally {
                setDefault()
            }
        }
        //drawHistogram(Handler handler,Chart chart,int time, float targetX, float scaleunitY, int scaleunit, int scale, ArrayList<Integer> dataColorset, ArrayList data){
        //    this.configure(handler,chart,time,targetX,scaleunitY,scaleunit,scale,dataColorset,data);
        //}

        private fun getDataTime(data: String): String {
            val tempdata = Data.convertDatetoDaysandSeconds(data)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            //DateFormat format= SimpleDateFormat.getDateTimeInstance();
            //Log.d("now",format.format(new Date()));
            val tempnow = Data.convertDatetoDaysandSeconds(format.format(Date()))
            val DistanceBetweenDates = Math.abs(tempnow[1] - tempdata[1])
            val temp = data.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (DistanceBetweenDates == 0L) {
                temp[0] = ""
            } else {
                temp[0] = DistanceBetweenDates.toString() + "<" + " "
            }
            val temp2 = temp[1].split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return temp[0] + temp2[0] + ":" + temp2[1]
        }

        fun configure(handler: Handler, chart: Chart, time: Int, targetX: Float, scaleunitY: Float, scaleunit: Int, scale: Int, dataColorset: ArrayList<Int>, data: ArrayList<*>,
                      frameColor2: Int, frameColor3: Int): drawHistogram {
            this.handler = handler
            this.tempchart = chart
            this.time = time
            this.targetX = targetX - chart.x
            this.scaleunitY = scaleunitY
            this.scaleunit = scaleunit
            this.data = data.clone() as ArrayList<*>
            this.dataColorset = dataColorset.clone() as ArrayList<Int>
            this.scale = scale
            this.FrameColor2 = frameColor2
            this.FrameColor3 = frameColor3
            return this
        }

        private fun setDefault() {
            this.tempchart = null
            this.handler = null
            this.time = 0
            this.scaleunitY = 0f
            this.scaleunit = 0
            this.targetwidth = 0f
            this.targetX = 0f
            this.Color = 0
            this.Usedcolor = 0
            this.segmentX1 = 0f
            this.tempsegmentX1 = 0f
            this.segmentX2 = 0f
            this.tempsegmentX2 = 0f
            this.tempscaleY = 0f
            this.segmentY1 = 0f
            this.tempsegmentY1 = 0f
            this.scale = 0
            this.startscale = 0
            this.datastartX = 0f
            this.dataX = 0f
            this.tempdataX = 0f
            this.dataY = 0f
            this.rawData = 0
            this.UsedrawData = 0
            this.dataTime = null
            this.UseddataTime = null
            this.dataColorset!!.clear()
            this.data!!.clear()
            this.useddataY = 0f
            this.tempdataY = 0f
            this.datasegmentX = 0f
            this.datasegmentY = 0f
            this.dataShader = null
            clearsaved()
            this.dataShaderfinal = null
            this.dataYdrift = 0f
            this.FrameColor2 = 0
            this.FrameColor3 = 0
            this.usedDataTimesetting = null
        }

        fun start(handler: Handler, chart: Chart, time: Int, targetX: Float, scaleunitY: Float, scaleunit: Int, scale: Int, dataColorset: ArrayList<Int>, data: ArrayList<*>,
                  frameColor2: Int, frameColor3: Int) {
            this.configure(handler, chart, time, targetX, scaleunitY, scaleunit, scale, dataColorset, data, frameColor2, frameColor3)
            run()
        }

        private fun clearsaved() {
            drawingsave.clear()
        }

        private fun Removedrawing(setting: Chart.setting?) {
            drawingsave.remove(setting)
        }

        private fun savedrawing(setting: Chart.setting) {
            drawingsave.add(setting)
        }

        private fun savedrawing(index: Int, setting: Chart.setting) {
            drawingsave.add(index, setting)
        }

        private fun drawdata(chart: Chart) {
            if (stopani) {
                return
            }
            Usedcolor = Color
            if (stopani) {
                return
            }
            dataYdrift = Yheight - useddataY - (dataWidth / 2).toFloat() + dataWidth / 8
            if (stopani) {
                return
            }
            var times = 0
            if (stopani) {
                return
            }
            run {
                var temp = 0
                while (temp <= scale - startscale) {
                    if (stopani) {
                        return
                    }
                    if (dataY >= temp * (scaleunitY / scaleunit)) {
                        if (stopani) {
                            return
                        }
                        if (times <= dataColorset!!.size - 1) {
                            dataShaderfinal = LinearGradient(datastartX, Yheight, datastartX, Yheight - dataY - (dataWidth / 2).toFloat(), FrameColor, dataColorset!![times], Shader.TileMode.CLAMP)
                            Color = dataColorset!![times]
                        } else {
                            dataShaderfinal = LinearGradient(datastartX, Yheight, datastartX, Yheight - dataY - (dataWidth / 2).toFloat(), FrameColor, FrameColor, Shader.TileMode.CLAMP)
                            Color = FrameColor
                        }
                        times++
                    } else if (dataY < 0) {
                        dataShaderfinal = LinearGradient(datastartX, Yheight, datastartX, Yheight - dataY - (dataWidth / 2).toFloat(), FrameColor, FrameColor, Shader.TileMode.CLAMP)
                        Color = FrameColor
                    }
                    if (stopani) {
                        return
                    }
                    temp = temp + scaleunit
                }
            }
            if (stopani) {
                return
            }
            datasegmentX = sleeptimes * (rawtargetwidth / (data!!.size + 1)) / (time / data!!.size)
            datasegmentY = sleeptimes * dataY / (time / data!!.size)
            tempdataX = 0f
            tempdataY = 0f
            while (true) {
                if (stopani) {
                    return
                }
                if (tempdataY < dataY - datasegmentY) {
                    tempdataX = tempdataX + datasegmentX
                    tempdataY = tempdataY + datasegmentY
                    if (stopani) {
                        return
                    }
                    if (datastartX > dataX + margin) {
                        if (stopani) {
                            return
                        }
                        Log.d("HERERUNN", "YESSS")
                        val finaldatastartX = datastartX
                        val finaldataX = dataX
                        val finaldataYdrift = dataYdrift
                        val finaltempdataX = tempdataX
                        val finaluseddataY = useddataY
                        val finaldataY = dataY
                        val finalUsedcolor = Usedcolor
                        val finalColor = Color
                        handler!!.post {
                            chart.setLine(finaldatastartX - finaldataX, finaldataYdrift, finaldatastartX - finaldataX + finaltempdataX, (finaluseddataY - finaldataY) / finaldataX * finaltempdataX + finaldataYdrift, FrameColor, Paint.Cap.ROUND, (dataWidth / 4).toFloat(), Paint.Style.FILL,
                                    LinearGradient(finaldatastartX - finaldataX, finaldataYdrift, finaldatastartX, (finaluseddataY - finaldataY) / finaldataX * finaldataX + finaldataYdrift, finalUsedcolor, finalColor, Shader.TileMode.CLAMP), false)
                        }
                    }
                    if (stopani) {
                        return
                    }
                    var Temptimes = 0
                    var temp = 0
                    while (temp <= scale - startscale) {
                        if (tempdataY >= temp * (scaleunitY / scaleunit)) {
                            if (Temptimes <= dataColorset!!.size - 1) {
                                dataShader = LinearGradient(datastartX, Yheight, datastartX, Yheight - tempdataY, FrameColor, dataColorset!![Temptimes], Shader.TileMode.CLAMP)
                            } else {
                                dataShader = LinearGradient(datastartX, Yheight, datastartX, Yheight - tempdataY, FrameColor, FrameColor, Shader.TileMode.CLAMP)
                            }
                            Temptimes++
                        } else if (tempdataY < 0) {
                            dataShader = LinearGradient(datastartX, Yheight, datastartX, Yheight - tempdataY, FrameColor, FrameColor, Shader.TileMode.CLAMP)
                        }
                        temp = temp + scaleunit
                    }
                    if (stopani) {
                        return
                    }
                    val finaldatastartX = datastartX
                    val finalYheight = Yheight
                    val finaltempdataY = tempdataY
                    val finaldataShader = dataShader
                    handler!!.post { chart.setLine(finaldatastartX, finalYheight, finaldatastartX, finalYheight - finaltempdataY, FrameColor, Paint.Cap.BUTT, dataWidth.toFloat(), Paint.Style.FILL, finaldataShader, true) }
                    if (stopani) {
                        return
                    }
                    try {
                        Thread.sleep(sleeptimes.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (stopani) {
                        return
                    }
                } else {
                    Log.d("hasthisrun", "TRUETRUE")
                    if (stopani) {
                        return
                    }
                    savedrawing(Chart.setting(datastartX, Yheight - dataY, Color, Paint.Cap.ROUND, dataWidth.toFloat(), Paint.Style.FILL, dataShaderfinal))
                    if (stopani) {
                        return
                    }
                    savedrawing(Chart.setting(datastartX, Yheight, datastartX, Yheight - dataY, FrameColor, Paint.Cap.BUTT, dataWidth.toFloat(), Paint.Style.FILL, dataShaderfinal))
                    if (stopani) {
                        return
                    }
                    if (datastartX > dataX + margin) {
                        savedrawing(Chart.setting(datastartX - dataX, dataYdrift, datastartX, (useddataY - dataY) / dataX * dataX + dataYdrift, FrameColor, Paint.Cap.ROUND, (dataWidth / 4).toFloat(), Paint.Style.FILL,
                                LinearGradient(datastartX - dataX, dataYdrift, datastartX, (useddataY - dataY) / dataX * dataX + dataYdrift, Usedcolor, Color, Shader.TileMode.CLAMP)))
                        if (stopani) {
                            return
                        }
                        if (Yheight - useddataY - (dataWidth / 2).toFloat() >= 35) {
                            savedrawing(Chart.setting(datastartX - dataX, Yheight - useddataY - (dataWidth / 2).toFloat(), FrameColor2, Paint.Style.FILL, null, UsedrawData.toString() + "°C", Paint.Align.CENTER, Chart.Text_Bottom, 35f))
                        } else {
                            savedrawing(Chart.setting(datastartX - dataX, 35f, FrameColor2, Paint.Style.FILL, null, UsedrawData.toString() + "°C", Paint.Align.CENTER, Chart.Text_Bottom, 35f))
                        }
                        if (stopani) {
                            return
                        }
                        var pathstartY = Yheight - useddataY + 10
                        var realFrameColor3 = FrameColor3
                        if (pathstartY + getTextPathDrift(UseddataTime, 60) > Yheight + 5) {
                            pathstartY = Yheight - useddataY - (dataWidth / 2).toFloat() - 35f - 5f - getTextPathDrift(UseddataTime, 60).toFloat()
                            realFrameColor3 = FormatFactory.getColorWithoutAlpha(realFrameColor3) + -0x80000000
                            if (pathstartY < -getTextPathDrift(UseddataTime, 60) / 3) {
                                pathstartY = Yheight - useddataY + 10
                                realFrameColor3 = FrameColor3
                            }
                        }
                        if (Yheight - useddataY + 10 < 60) {
                            pathstartY = 60f
                        }
                        if (stopani) {
                            return
                        }
                        val path2 = Path()
                        path2.moveTo(datastartX - dataWidth / 2 + 3 - dataX, pathstartY)
                        path2.lineTo(datastartX - dataWidth / 2 + 3 - dataX, pathstartY + getTextPathDrift(UseddataTime, 60))
                        if (stopani) {
                            return
                        }
                        Removedrawing(usedDataTimesetting)
                        savedrawing(Chart.setting(0f, 0f, realFrameColor3, Paint.Style.FILL, null, UseddataTime, Paint.Align.RIGHT, 60f, path2, 0f, 0f, 0f))
                    }
                    if (stopani) {
                        return
                    }
                    if (Yheight - dataY - (dataWidth / 2).toFloat() >= 35) {
                        savedrawing(Chart.setting(datastartX, Yheight - dataY - (dataWidth / 2).toFloat(), FrameColor2, Paint.Style.FILL, null, rawData.toString() + "°C", Paint.Align.CENTER, Chart.Text_Bottom, 35f))
                    } else {
                        savedrawing(Chart.setting(datastartX, 35f, FrameColor2, Paint.Style.FILL, null, rawData.toString() + "°C", Paint.Align.CENTER, Chart.Text_Bottom, 35f))
                    }
                    if (stopani) {
                        return
                    }
                    var pathstartY = Yheight - dataY + 10
                    var realFrameColor3 = FrameColor3
                    if (pathstartY + getTextPathDrift(dataTime, 60) > Yheight + 5) {
                        pathstartY = Yheight - dataY - (dataWidth / 2).toFloat() - 35f - 5f - getTextPathDrift(dataTime, 60).toFloat()
                        realFrameColor3 = FormatFactory.getColorWithoutAlpha(realFrameColor3) + -0x80000000
                        if (pathstartY < -getTextPathDrift(dataTime, 60) / 3) {
                            pathstartY = Yheight - dataY + 10
                            realFrameColor3 = FrameColor3

                        }
                    }
                    if (Yheight - dataY + 10 < 60) {
                        pathstartY = 60f
                    }
                    if (stopani) {
                        return
                    }
                    val path = Path()
                    path.moveTo(datastartX - dataWidth / 2 + 3, pathstartY)
                    path.lineTo(datastartX - dataWidth / 2 + 3, pathstartY + getTextPathDrift(dataTime, 60))
                    if (stopani) {
                        return
                    }
                    usedDataTimesetting = Chart.setting(0f, 0f, realFrameColor3, Paint.Style.FILL, null, dataTime, Paint.Align.RIGHT, 60f, path, 0f, 0f, 0f)
                    savedrawing(usedDataTimesetting!!)
                    if (stopani) {
                        return
                    }
                    val tempdrawingsave = drawingsave.clone() as ArrayList<Chart.setting>
                    handler!!.post {
                        chart.clear()
                        chart.setArrayDrawing(tempdrawingsave)
                    }
                    if (stopani) {
                        return
                    }
                    useddataY = dataY
                    if (stopani) {
                        return
                    }
                    try {
                        Thread.sleep(sleeptimes.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    if (stopani) {
                        return
                    }
                    break
                }


            }
        }

        private fun getTextPathDrift(time: String?, textsize: Int): Int {
            val paint = Paint()
            paint.textSize = textsize.toFloat()//paint.setColor(0xff000000);
            //Rect measurement=new Rect();
            //paint.getTextBounds();
            return Math.ceil(paint.measureText(time).toDouble()).toInt()
        }

    }
}