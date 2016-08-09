package br.com.leandrojacomelli.marsexplorer.explorer.domain;

public enum Direction {
    NORTH {
        @Override
        public Direction turnLeft() {
            return WEST;
        }

        @Override
        public Direction turnRight() {
            return EAST;
        }
    }, SOUTH {
        @Override
        public Direction turnLeft() {
            return EAST;
        }

        @Override
        public Direction turnRight() {
            return WEST;
        }
    }, WEST {
        @Override
        public Direction turnLeft() {
            return SOUTH;
        }

        @Override
        public Direction turnRight() {
            return NORTH;
        }
    }, EAST {
        @Override
        public Direction turnLeft() {
            return NORTH;
        }

        @Override
        public Direction turnRight() {
            return SOUTH;
        }
    };

    public static Direction from(String value) {
        switch (value) {
            case "E":
                return EAST;
            case "W":
                return WEST;
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            default:
                throw new RuntimeException("Invalid Entry");

        }
    }

    public abstract Direction turnLeft();

    public abstract Direction turnRight();
}
