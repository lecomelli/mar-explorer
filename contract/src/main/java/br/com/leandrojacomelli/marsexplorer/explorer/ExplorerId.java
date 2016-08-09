package br.com.leandrojacomelli.marsexplorer.explorer;

import br.com.leandrojacomelli.marsexplorer.common.GenericId;

import java.io.Serializable;
import java.util.UUID;

public class ExplorerId extends GenericId implements Serializable {


    public ExplorerId(String id) {
        super(id);
    }

    public static ExplorerId randomId() {
        return new ExplorerId(UUID.randomUUID().toString());
    }
}
