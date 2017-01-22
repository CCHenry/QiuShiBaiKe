/**
  * Copyright 2017 aTool.org 
  */
package com.example.henryzheng.qiushibaike.M.bean.news;
import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-01-18 11:33:19
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Data implements Serializable{

    private int status;
    private int view_count;
    private String description;
    private String title;
    private int participate_count;
    private String created_at;
    private String web_link;
    private List<String> covers;
    private String source;
    private int comment_count;
    private int down_count;
    private int topic_id;
    private int type;
    private int id;
    private String share_link;
    private int up_count;
    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setViewCount(int viewCount) {
         this.view_count = viewCount;
     }
     public int getViewCount() {
         return view_count;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setParticipateCount(int participateCount) {
         this.participate_count = participateCount;
     }
     public int getParticipateCount() {
         return participate_count;
     }

    public void setCreatedAt(String createdAt) {
         this.created_at = createdAt;
     }
     public String getCreatedAt() {
         return created_at;
     }

    public void setWebLink(String webLink) {
         this.web_link = webLink;
     }
     public String getWebLink() {
         return web_link;
     }

    public void setCovers(List<String> covers) {
         this.covers = covers;
     }
     public List<String> getCovers() {
         return covers;
     }

    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    public void setCommentCount(int commentCount) {
         this.comment_count = commentCount;
     }
     public int getCommentCount() {
         return comment_count;
     }

    public void setDownCount(int downCount) {
         this.down_count = downCount;
     }
     public int getDownCount() {
         return down_count;
     }

    public void setTopicId(int topicId) {
         this.topic_id = topicId;
     }
     public int getTopicId() {
         return topic_id;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setShareLink(String shareLink) {
         this.share_link = shareLink;
     }
     public String getShareLink() {
         return share_link;
     }

    public void setUpCount(int upCount) {
         this.up_count = upCount;
     }
     public int getUpCount() {
         return up_count;
     }

}