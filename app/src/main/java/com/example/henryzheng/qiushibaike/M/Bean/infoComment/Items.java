/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.infoComment;
/**
 * Auto-generated: 2017-01-20 11:17:40
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Items {

    private   String content;
    private int parent_id;
    private boolean liked;
    private String like_count;
    private At_Infos at_infos;
    private String floor;
    private String created_at;
    private int id;
    private User user;
    public Refer refer;
    public void setContent(String content) {
         this.content = content;
     }
     public  String getContent() {
         return content;
     }

    public void setParentId(int parentId) {
         this.parent_id = parentId;
     }
     public int getParentId() {
         return parent_id;
     }

    public void setLiked(boolean liked) {
         this.liked = liked;
     }
     public boolean getLiked() {
         return liked;
     }

    public void setLikeCount(String likeCount) {
         this.like_count = likeCount;
     }
     public String getLikeCount() {
         return like_count;
     }

    public void setAtInfos(At_Infos atInfos) {
         this.at_infos = atInfos;
     }
     public At_Infos getAtInfos() {
         return at_infos;
     }

    public void setFloor(String floor) {
         this.floor = floor;
     }
     public String getFloor() {
         return floor;
     }

    public void setCreatedAt(String createdAt) {
         this.created_at = createdAt;
     }
     public String getCreatedAt() {
         return created_at;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setUser(User user) {
         this.user = user;
     }
     public User getUser() {
         return user;
     }

    public Refer getRefer() {
        return refer;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }


}