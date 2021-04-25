

public class Fruit {

    // Attributes for Fruit { instance
    private Integer value;
    private Integer positionX;
    private Integer positionY;

    // Constant
    private static final Integer POINTS = 100;

    /**
     * Description: constructor method.
     * param positionX
     * @param positionY
     */
    public Fruit(Integer positionX, Integer positionY) {

        this.value = POINTS;
        this.positionX = positionX;
        this.positionY = positionY;

    }

    /**
     * Description: returns value of fruit.
     */
    public Integer getValue() {

        return value;

    }

    /**
     * Description: returns X position of fruit.
     */
    public Integer getPositionX() {

        return positionX;

    }

    /**
     * Description: sets X position to fruit.
     * @param positionX
     */
    public void setPositionX(Integer positionX) {

        this.positionX += positionX;

    }

    /**
     * Description: returns Y position of fruit.
     */
    public int getPositionY() {

        return positionY;

    }

    /**
     * Description: sets Y position to fruit.
     * @param positionY
     */
    public void setPositionY(Integer positionY) {

        this.positionY += positionY;

    }

}
