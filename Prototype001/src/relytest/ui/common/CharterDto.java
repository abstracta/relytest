/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.common;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class CharterDto {
     private String name;
     private String charterFileName;
     private String folderNamePath;
     private String picturePath;
     private String pathHtml;
     private String totalTime;
     private String folderName;
     private GroupNote[] groupNotes;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the groupNotes
     */
    public GroupNote[] getGroupNotes() {
        return groupNotes;
    }

    /**
     * @param groupNotes the groupNotes to set
     */
    public void setGroupNotes(GroupNote[] groupNotes) {
        this.groupNotes = groupNotes;
    }

    /**
     * @return pathHtmlpath
     */
    public String getPathHtml() {
       return pathHtml;
    }

    /*pathHtml   * @parpathHtmlath the path to set
    setPathHtmlpathHtmllic void setPath(StrpathHtmlpath)pathHtml       this.path = path;
    }

    /**
     * @return the charterFileName
     */
    public String getCharterFileName() {
        return charterFileName;
    }

    /**
     * @param charterFileName the charterFileName to set
     */
    public void setCharterFileName(String charterFileName) {
        this.charterFileName = charterFileName;
    }

    /**
     * @return the totalTime
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @param pathHtml the pathHtml to set
     */
    public void setPathHtml(String pathHtml) {
        this.pathHtml = pathHtml;
    }

    /**
     * @return the folderNamePath
     */
    public String getFolderNamePath() {
        return folderNamePath;
    }

    /**
     * @param folderNamePath the folderNamePath to set
     */
    public void setFolderNamePath(String folderNamePath) {
        this.folderNamePath = folderNamePath;
    }

    /**
     * @return the folderName
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * @param folderName the folderName to set
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * @return the picturePath
     */
    public String getPicturePath() {
        return picturePath;
    }

    /**
     * @param picturePath the picturePath to set
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
