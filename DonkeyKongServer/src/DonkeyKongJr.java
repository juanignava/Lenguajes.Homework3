import java.util.ArrayList;
import java.lang.Math;

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

        Integer futureValue = this.positionX+positionX;
        if ((futureValue > 50 && futureValue < 234 ||
            futureValue > 388 && futureValue < 602 ||
            futureValue > 756 && futureValue < 970) &&
            (this.positionY > 560 && this.positionY < 600)){
            this.positionX += positionX;
        }
        else if ((futureValue > 204 && futureValue < 408 ||
                futureValue > 572 && futureValue < 786) &&
                (this.positionY < 315 && this.positionY > 285)){
            this.positionX += positionX;
        }
        else if (futureValue > 756 && futureValue < 950  &&
                (this.positionY < 170 && this.positionY > 150)){
            this.positionX += positionX;
        }
        

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

        Integer futureValue = this.positionY + positionY;

        if((futureValue > 50 && futureValue < 600) &&
            ((this.positionX > 194 && this.positionX < 224) ||
              (this.positionX > 378 && this.positionX < 408) ||
              (this.positionX > 562 && this.positionX < 592) ||
              (this.positionX > 746 && this.positionX < 776)) ){
                  
            this.positionY += positionY;
        }



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

    void colisions(ArrayList<Fruit> fruits1, ArrayList<Alligator> alligators1){
        for (int j = 0; j < fruits1.size(); j++) {
            if(fruits1.get(j) != null){
                if (Math.abs(fruits1.get(j).getPositionX()-this.positionX) < 20 &&
                    Math.abs(fruits1.get(j).getPositionY() -this.positionY) < 20){
                        fruits1.get(j).setPositionX(-1*fruits1.get(j).getPositionX());
                        fruits1.get(j).setPositionY(-1*-1*fruits1.get(j).getPositionY());
                        this.lifes+=1;
                    }
            }
        }

        for (int k = 0; k < alligators1.size(); k++) {
            if(alligators1.get(k) != null){
                if (Math.abs(alligators1.get(k).getPositionX()-this.positionX) < 30 &&
                    Math.abs(alligators1.get(k).getPositionY() -this.positionY) < 30){
                        alligators1.get(k).setPositionX(-1*alligators1.get(k).getPositionX());
                        alligators1.get(k).setPositionY(-1*-1*alligators1.get(k).getPositionY());
                        this.lifes-=1;
                    }
            }
        }

    }

}
