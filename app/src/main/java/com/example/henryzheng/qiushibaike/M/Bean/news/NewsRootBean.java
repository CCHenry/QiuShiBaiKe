/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.news;
import java.util.List;
/**
 * Auto-generated: 2017-01-18 11:33:19
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class NewsRootBean {

    private boolean has_more;
    private List<Banners> banners;
    private List<Data> data;
    private int err;
    public void setHasMore(boolean hasMore) {
         this.has_more = hasMore;
     }
     public boolean getHasMore() {
         return has_more;
     }

    public void setBanners(List<Banners> banners) {
         this.banners = banners;
     }
     public List<Banners> getBanners() {
         return banners;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setErr(int err) {
         this.err = err;
     }
     public int getErr() {
         return err;
     }

}