package com.krishna.results;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ResultList {

    @Getter
    private List<String> results;

    public static ResultList INSTANCE = new ResultList();
    public ResultList(){
        this.results = new ArrayList<>();
    }
}
