/*
 * Copyright (C) 2016 Gabriela Sanchez - Miguel Sanchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package relytest.ui.common;

import java.util.ArrayList;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class CharterDto {

    public CharterDto(String name) {
        this.name = name;
        details=new DetailsDto();
    }
     private String name;
     private String charterFileName;
     private String folderNamePath;
     private String picturePath;
     private String pathHtml;
     private String totalTime;
     private String folderName;
     private String startTime;
     private GroupNote[] groupNotes;
     private ArrayList<Note> notesTaken;
     private DetailsDto details;

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

    /**
     * @return the notesTaken
     */
    public ArrayList<Note> getNotesTaken() {
        return notesTaken;
    }

    /**
     * @param notesTaken the notesTaken to set
     */
    public void setNotesTaken(ArrayList<Note> notesTaken) {
        this.notesTaken = notesTaken;
    }

    /**
     * @return the details
     */
    public DetailsDto getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(DetailsDto details) {
        this.details = details;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
