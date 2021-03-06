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

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import relytest.ui.Constants;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class Note {

    private long id;
    private String text;
    private String textHtml;
    private String timeStamp;
    private String label;

    public Note(long idNew, String textNew, String timeStampNew, String aLabel) {
        text = textNew;
        timeStamp = timeStampNew;
        id = idNew;
        label = aLabel;
    }

    public Note() {

    }
    
    public boolean canBeRemoved(){
       return ( (getLabel()==Constants.LABEL_NOTE) || (getLabel()==Constants.LABEL_BUG)
                            ||(getLabel()==Constants.LABEL_RISK ) ||(getLabel()==Constants.LABEL_PROBLEM )
                                ||(getLabel()==Constants.LABEL_ToDo )
                            );
    }

     @Override
    public boolean equals(Object o) {
        if (!(o instanceof Note)) {
            return false;
        }
        Note noteO = (Note) o;
        return this.id ==(noteO.getId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
 
    
    @Override
    public String toString() {
        return text;
    }
    private final String Separator = "<br>";

    private final int maxLabelDisplaySize = 40;

    public String toStringHtml() {
        String open = "";
        if (Constants.LABEL_PICTURE_TAKEN==label) {
            open = Separator + "<font color=\"blue\">Double click opens the picture</font>";      
        }
        
        String txt;
        if (text.length() > maxLabelDisplaySize) {

            txt = text.substring(0, maxLabelDisplaySize)+"...";
        } else {
            txt = text;
        }
        return "<html><b>Label:</b> " + label + " " + Separator
                + "<b>Id:</b> " + id + Separator
                + "<b>Text:</b> " + txtToHtml(txt) + Separator
                + "<b>Time:</b> " + timeStamp + open + "</html>";

    }

    private String txtToHtml(String s) {
        StringBuilder builder = new StringBuilder();
        boolean previousWasASpace = false;
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (previousWasASpace) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch (c) {
                case '<':
                    builder.append("&lt;");
                    break;
                case '>':
                    builder.append("&gt;");
                    break;
                case '&':
                    builder.append("&amp;");
                    break;
                case '"':
                    builder.append("&quot;");
                    break;
                case '\n':
                    builder.append("<br>");
                    break;
                // We need Tab support here, because we print StackTraces as HTML
                case '\t':
                    builder.append("&nbsp; &nbsp; &nbsp;");
                    break;
                default:
                    builder.append(c);

            }
        }
        String converted = builder.toString();
        String str = "(?i)\\b((?:https?://|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\'\".,<>?«»“”‘’]))";
        Pattern patt = Pattern.compile(str);
        Matcher matcher = patt.matcher(converted);
        converted = matcher.replaceAll("<a href=\"$1\">$1</a>");
        return converted;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
        this.setTextHtml(txtToHtml(text));
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the textHtml
     */
    public String getTextHtml() {
        return textHtml;
    }

    /**
     * @param textHtml the textHtml to set
     */
    public void setTextHtml(String textHtml) {
        this.textHtml = textHtml;
    }
}
