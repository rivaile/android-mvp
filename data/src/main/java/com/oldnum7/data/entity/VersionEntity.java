package com.oldnum7.data.entity;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/10/19/18:01
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class VersionEntity {

    private String downloadLink; // 客户端下载链接
    private String imgPath; // 闪屏页图片路径
    private String link; // 闪屏页跳转链接
    private String updateContent; // 升级文案
    private String updateStatus; // 升级状态 0.无 1.正常升级 2.强制升级

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    @Override
    public String toString() {
        return "VersionEntity{" +
                "downloadLink='" + downloadLink + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", link='" + link + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", updateStatus='" + updateStatus + '\'' +
                '}';
    }
}
