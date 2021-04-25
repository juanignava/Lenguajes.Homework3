
public class DonkeyKongJr {

    // Attributes for DonkeyKongJr instance
    private Integer positionX;
    private Integer positionY;
    private Integer lifes;
    private Integer score;

    /**
     * Description: constructor method.
     * @param positionX
     * @param positionY
     * @param lifes
     * @param score
     */
    public DonkeyKongJr(Integer positionX, Integer positionY, Integer lifes, Integer score) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.lifes = lifes;
        this.score = score;

    }

    /**
     * Description: returns X position of DonkeyKongJr.
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

    /**
     * Description: returns lifes of DonkeyKongJr.
     */
    public Integer getLifes() {

        return lifes;

    }

    /**
     * Description: sets lifes to DonkeyKongJr.
     * @param lifes
     */
    public void setLifes(Integer lifes) {

        this.lifes += lifes;

    }

    /**
     * Description: returns score of the DonkeyKongJr.
     */
    public Integer getScore() {

        return score;

    }

    /**
     * Description: sets score to DonkeyKongJr.
     * @param score
     */
    public void setScore(Integer score) {

        this.score += score;

    }

}
