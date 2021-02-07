package com.krishna.results;

import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ResultList {

    @Getter
    private Queue<String> results;

    public static ResultList INSTANCE = new ResultList();
    public ResultList(){
        this.results = new ConcurrentLinkedQueue<String>();
    }
}
