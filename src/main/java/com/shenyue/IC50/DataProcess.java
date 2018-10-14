package com.shenyue.IC50;

import java.util.LinkedList;
import java.util.List;

public class DataProcess {
    public DataProcess(){}
    public TableData<Double> dataProcess(List<List<Double>> table){
        List<Double> mean = new LinkedList<>();
        List<Double> sd = new LinkedList<>();
        List<Double> meanNorm = new LinkedList<>();
        List<Double> sdNorm = new LinkedList<>();
        List<Double> dense = new LinkedList<>();
        List<Double> denseLog = new LinkedList<>();

        //计算n
        int N = 0;
        for(int i=0;i < table.size();i++){
            if(!table.get(i).get(2).equals(0D)){
                N++;
            }
        }
        N--;

        //读取浓度和浓度对数
        for(int i=2;i<table.get(0).size();i++){
            dense.add(table.get(0).get(i));
            denseLog.add(Math.log10(table.get(0).get(i)));
        }

        //计算mean和sd
        for(int i=0;i < table.get(1).size();i++){
            List<Double> col = new LinkedList<>();
            for(int j=1;j < N;j++){
                col.add(table.get(j).get(i));
            }
            mean.add(getMeanAndSd(col).get(0));
            sd.add(getMeanAndSd(col).get(1));
        }
        //归一化mean和sd
        for(int i=2;i < mean.size();i++){
            meanNorm.add((mean.get(i)-mean.get(0))/(mean.get(1)-mean.get(0)));
            sdNorm.add(sd.get(i)/(mean.get(1)-mean.get(0)));
        }



        //写入结果表中
        TableData<Double> tableData = new TableData<>();
        for(int i=0;i < meanNorm.size();i++){
            if(!dense.get(i).equals(0D)){
                tableData.addRow(dense.get(i),denseLog.get(i),meanNorm.get(i),sdNorm.get(i),(double)N);
            }
        }
        return tableData;
    }

    //返回第一个值为mean,第二个值为sd
    public List<Double> getMeanAndSd(List<Double> list){
        List<Double> result = new LinkedList<>();
        double sum = 0;
        for(double value : list){
            sum += value;
        }
        //添加mean值
        double mean = sum/list.size();
        result.add(mean);
        //求sd
        sum = 0;
        for(double value : list){
            sum += (value - mean)*(value - mean);
        }
        double sd = Math.sqrt(sum/(list.size()-1));
        result.add(sd);
        return result;
    }

}
