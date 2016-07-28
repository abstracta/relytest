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
package relytest.ui;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class Constants {

    public static final String DATE_TIME_FORMAT = "yyyy.MM.dd_HH.mm.ss";
    public static final String DATE_TIME_FORMAT_LOG = "yyyy.MM.dd_HH:mm:ss";
    public static final String KEY_OPEN_IMAGE_EDITOR = "OpenImageEditor";
    public static final String KEY_HIDE_RELY_TEST = "HideRelyTest";
    public static final String KEY_TAKE_PICTURE_AFTER_BUG = "TakePictureAfterBug";
    public static final String KEY_NAME = "Name";
    public static final String KEY_LOOK_AND_FEEL = "lookAndFeel";
    public static final String KEY_SHORT_TIME = "ShortTime";
    public static final String KEY_LONG_TIME = "LongTime";
    public static final String KEY_MEDIUM_TIME = "MediumTime";
    public static final String KEY_PAINT_APP = "PaintApp";
    
    public static final String KEY_CONFIRM_STOP_CHARTER = "ConfirmStopCharter";
    public static final String KEY_CONFIRM_EXIT_RELYTEST = "ConfirmExitRelyTest";
    public static final String KEY_LAUNCH_BROWSER= "LaunchBrowserAfterCharterEnds";

    public static final String KEY_BUTTON_NOTE = "Note";
    public static final String KEY_BUTTON_BUG = "Bug";
    public static final String KEY_BUTTON_TODO = "ToDo";
    public static final String KEY_BUTTON_RISK = "Risk";
    public static final String KEY_BUTTON_PROBLEM = "Problem";

    /** LABELS **/
        public static final String LABEL_NOTE = "Note";
    public static  final String LABEL_BUG = "Bug";
    public static  final String LABEL_ToDo = "ToDo";
    public static  final String LABEL_RISK = "Risk";
    public static  final String LABEL_PROBLEM = "Problem";
    public static  final String LABEL_PICTURE_TAKEN = "Picture Taken";
    public static  final String LABEL_SESSION_STARTED = "Session Started";
    public static  final String LABEL_SESSION_FINISHED="Session Finished";
    public static  final String LABEL_EVENT="Event";
    public static  final String LABEL_PAUSED="Paused";
    public static  final String LABEL_CONTINUE="Continue";
    
    /* Look and Feel */
    public static final int LAF_ACRYL = 0;
    public static final int LAF_AERO = 1;
    public static final int LAF_ALUMINIUM = 2;
    public static final int LAF_BERNSTEIN = 3;
    public static final int LAF_FAST = 4;
    public static final int LAF_GRAPHITE = 5;
    public static final int LAF_HIFI = 6;
    public static final int LAF_LUNA = 7;
    public static final int LAF_MCWIN = 8;
    public static final int LAF_MINT = 9;
    public static final int LAF_NOIRE = 10;
    public static final int LAF_SMART = 11;
    public static final int LAF_TEXTURE = 12;

    // The possible look and feels
    public static final String[] LAF_NAMES = new String[]{"Acryl", "Aero", "Aluminium", "Bernstein", "Fast", "Graphite", "HiFi", "Luna", "McWin", "Mint", "Noire", "Smart", "Texture"};

    public static final String HTML_TEMPLATE_FILE ="relyTest.ftl";
}
