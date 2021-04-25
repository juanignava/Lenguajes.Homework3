

public class Alligator {

    // Attributes for Alligator instance
    private String color;
    private Integer positionX;
    private Integer positionY;
    private Integer speed;

    /**
     * Description: constructor method.
     * param positionX
     * @param positionY
     * @param speed
     */
    public Alligator(String color, int positionX, int positionY, int speed) {

        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;

    }

    /**
     * Description: returns color of alligator.
     */
    public String getColor() {

        return color;

    }

    /**
     * Description: returns X position of alligator.
     */
    public Integer getPositionX() {

        return positionX;

    }

    /**
     * Description: sets X position to alligator.
     * @param positionX
     */
    public void setPositionX(Integer positionX) {

        this.positionX += positionX;

    }

    /**
     * Description: returns Y position of alligator.
     */
    public Integer getPositionY() {

        return positionY;

    }

    /**
     * Description: sets Y position to alligator.
     * @param positionY
     */
    public void setPositionY(Integer positionY) {

        this.positionY += positionY;

    }

    /**
     * Description: returns speed of alligator.
     */
    public Integer getSpeed() {

        return speed;

    }

    /**
     * Description: sets speed to alligator.
     * @param speed
     */
    public void setSpeed(Integer speed) {

        this.speed += speed;

    }

}
