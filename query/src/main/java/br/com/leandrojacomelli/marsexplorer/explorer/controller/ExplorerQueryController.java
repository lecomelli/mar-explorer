package br.com.leandrojacomelli.marsexplorer.explorer.controller;

import br.com.leandrojacomelli.marsexplorer.common.controller.BaseController;
import br.com.leandrojacomelli.marsexplorer.explorer.ExplorerId;
import br.com.leandrojacomelli.marsexplorer.explorer.query.ExplorerProjection;
import br.com.leandrojacomelli.marsexplorer.explorer.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/query")
public class ExplorerQueryController extends BaseController {

    private final QueryService queryService;

    @Autowired
    public ExplorerQueryController(QueryService queryService) {
        this.queryService = queryService;
    }


    @RequestMapping(value = "/explores",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ExplorerProjection>> listExplores() {
        return ResponseEntity.ok(queryService.getExplores());
    }

    @RequestMapping(value = "/explorer/{id}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ExplorerProjection> getExplorer(@PathVariable("id") String id) {
        return ResponseEntity.ok(queryService.getExplorer(new ExplorerId(id)));
    }


}
