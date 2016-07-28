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

import java.io.File;
import relytest.interfaces.IWriter;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class EnvironmentStats extends Thread {

    private final String Separator = "-------------------------------------";
    private final String NewLine = "line.separator";
    private final IWriter writer;
    private String file;
    PlanificationDto planificationDto;

    public EnvironmentStats(PlanificationDto planification) {
        this.writer = new Writer();
        planificationDto = planification;
    }

    @Override
    public void run() {

        loadPlanDto();
        String str = getSOInfo();
        writer.writeToFile(getFile(), str);
    }

    private void loadPlanDto() {
        planificationDto.setOperatingSystem(System.getProperty("os.name"));
        planificationDto.setOperatingSystemArch(System.getProperty("os.arch"));
        planificationDto.setOperatingSystemVersion(System.getProperty("os.version"));

        planificationDto.setUserName(System.getProperty("user.name"));
        planificationDto.setUserLanguage(System.getProperty("user.language"));
        planificationDto.setUserTimezone(System.getProperty("user.timezone"));
        planificationDto.setUserCountry(System.getProperty("user.country"));
    }

    private String getSOInfo() {
        StringBuilder str = new StringBuilder();
        str.append(Separator).append(System.getProperty(NewLine));
        str.append("OS").append(System.getProperty(NewLine));
        str.append("OS name\t\t= ").append(System.getProperty("os.name")).append(System.getProperty(NewLine));
        str.append("OS arch\t\t= ").append(System.getProperty("os.arch")).append(System.getProperty(NewLine));
        str.append("OS version\t= ").append(System.getProperty("os.version")).append(System.getProperty(NewLine));
        str.append("User").append(System.getProperty(NewLine));
        str.append("User language\t= ").append(System.getProperty("user.language")).append(System.getProperty(NewLine));
        str.append("User name\t= ").append(System.getProperty("user.name")).append(System.getProperty(NewLine));
        str.append("User timezone\t= ").append(System.getProperty("user.timezone")).append(System.getProperty(NewLine));
        str.append("User country\t= ").append(System.getProperty("user.country")).append(System.getProperty(NewLine));
        str.append(Separator).append(System.getProperty(NewLine));
        str.append("Available Processors\t= ").append(Runtime.getRuntime().availableProcessors()).append(System.getProperty(NewLine));
        str.append("Free memory\t\t= ").append(convertBytes(Runtime.getRuntime().freeMemory())).append(System.getProperty(NewLine));
        str.append("Total memory available\t= ").append(convertBytes(Runtime.getRuntime().totalMemory())).append(System.getProperty(NewLine));
        str.append(Separator).append(System.getProperty(NewLine));
        File[] roots = File.listRoots();
        for (File root : roots) {
            str.append("File system root \t= ").append(root.getAbsolutePath()).append(System.getProperty(NewLine));
            str.append("Total space\t\t= ").append(convertBytes(root.getTotalSpace())).append(System.getProperty(NewLine));
            str.append("Free space\t\t= ").append(convertBytes(root.getFreeSpace())).append(System.getProperty(NewLine));
            str.append("Usable space\t\t= ").append(convertBytes(root.getUsableSpace())).append(System.getProperty(NewLine));
        }
        str.append(Separator).append(System.getProperty(NewLine));
        return str.toString();
    }

    private String convertBytes(long sizeBytes) {
        if (sizeBytes > (1024 * 1024 * 1024)) {
            return "" + sizeBytes / (1024 * 1024 * 1024) + " GB";
        } else if (sizeBytes > (1024 * 1024)) {
            return "" + sizeBytes / (1024 * 1024) + " MB";
        } else if (sizeBytes > 1024) {
            return "" + sizeBytes / (1024) + " KB";
        } else {
            return "" + sizeBytes + " B";
        }
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }
}
