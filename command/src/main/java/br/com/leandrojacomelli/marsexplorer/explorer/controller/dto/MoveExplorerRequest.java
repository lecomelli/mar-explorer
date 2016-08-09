package br.com.leandrojacomelli.marsexplorer.explorer.controller.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

import static br.com.leandrojacomelli.marsexplorer.common.GenericId.ID_PATTERN;

public class MoveExplorerRequest {

    @NotEmpty
    @Pattern(regexp = ID_PATTERN)
    private String explorerId;

    @NotNull
    @NotEmpty
    private List<String> movements;

    public String getExplorerId() {
        return explorerId;
    }

    public void setExplorerId(String explorerId) {
        this.explorerId = explorerId;
    }

    public List<String> getMovements() {
        return movements;
    }

    public void setMovements(List<String> movements) {
        this.movements = movements;
    }
}
