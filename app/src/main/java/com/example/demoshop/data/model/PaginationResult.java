package com.example.demoshop.data.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class PaginationResult<T> implements Serializable {

    @Expose
    public int count;

    @Expose
    Integer previous;

    @Expose
    public Integer next;

    @Expose
    public List<T> resutls;
}
