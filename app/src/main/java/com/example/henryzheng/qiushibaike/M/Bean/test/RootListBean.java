/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.test;
import java.util.List;

/**
 * Auto-generated: 2017-01-12 10:31:50
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class RootListBean {

    private int count;
    private List<Items> items;
    private int total;
    private int page;
    private int err;
    private int refresh;
    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setItems(List<Items> items) {
         this.items = items;
     }
     public List<Items> getItems() {
         return items;
     }

    public void setTotal(int total) {
         this.total = total;
     }
     public int getTotal() {
         return total;
     }

    public void setPage(int page) {
         this.page = page;
     }
     public int getPage() {
         return page;
     }

    public void setErr(int err) {
         this.err = err;
     }
     public int getErr() {
         return err;
     }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }
}