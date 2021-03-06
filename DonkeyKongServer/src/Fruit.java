

public class Fruit extends ObjectFactory {

    // Attributes for Fruit instance
    private Integer value;

    /**
     * Description: constructor method.
     * param positionX
     * @param positionY
     */
    public Fruit(Integer positionX, Integer positionY) {

        super(positionX, positionY);

        this.value = Constants.POINTS;

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
    public Integer getPositionY() {

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
