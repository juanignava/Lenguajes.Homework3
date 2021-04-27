
public abstract class ObjectFactory {

    // Attributes for ObjectFactory instance
    public Integer positionX;
    public Integer positionY;

    /**
     * Description: constructor method.
     */
    public ObjectFactory(Integer positionX, Integer positionY) {

        this.positionX = positionX;
        this.positionY = positionY;

    }

    /**
     * Description: returns X position of ObjectFactory.
     */
    public Integer getPositionX() {

        return positionX;

    }

    /**
     * Description: sets X position to DonkeyKongJr.
     * @param positionX
     */
    public void setPositionX(Integer positionX) {

        this.positionX += positionX;

    }

    /**
     * Description: returns Y position of DonkeyKongJr.
     */
    public Integer getPositionY() {

        return positionY;

    }

    /**
     * Description: sets Y position to DonkeyKongJr.
     * @param positionY
     */
    public void setPositionY(Integer positionY) {

        this.positionY += positionY;

    }

}
