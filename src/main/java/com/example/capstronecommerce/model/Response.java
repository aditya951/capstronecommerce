package com.example.capstronecommerce.model;

import java.util.List;

public class Response {

    List<Product> list;

    public Response(List<Product> list) {
        this.list = list;
    }

    public Response() {
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
