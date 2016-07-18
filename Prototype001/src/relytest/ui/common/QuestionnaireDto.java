/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relytest.ui.common;

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class QuestionnaireDto {
    private int focusOnCharter;
    private String feelUsingRelyTest;
    private String navegability;

    /**
     * @return the focusOnCharter
     */
    public int getFocusOnCharter() {
        return focusOnCharter;
    }

    /**
     * @param focusOnCharter the focusOnCharter to set
     */
    public void setFocusOnCharter(int focusOnCharter) {
        this.focusOnCharter = focusOnCharter;
    }

    /**
     * @return the feelUsingRelyTest
     */
    public String getFeelUsingRelyTest() {
        return feelUsingRelyTest;
    }

    /**
     * @param feelUsingRelyTest the feelUsingRelyTest to set
     */
    public void setFeelUsingRelyTest(String feelUsingRelyTest) {
        this.feelUsingRelyTest = feelUsingRelyTest;
    }

    /**
     * @return the navegability
     */
    public String getNavegability() {
        return navegability;
    }

    /**
     * @param navegability the navegability to set
     */
    public void setNavegability(String navegability) {
        this.navegability = navegability;
    }
}
