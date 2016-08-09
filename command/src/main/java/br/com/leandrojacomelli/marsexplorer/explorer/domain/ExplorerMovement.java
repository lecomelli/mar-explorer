package br.com.leandrojacomelli.marsexplorer.explorer.domain;

public enum ExplorerMovement {
    TURN_LEFT {
        @Override
        public void apply(Explorer explorer) {
            explorer.turnLeft();
        }
    }, TURN_RIGHT {
        @Override
        public void apply(Explorer explorer) {
            explorer.turnRight();
        }
    }, MOVE_FORWARD {
        @Override
        public void apply(Explorer explorer) {
            explorer.moveForward();
        }
    };

    public static ExplorerMovement from(String movement) {
        switch (movement) {
            case "L":
                return TURN_LEFT;
            case "R":
                return TURN_RIGHT;
            case "M":
                return MOVE_FORWARD;
            default:
                throw new RuntimeException("Invalid entry");
        }
    }


    public abstract void apply(Explorer explorer);
}
