
public class Alligator extends ObjectFactory {

    // Attributes for Alligator instance
    private String color;
    private Integer speed;
    private Integer direction = 1;

    /**
     * Description: constructor method.
     * param positionX
     * @param positionY
     * @param speed
     */
    public Alligator(String color, int positionX, int positionY, int speed) {

        super(positionX, positionY);

        this.color = color;
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

        if(this.positionX!=0) {

            Integer futurePosition = this.positionY + positionY * this.direction;

            if (futurePosition < 600 && futurePosition > 35) {

                this.positionY += positionY * direction;

            } else if ((futurePosition >= 600 && this.color == "r")) {

                this.direction = -1;
                this.positionY += positionY * direction;

            } else if ((futurePosition <= 35 && this.color == "r")) {

                this.direction = 1;
                this.positionY += positionY * direction;

            } else {

                this.positionX = 0;
                this.positionY = 0;

            }

        }

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
