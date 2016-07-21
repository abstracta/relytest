/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
