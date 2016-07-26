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

/**
 *
 * @author Gabriela Sanchez - Miguel Sanchez
 */
public class DetailsDto {

    private PlanificationDto planification;
    private ExecutionDto execution;
    private MetricsDto metrics;

    public DetailsDto() {
        planification = new PlanificationDto();
        execution = new ExecutionDto();
        metrics=new MetricsDto();
    }

    /**
     * @return the planification
     */
    public PlanificationDto getPlanification() {
        return planification;
    }

    /**
     * @param planification the planification to set
     */
    public void setPlanification(PlanificationDto planification) {
        this.planification = planification;
    }

    /**
     * @return the execution
     */
    public ExecutionDto getExecution() {
        return execution;
    }

    /**
     * @param execution the execution to set
     */
    public void setExecution(ExecutionDto execution) {
        this.execution = execution;
    }

    /**
     * @return the metrics
     */
    public MetricsDto getMetrics() {
        return metrics;
    }

    /**
     * @param metrics the metrics to set
     */
    public void setMetrics(MetricsDto metrics) {
        this.metrics = metrics;
    }
}
