/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.Bean.text;
import java.util.List;

/**
 * Auto-generated: 2017-01-18 14:57:55
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class TextRootBean {

    private int count;
    private int err;
    private List<Items> items;
    private int total;
    private int page;
    private int refresh;
    public void setCount(int count) {
         this.count = count;
     }
     public int getCount() {
         return count;
     }

    public void setErr(int err) {
         this.err = err;
     }
     public int getErr() {
         return err;
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

    public void setRefresh(int refresh) {
         this.refresh = refresh;
     }
     public int getRefresh() {
         return refresh;
     }

}