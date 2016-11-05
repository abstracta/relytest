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
public class TesterCollection {
    private List<Tester> testers;

    /**
     * @return the testers
     */
    public List<Tester> getTesters() {
        return testers;
    }

    /**
     * @param testers the testers to set
     */
    public void setTesters(List<Tester> testers) {
        this.testers = testers;
    }

}
