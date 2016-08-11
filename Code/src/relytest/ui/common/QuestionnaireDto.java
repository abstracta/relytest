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
public class QuestionnaireDto {
    private int focusOnCharter;
    private String feelUsingRelyTest;
    private String navegability;
    
    private int timeOnConfiguration;
    private int timeOnBugReport;
    private int timeOnTesting;

    /**
     * @return the focusOnCharter
     */
    public int getFocusOnCharter() {
        return focusOnCharter;
    }

    /**
     * @param focusOnCharter the focusOnCharter to set
     */
    public void setFocusOnCharter(int focusOnCharter) {
        this.focusOnCharter = focusOnCharter;
    }

    /**
     * @return the feelUsingRelyTest
     */
    public String getFeelUsingRelyTest() {
        return feelUsingRelyTest;
    }

    /**
     * @param feelUsingRelyTest the feelUsingRelyTest to set
     */
    public void setFeelUsingRelyTest(String feelUsingRelyTest) {
        this.feelUsingRelyTest = feelUsingRelyTest;
    }

    /**
     * @return the navegability
     */
    public String getNavegability() {
        return navegability;
    }

    /**
     * @param navegability the navegability to set
     */
    public void setNavegability(String navegability) {
        this.navegability = navegability;
    }

    /**
     * @return the timeOnConfiguration
     */
    public int getTimeOnConfiguration() {
        return timeOnConfiguration;
    }

    /**
     * @param timeOnConfiguration the timeOnConfiguration to set
     */
    public void setTimeOnConfiguration(int timeOnConfiguration) {
        this.timeOnConfiguration = timeOnConfiguration;
    }

    /**
     * @return the timeOnBugReport
     */
    public int getTimeOnBugReport() {
        return timeOnBugReport;
    }

    /**
     * @param timeOnBugReport the timeOnBugReport to set
     */
    public void setTimeOnBugReport(int timeOnBugReport) {
        this.timeOnBugReport = timeOnBugReport;
    }

    /**
     * @return the timeOnTesting
     */
    public int getTimeOnTesting() {
        return timeOnTesting;
    }

    /**
     * @param timeOnTesting the timeOnTesting to set
     */
    public void setTimeOnTesting(int timeOnTesting) {
        this.timeOnTesting = timeOnTesting;
    }
}
