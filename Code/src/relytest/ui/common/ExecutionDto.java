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

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class ExecutionDto {
     private String realDuration;
     private int numberOfBugs;
     private int numberOfRisks;
     private int numberOfNotes;
     private int numberOfProblems;
     private int numberOfToDo;

    /**
     * @return the realDuration
     */
    public String getRealDuration() {
        return realDuration;
    }

    /**
     * @param realDuration the realDuration to set
     */
    public void setRealDuration(String realDuration) {
        this.realDuration = realDuration;
    }

    /**
     * @return the numberOfBugs
     */
    public int getNumberOfBugs() {
        return numberOfBugs;
    }

    /**
     * @param numberOfBugs the numberOfBugs to set
     */
    public void setNumberOfBugs(int numberOfBugs) {
        this.numberOfBugs = numberOfBugs;
    }

    /**
     * @return the numberOfRisks
     */
    public int getNumberOfRisks() {
        return numberOfRisks;
    }

    /**
     * @param numberOfRisks the numberOfRisks to set
     */
    public void setNumberOfRisks(int numberOfRisks) {
        this.numberOfRisks = numberOfRisks;
    }

    /**
     * @return the numberOfNotes
     */
    public int getNumberOfNotes() {
        return numberOfNotes;
    }

    /**
     * @param numberOfNotes the numberOfNotes to set
     */
    public void setNumberOfNotes(int numberOfNotes) {
        this.numberOfNotes = numberOfNotes;
    }

    /**
     * @return the numberOfProblems
     */
    public int getNumberOfProblems() {
        return numberOfProblems;
    }

    /**
     * @param numberOfProblems the numberOfProblems to set
     */
    public void setNumberOfProblems(int numberOfProblems) {
        this.numberOfProblems = numberOfProblems;
    }

    /**
     * @return the numberOfToDo
     */
    public int getNumberOfToDo() {
        return numberOfToDo;
    }

    /**
     * @param numberOfToDo the numberOfToDo to set
     */
    public void setNumberOfToDo(int numberOfToDo) {
        this.numberOfToDo = numberOfToDo;
    }
}
