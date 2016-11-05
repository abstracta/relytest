/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package relytest.common.dto;

import java.util.List;

/**
 *
 * @author MS
 */
public class CharterCollection {
    private List<Charter> charters;

    /**
     * @return the charters
     */
    public List<Charter> getCharters() {
        return charters;
    }

    /**
     * @param charters the charters to set
     */
    public void setCharters(List<Charter> charters) {
        this.charters = charters;
    }
}
