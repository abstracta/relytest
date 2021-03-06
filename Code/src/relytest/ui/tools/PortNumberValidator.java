/*
 * Copyright (C) 2016 MS
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
package relytest.ui.tools;

import java.util.regex.Pattern;

/**
 *
 * @author MS
 */
public class PortNumberValidator implements IValidator {

    Pattern pattern = Pattern.compile(".*[^0-9].*");

    @Override
    public boolean validate(String value) {
        if (value=="" || pattern.matcher(value).matches()) {
            return false;
        }
        int port;
        try{
         port= Integer.parseInt(value);}
        catch(NumberFormatException e){
            port = Integer.MAX_VALUE;
        }
        return (port < 65336);
    }

}
