package adventofcode2018;

/**
 *
 * @author Lukas Thöni
 */
public class Claim {

    private final int ID, xPos, yPos, xSize, ySize;

    public Claim(String specs) {
        String[] stringData = specs.replaceAll("[^\\d \\s]", " ").split("[\\s]+");
        int[] intData = new int[stringData.length];
        for (int i = 0; i < stringData.length; i++) {
            if (!stringData[i].equals("")) {
                intData[i] = Integer.parseInt(stringData[i]);
            }
        }
        this.ID = intData[1];
        this.xPos = intData[2];
        this.yPos = intData[3];
        this.xSize = intData[4];
        this.ySize = intData[5];
    }

    public Claim(int ID, int xPos, int yPos, int xSize, int ySize) {
        this.ID = ID;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public Claim() {
        this(0, 0, 0, 0, 0);
    }

    public int getID() {
        return ID;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

}
