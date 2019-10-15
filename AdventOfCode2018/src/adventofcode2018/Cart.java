package adventofcode2018;

/**
 *
 * @author Lukas Thöni
 */
public class Cart implements Comparable {

    private int x, y, noOfTurns, currentDirection;

    public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    public Cart(int x, int y, char currentDirection) {
        this.x = x;
        this.y = y;
        noOfTurns = 0;
        switch (currentDirection) {
            case '^':
                this.currentDirection = UP;
                break;
            case 'v':
                this.currentDirection = DOWN;
                break;
            case '<':
                this.currentDirection = LEFT;
                break;
            case '>':
                this.currentDirection = RIGHT;
                break;
        }
    }

    public void executeStep(char nextSquare) {
        move();
        switch (nextSquare) {
            case '-':
            case '|':
            case '^':
            case 'v':
            case '<':
            case '>':
                break;
            case '/':
                currentDirection = 3 - currentDirection;
                break;
            case '\\':
                currentDirection = (currentDirection + 2) % 4;
                break;
            case '+':
                currentDirection = getCrossingDirection();
        }
    }

    private int getCrossingDirection() {
        int result = 0;
        switch (noOfTurns) {
            case 0:
                switch (currentDirection) {
                    case UP:
                        result = LEFT;
                        break;
                    case DOWN:
                        result = RIGHT;
                        break;
                    case LEFT:
                        result = DOWN;
                        break;
                    case RIGHT:
                        result = UP;
                        break;
                }
                break;
            case 1:
                result = currentDirection;
                break;
            case 2:
                switch (currentDirection) {
                    case UP:
                        result = RIGHT;
                        break;
                    case DOWN:
                        result = LEFT;
                        break;
                    case LEFT:
                        result = UP;
                        break;
                    case RIGHT:
                        result = DOWN;
                        break;
                }
                break;
        }
        noOfTurns = ++noOfTurns % 3;
        return result;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void move() {
        switch (currentDirection) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Cart)) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } else {
            Cart other = (Cart) o;
            if (this == other) {
                return 0;
            }
            if (this.y < other.y) {
                return -1;
            } else if (this.y == other.y && this.x < other.x) {
                return -1;
            } else if (this.y == other.y && this.x == other.x) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
