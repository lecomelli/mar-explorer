package br.com.leandrojacomelli.marsexplorer.explorer.controller.dto;

import javax.validation.constraints.NotNull;

public class LandExplorerRequest {

    @NotNull
    private CoordinateDto lowerBoundary;

    @NotNull
    private CoordinateDto upperBoundary;

    @NotNull
    private CoordinateDto location;

    @NotNull
    private String direction;

    public CoordinateDto getLowerBoundary() {
        return lowerBoundary;
    }

    public void setLowerBoundary(CoordinateDto lowerBoundary) {
        this.lowerBoundary = lowerBoundary;
    }

    public CoordinateDto getUpperBoundary() {
        return upperBoundary;
    }

    public void setUpperBoundary(CoordinateDto upperBoundary) {
        this.upperBoundary = upperBoundary;
    }

    public CoordinateDto getLocation() {
        return location;
    }

    public void setLocation(CoordinateDto location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
