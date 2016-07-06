/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.common;

import java.util.ArrayList;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class GroupNote {
    private String label;
    public ArrayList<Note> notes = new ArrayList<>();
    
    public GroupNote(String labelName){
        label=labelName;
    }
    public void addNote(Note note){
        notes.add(note);
    }
    
}
