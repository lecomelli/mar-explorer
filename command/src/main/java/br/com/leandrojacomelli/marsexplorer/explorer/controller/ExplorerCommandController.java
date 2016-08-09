package br.com.leandrojacomelli.marsexplorer.explorer.controller;

import br.com.leandrojacomelli.marsexplorer.common.command.CommandBus;
import br.com.leandrojacomelli.marsexplorer.common.controller.BaseController;
import br.com.leandrojacomelli.marsexplorer.common.exception.ExceptionMessage;
import br.com.leandrojacomelli.marsexplorer.explorer.command.ExplorerCommandFactory;
import br.com.leandrojacomelli.marsexplorer.explorer.command.LandExplorerCommand;
import br.com.leandrojacomelli.marsexplorer.explorer.command.MoveExplorerCommand;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.LandExplorerRequest;
import br.com.leandrojacomelli.marsexplorer.explorer.controller.dto.MoveExplorerRequest;
import br.com.leandrojacomelli.marsexplorer.explorer.domain.exception.ExplorerSetupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;

@RestController
@RequestMapping("/command")
public class ExplorerCommandController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(ExplorerCommandController.class);

    private final ExplorerCommandFactory explorerCommandFactory;


    private final CommandBus command;

    @Autowired
    public ExplorerCommandController(ExplorerCommandFactory explorerCommandFactory, CommandBus command) {
        this.explorerCommandFactory = explorerCommandFactory;
        this.command = command;
    }


    @RequestMapping(value = "/land-explorer",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity landExplorer(@Validated @RequestBody final LandExplorerRequest request) {
        LandExplorerCommand landExplorerCommand = explorerCommandFactory.toCommand(request);
        command.dispatch(landExplorerCommand);
        return ResponseEntity.ok(landExplorerCommand.getExplorerId());
    }

    @RequestMapping(value = "/move-explorer",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity moveExplorer(@Validated @RequestBody final MoveExplorerRequest request) {
        MoveExplorerCommand landExplorerCommand = explorerCommandFactory.toCommand(request);
        command.dispatch(landExplorerCommand);
        return ResponseEntity.ok(new AbstractMap.SimpleEntry<>("ExplorerId", landExplorerCommand.getExplorerId()));
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExplorerSetupException.class)
    public ExceptionMessage explorerSetupExceptionHandler(HttpServletRequest req, Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionMessage(exception.getMessage());
    }
}
