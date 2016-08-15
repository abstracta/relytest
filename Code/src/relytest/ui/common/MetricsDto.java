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
public class MetricsDto {
    private String focusOnCharter;
    private String timeSpentConfiguration;
    private String timeSpentReportingBugs;
    private String timeSpentTesting;
    private String feelUsingSystemUndeTest;
    private String navegability;
    private int numberSessionsNeeded;

    /**
     * @return the focusOnCharter
     */
    public String getFocusOnCharter() {
        return focusOnCharter;
    }

    /**
     * @param focusOnCharter the focusOnCharter to set
     */
    public void setFocusOnCharter(String focusOnCharter) {
        this.focusOnCharter = focusOnCharter;
    }

    /**
     * @return the timeSpentConfiguration
     */
    public String getTimeSpentConfiguration() {
        return timeSpentConfiguration;
    }

    /**
     * @param timeSpentConfiguration the timeSpentConfiguration to set
     */
    public void setTimeSpentConfiguration(String timeSpentConfiguration) {
        this.timeSpentConfiguration = timeSpentConfiguration;
    }

    /**
     * @return the timeSpentReportingBugs
     */
    public String getTimeSpentReportingBugs() {
        return timeSpentReportingBugs;
    }

    /**
     * @param timeSpentReportingBugs the timeSpentReportingBugs to set
     */
    public void setTimeSpentReportingBugs(String timeSpentReportingBugs) {
        this.timeSpentReportingBugs = timeSpentReportingBugs;
    }

    /**
     * @return the timeSpentTesting
     */
    public String getTimeSpentTesting() {
        return timeSpentTesting;
    }

    /**
     * @param timeSpentTesting the timeSpentTesting to set
     */
    public void setTimeSpentTesting(String timeSpentTesting) {
        this.timeSpentTesting = timeSpentTesting;
    }

    /**
     * @return the feelUsingSystemUndeTest
     */
    public String getFeelUsingSystemUndeTest() {
        return feelUsingSystemUndeTest;
    }

    /**
     * @param feelUsingSystemUndeTest the feelUsingSystemUndeTest to set
     */
    public void setFeelUsingSystemUndeTest(String feelUsingSystemUndeTest) {
        this.feelUsingSystemUndeTest = feelUsingSystemUndeTest;
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
     * @return the numberSessionsNeeded
     */
    public int getNumberSessionsNeeded() {
        return numberSessionsNeeded;
    }

    /**
     * @param numberSessionsNeeded the numberSessionsNeeded to set
     */
    public void setNumberSessionsNeeded(int numberSessionsNeeded) {
        this.numberSessionsNeeded = numberSessionsNeeded;
    }
}
