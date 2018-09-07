package MyReport;

import TestData.TestFilePath;
import Tools.MyData;
import Tools.MyRandom;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Administrator on 2017/12/21.
 */
public class TestListener {
    String reportPath;
    static String path = System.getProperties().getProperty("user.dir");
    private static Logger logger = Logger.getLogger(TestListener.class);

    StringBuilder my = new StringBuilder();

    //创建html头部信息
    public void StartHtml(){
        //文件流对象
        my.append("<html>\n" +
                "\t<head lang=\"en\">\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "\t\t<title>API测试报告</title>\n" +
                "\t\t<style type=\"text/css\">\n" +
                "\t\t\t/* CSS样式制作 */  \n" +
                "\t\t\t*{padding:0px; margin:0px;}\n" +
                "\t\t\ta{text-decoration:none; color:black;}\n" +
                "\t\t\t#tab ul{list-style:none; height:50px;line-height:50px; border-bottom:4px #FF8000 solid;text-align:center}\n" +
                "\t\t\t#tab ul li{cursor:pointer;float:left;list-style:none height:55px; line-height:30px;padding:0px 10px; margin:0px 15px; border:3px solid #FF8000; border-bottom:3px solid #FF8000;text-align:center }\n" +
                "\t\t\t.hide{display:none;}\n" +
                "\t\t\t.on{background:#2894FF;}\n" +
                "\t\t</style>\n" +
                "\t<link href=\"js/reportng.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
                "\t<script type=\"text/javascript\" src=\"js/jquery.js\"></script>\n" +
                "\t<script type=\"text/javascript\" src=\"js/reportng.js\"></script>\n" +
                "\t<script src=\"js/echarts.js\"></script>\n" +
                "\t\t<script type=\"text/javascript\">\n" +
                "\t\t// JS实现选项卡切换\n" +
                "\t\twindow.onload = function(){\n" +
                "\t\tvar myTab = document.getElementById(\"tab\");    //整个div\n" +
                "\t\tvar myUl = myTab.getElementsByTagName(\"ul\")[0];//一个节点\n" +
                "\t\tvar myLi = $(\"#tab ul li\");    //数组\n" +
                "\t\tvar myDiv = $(\"#tab>div\"); //数组\n" +
                "\t\tconsole.log(myDiv);\n" +
                "\t\tmyLi.click(function(){\n" +
                "\t\t\t\tvar index = $(this).index();\n" +
                "\t\t\t\tmyLi.removeClass(\"on\").addClass(\"off\");\n" +
                "\t\t\t\tmyDiv.removeClass(\"show\").addClass(\"hide\");\n" +
                "\t\t\t\t$(this).removeClass(\"off\").addClass(\"On\");\n" +
                "\t\t\t    if($(this).attr('id')=='data'){$('#list').removeClass('On')}\n" +
                "\t\t\t\telse{$('#data').removeClass('On')}\n" +
                "\t\t\t\tmyDiv.eq(index).removeClass(\"hide\").addClass(\"show\");\n" +
                "\t\t})\n" +
                "\t}\n" +
                "\t\t\t</script>\n" +
                "\t</head>\n" +
                "\t<body style=\"background:#F0FFFF\"><!-- HTML页面布局 -->\n" +
                "\t<br>\n" +
                "\t<div id =\"tab\" style=\"font-size:16px;\">\n" +
                "\t<p id=\"top1\" style=\"font-weight:bold;font-size:32px;font-family:楷体;\" align=\"center\">谷仓API测试报告("+MyData.format(MyData.time8)+")</p>\n" +
                "\t\t<br>\n" +
                "\t\t\t\t<ul>\n" +
                "\t\t\t\t<li class=\"off\" id=\"list\">测试结果明细</li>\n" +
                "\t\t\t\t<li class=\"on\" id=\"data\">测试结果统计</li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t<br>\n" +
                "\n" +
                "\t\t<div id=\"firstpage\" class=\"hide\" align=\"center\">\n" +
                "\t\t\t<table width=\"95%\" height=\"100\" border=\"1\" align=\"center\" cellspacing=\"0\" rules=\"all\" style=\"font-family:楷体;table-layout:fixed;font-size:20px;\" width=\"500px\">\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<thead>\n" +
                "\t\t\t\t<tr height=\"45\">\n" +
                "\t\t\t\t\t<th  WIDTH=\"150px\" >接口名称</th>\n" +
                "\t\t\t\t\t<th  WIDTH=\"300px\" >用例名称</th>\n" +
                "\t\t\t\t\t<th  WIDTH=\"400px\" >请求参数</th>\n" +
                "\t\t\t\t\t<th  WIDTH=\"400px\" >响应结果</th>\n" +
                "\t\t\t\t\t<th  WIDTH=\"200px\" >测试结果</th>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t</thead>\n" +
                "\t\t\t<tbody style=\"font-family:楷体;table-layout:fixed;font-size:16px\" align=\"center\" >");
    }

    //按接口执行时生成接口名称，在接口第一次调用示执行,qty合并行的值
    public void AddAPIName(String qty,String APIName){
        my.append("<tr height=\"30\">\n" +
                  "\t\t\t\t<!接口名称>\n" +
                  "\t\t\t\t<th style=\"word-wrap:break-word;font-size:20px;\" rowspan="+qty+">"+APIName+"</th>"
        );
    }

    //每一次执行用例写入入结果,数组的值分别为：0-用例名称，1-请求参数，2-响应内容，3-测试结果，4-预期结果
    public void AddTestResult(String[] result){
        if (result[3].trim().equals("PASS")) {
            long id1 = new MyRandom().getNumRandom(5);
            long id2 = new MyRandom().getNumRandom(6);
            my.append("<td style=\"word-wrap:break-word;\">"+result[0]+"</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-"+id1+"', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"LimeGreen\">RequestData</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id1+"\" ><small>"+ result[1] +"</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-" + id2 + "', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"LimeGreen\">预期结果：" + result[4] + "，执行通过</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id2+"\" ><small> " + result[2] + "</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t<td><font color=\"LimeGreen\">"+ result[3] +"</font></td>\n" +
                    "\t\t\t\t</tr>");
        }else if(result[3].trim().equals("FAIL")){
                long id3= new MyRandom().getNumRandom(5);
                long id4= new MyRandom().getNumRandom(6);
            my.append("<td style=\"word-wrap:break-word;\">"+result[0]+"</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-"+id3+"', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"red\">RequestData</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id3+"\" ><small>"+ result[1] +"</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-" + id4 + "', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"red\">预期结果：" + result[4] + "，执行失败</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id4+"\" ><small>" + result[2] + "</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t<td><font color=\"red\">"+ result[3] +"</font></td>\n" +
                    "\t\t\t\t</tr>");
        }else {
            long id5= new MyRandom().getNumRandom(5);
            long id6= new MyRandom().getNumRandom(6);
            my.append("<td style=\"word-wrap:break-word;\">"+result[0]+"</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-"+id5+"', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"GoldenRod\">RequestData</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id5+"\" ><small>"+ result[1] +"</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t\n" +
                    "\t\t\t\t\t<td style=\"word-wrap:break-word;\">\n" +
                    "\t\t\t\t\t\t<a align=\"left\" href=\"javascript:toggleElement('exception-" + id6 + "', 'block')\" title=\"Click to expand/collapse\"><b><font color=\"GoldenRod\">预期结果：" + result[4] + "，执行异常</font></b></a>\n" +
                    "\t\t\t\t\t\t\t<br>\n" +
                    "\t\t\t\t\t\t\t<div align=\"left\" class=\"stackTrace\" id=\"exception-"+id6+"\" ><small> " + result[2] + "</small> \n" +
                    "\t\t\t\t\t\t </div>\n" +
                    "\t\t\t\t\t</td>\n" +
                    "\t\t\t\t\t<td><font color=\"GoldenRod\">"+ result[3] +"</font></td>\n" +
                    "\t\t\t\t</tr>");
        }
    }

    //用例结果html数据分析
    public void AnalystResult(String[] data1,String[] API,String[] pass,String[] fail,String[] other){
        my.append("</tbody>\n" +
                "\t\t\t</table>\n" +
                "\t\t\t<br>\n" +
                "\t\t\t<a href=\"#firstPage\" style=\"font-size:18px;\">返回顶部</a>\n" +
                "\t\t</div>\n" +
                "\n" +
                "\t\t<div id=\"secondPage\" class=\"show\" align=\"center\" style=\"width:1800px;height:700px;\">\n" +
                "\t\t\t<table width=\"90%\" cellspacing=\"0\" border=\"1\" align=\"center\" style=\"font-family:楷体;table-layout:fixed;font-size:20px;width=500px\" rules=\"all\">\n" +
                "\t\t\t\t<tbody align=\"center\" >\n" +
                "\t\t\t\t<tr height=\"60\"><td>测试用例总数</td> <td>"+data1[0]+"</td> <td>测试通过数</td> <td>"+data1[1]+"</td><td>测试失败数</td>  <td>"+data1[2]+"</td>  <td>未执行/异常</td> <td>"+data1[3]+"</td></tr>\n" +
                "\t\t\t\t</tbody>\n" +
                "\t\t\t\t<div id=\"main\" class=\"show\" width=\"110%\" style=\"width: 1800px;height:650px;\">\n" +
                "\t\t\t\t\t<script type=\"text/javascript\">\n" +
                "\t\t\t\t\t\t// 基于准备好的dom，初始化echarts实例\n" +
                "\t\t\t\t\t\tvar app = echarts.init(document.getElementById('main'));\n" +
                "\t\t\t\t\t\tvar posList = ['inside'];\n" +
                "\n" +
                "\t\t\t\t\t\tapp.configParameters = {\n" +
                "\t\t\t\t\t\t\trotate: {\n" +
                "\t\t\t\t\t\t\t\tmin: 100,\n" +
                "\t\t\t\t\t\t\t\tmax: 200\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\talign: {\n" +
                "\t\t\t\t\t\t\t\toptions: {\n" +
                "\t\t\t\t\t\t\t\t\tleft: 'left',\n" +
                "\t\t\t\t\t\t\t\t\tcenter: 'center',\n" +
                "\t\t\t\t\t\t\t\t\tright: 'right'\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\tverticalAlign: {\n" +
                "\t\t\t\t\t\t\t\toptions: {\n" +
                "\t\t\t\t\t\t\t\t\ttop: 'top',\n" +
                "\t\t\t\t\t\t\t\t\tmiddle: 'middle',\n" +
                "\t\t\t\t\t\t\t\t\tbottom: 'bottom'\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\tposition: {\n" +
                "\t\t\t\t\t\t\t\toptions: echarts.util.reduce(posList, function (map, pos) {\n" +
                "\t\t\t\t\t\t\t\t\tmap[pos] = pos;\n" +
                "\t\t\t\t\t\t\t\t\treturn map;\n" +
                "\t\t\t\t\t\t\t\t}, {})\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\tdistance: {\n" +
                "\t\t\t\t\t\t\t\tmin: 2,\n" +
                "\t\t\t\t\t\t\t\tmax: 20\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t};\n" +
                "\n" +
                "\t\t\t\t\t\tapp.config = {\n" +
                "\t\t\t\t\t\t\trotate: 90,\n" +
                "\t\t\t\t\t\t\talign: 'left',\n" +
                "\t\t\t\t\t\t\tverticalAlign: 'middle',\n" +
                "\t\t\t\t\t\t\tposition: 'insideBottom',\n" +
                "\t\t\t\t\t\t\tdistance: 15,\n" +
                "\t\t\t\t\t\t\tonChange: function () {\n" +
                "\t\t\t\t\t\t\t\tvar labelOption = {\n" +
                "\t\t\t\t\t\t\t\t\tnormal: {\n" +
                "\t\t\t\t\t\t\t\t\t\trotate: app.config.rotate,\n" +
                "\t\t\t\t\t\t\t\t\t\talign: app.config.align,\n" +
                "\t\t\t\t\t\t\t\t\t\tverticalAlign: app.config.verticalAlign,\n" +
                "\t\t\t\t\t\t\t\t\t\tposition: app.config.position,\n" +
                "\t\t\t\t\t\t\t\t\t\tdistance: app.config.distance\n" +
                "\t\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t\t};\n" +
                "\t\t\t\t\t\t\t\tmyChart.setOption({\n" +
                "\t\t\t\t\t\t\t\t\tseries: [{\n" +
                "\t\t\t\t\t\t\t\t\t\tlabel: labelOption\n" +
                "\t\t\t\t\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\t\t\t\tlabel: labelOption\n" +
                "\t\t\t\t\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\t\t\t\tlabel: labelOption\n" +
                "\t\t\t\t\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\t\t\t\tlabel: labelOption\n" +
                "\t\t\t\t\t\t\t\t\t}]\n" +
                "\t\t\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t};\n" +
                "\n" +
                "\n" +
                "\t\t\t\t\t\tvar labelOption = {\n" +
                "\t\t\t\t\t\t\tnormal: {\n" +
                "\t\t\t\t\t\t\t\tshow: true,\n" +
                "\t\t\t\t\t\t\t\tposition: app.config.position,\n" +
                "\t\t\t\t\t\t\t\tdistance: app.config.distance,\n" +
                "\t\t\t\t\t\t\t\talign: app.config.align,\n" +
                "\t\t\t\t\t\t\t\tverticalAlign: app.config.verticalAlign,\n" +
                "\t\t\t\t\t\t\t\trotate: app.config.rotate,\n" +
                "\t\t\t\t\t\t\t\tformatter: '{c}  {name|{a}}',\n" +
                "\t\t\t\t\t\t\t\tfontSize: 16,\n" +
                "\t\t\t\t\t\t\t\trich: {\n" +
                "\t\t\t\t\t\t\t\t\tname: {\n" +
                "\t\t\t\t\t\t\t\t\t\ttextBorderColor: '#fff'\n" +
                "\t\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t};\n" +
                "\n" +
                "\t\t\t\t\t\toption = {\n" +
                "\t\t\t\t\t\t\tcolor: ['LimeGreen', 'red', 'orange'],\n" +
                "\t\t\t\t\t\t\ttooltip: {\n" +
                "\t\t\t\t\t\t\t\ttrigger: 'axis',\n" +
                "\t\t\t\t\t\t\t\taxisPointer: {\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'shadow'\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\tlegend: {\n" +
                "\t\t\t\t\t\t\t\tdata: ['PASS', 'FAIL', 'OTHER']\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\ttoolbox: {\n" +
                "\t\t\t\t\t\t\t\tshow: true,\n" +
                "\t\t\t\t\t\t\t\torient: 'vertical',\n" +
                "\t\t\t\t\t\t\t\tleft: 'right',\n" +
                "\t\t\t\t\t\t\t\ttop: 'center',\n" +
                "\t\t\t\t\t\t\t\tfeature: {\n" +
                "\t\t\t\t\t\t\t\t\tmark: {show: true},\n" +
                "\t\t\t\t\t\t\t\t\tdataView: {show: true, readOnly: false},\n" +
                "\t\t\t\t\t\t\t\t\tmagicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},\n" +
                "\t\t\t\t\t\t\t\t\trestore: {show: true},\n" +
                "\t\t\t\t\t\t\t\t\tsaveAsImage: {show: true}\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\tcalculable: true,\n" +
                "\t\t\t\t\t\t\txAxis: [\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'category',\n" +
                "\t\t\t\t\t\t\t\t\taxisTick: {show: false},\n" +
                "\t\t\t\t\t\t\t\t\tdata: ["+this.addapi(API)+"]\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t],\n" +
                "\t\t\t\t\t\t\tyAxis: [\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'value'\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t],\n" +
                "\t\t\t\t\t\t\tseries: [\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\tname: 'PASS',\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'bar',\n" +
                "\t\t\t\t\t\t\t\t\tbarGap: 0,\n" +
                "\t\t\t\t\t\t\t\t\tlabel: labelOption,\n" +
                "\t\t\t\t\t\t\t\t\tdata: ["+this.adddata(pass)+"]\n" +
                "\t\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\tname: 'FAIL',\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'bar',\n" +
                "\t\t\t\t\t\t\t\t\tlabel: labelOption,\n" +
                "\t\t\t\t\t\t\t\t\tdata: ["+this.adddata(fail)+"]\n" +
                "\t\t\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\t\tname: 'OTHER',\n" +
                "\t\t\t\t\t\t\t\t\ttype: 'bar',\n" +
                "\t\t\t\t\t\t\t\t\tlabel: labelOption,\n" +
                "\t\t\t\t\t\t\t\t\tdata: [" +this.adddata(other)+"]\n" +
                "\t\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t\t]\n" +
                "\t\t\t\t\t\t};\n" +
                "\t\t\t\t\t\t// 使用刚指定的配置项和数据显示图表。\n" +
                "\t\t\t\t\t\tapp.setOption(option);\n" +
                "\t\t\t\t\t</script>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</table>\n" +
                "\t\t</div>\n" +
                "\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
    }

    //生成一个html文件，并写入报告内容
    public void WriteHtml() {
        //获取报告存放文件夹，判断是否存在
        File htmlReportDir = new File(path + "/TestHtmlReport");
        if (htmlReportDir.exists() == false) {
            htmlReportDir.mkdirs();
        }
        String reportName = "API" + MyData.format(MyData.time8) + ".html";
        reportPath = htmlReportDir + "/" + reportName;
        //生成html报告文件，并判断是否已存在
        File report = new File(reportPath);
        if (report.exists() == false) {
            try {
                report.createNewFile();
                String msg = my.toString();
                Files.write((Paths.get(reportPath)),msg.getBytes("utf-8"), StandardOpenOption.APPEND);
            } catch (IOException e) {
                logger.error("生成HTML失败");
            }
        }
    }

    //添加接口名称
    public String addapi(String[] api){
        String text="";
        for(int i=0;i<api.length;i++){
            if(i==(api.length-1)){
                text=text+"'"+api[i]+"'";
            }else{
                text=text+"'"+api[i]+"',";
            }
        }
        return text;
    }

    //添加数据结果
    public String adddata(String[] data){
        String text="";
        for(int i=0;i<data.length;i++){
            if(i==(data.length-1)){
                text=text+data[i];
            }else{
                text=text+data[i]+",";
            }
        }
        return text;
    }

}
