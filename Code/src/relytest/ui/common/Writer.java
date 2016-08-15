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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import relytest.interfaces.IWriter;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class Writer implements IWriter {

    @Override
    public boolean writeToFile(String file, String text) {
        text = text.replaceAll("(?!\\r)\\n", "\r\n");
        boolean ok = false;
        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(text);
            ok=true;
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return ok;
    }
}
