package com.shenyue.IC50;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TableData<T> {

    public TableData(){
        this.table = new LinkedList<>();
    }

    private List<Map<String,T>> table;

    public void addRow(T col1,T col2){
        HashMap<String,T> map = new HashMap<>();
        map.put("col1",col1);
        map.put("col2",col2);
        this.table.add(map);
    }

    public List<Map<String,T>> getTable(){
        return table;
    }

}
