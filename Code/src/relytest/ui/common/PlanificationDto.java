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
public class PlanificationDto {

    private String tester;
    private String duration;
    private String operatingSystem;
    private String operatingSystemArch;
    private String operatingSystemVersion;
    private String browser;

    private String userName;
    private String userLanguage;
    private String userTimezone;
    private String userCountry;

    /**
     * @return the tester
     */
    public String getTester() {
        return tester;
    }

    /**
     * @param tester the tester to set
     */
    public void setTester(String tester) {
        this.tester = tester;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return the operatingSystem
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * @param operatingSystem the operatingSystem to set
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return the browser
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser the browser to set
     */
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**
     * @return the operatingSystemArch
     */
    public String getOperatingSystemArch() {
        return operatingSystemArch;
    }

    /**
     * @param operatingSystemArch the operatingSystemArch to set
     */
    public void setOperatingSystemArch(String operatingSystemArch) {
        this.operatingSystemArch = operatingSystemArch;
    }

    /**
     * @return the operatingSystemVersion
     */
    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    /**
     * @param operatingSystemVersion the operatingSystemVersion to set
     */
    public void setOperatingSystemVersion(String operatingSystemVersion) {
        this.operatingSystemVersion = operatingSystemVersion;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userLanguage
     */
    public String getUserLanguage() {
        return userLanguage;
    }

    /**
     * @param userLanguage the userLanguage to set
     */
    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    /**
     * @return the userTimezone
     */
    public String getUserTimezone() {
        return userTimezone;
    }

    /**
     * @param userTimezone the userTimezone to set
     */
    public void setUserTimezone(String userTimezone) {
        this.userTimezone = userTimezone;
    }

    /**
     * @return the userCountry
     */
    public String getUserCountry() {
        return userCountry;
    }

    /**
     * @param userCountry the userCountry to set
     */
    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }
}
