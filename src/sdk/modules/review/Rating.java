package sdk.modules.review;

import sdk.modules.Notification;

public class Rating {
    private float averageRating;
    private int countOfOneStar;
    private int countOfTwoStars;
    private int countOfThreeStars;
    private int countOfFourStars;
    private int countOfFiveStars;
    private int count;
    private float totalRatings;

    public Rating()
    {
        this.averageRating= 0;
        count = 0;
        totalRatings = 0;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public int getCount()
    {
        return count;
    }

    public void subtractCount()
    {
        count--;
    }

    public void addCount()
    {
        count++;
    }


    public void addSeparateCount(int noOfStars)
    {
        switch (noOfStars)
        {
            case 1 : countOfOneStar++;
            addCount();

                changeRating(noOfStars,true);
            break;
            case 2 : countOfTwoStars++;
                addCount();

                changeRating(noOfStars,true);
            break;
            case 3 : countOfThreeStars++;
                addCount();

                changeRating(noOfStars,true);
            break;
            case 4 : countOfFourStars++;
                addCount();

                changeRating(noOfStars,true);
            break;
            case 5 : countOfFiveStars++;
                addCount();

                changeRating(noOfStars,true);
            break;
            default:{

            }
        }
    }

    public void subtractSeparateCount(int noOfStars)
    {
        switch (noOfStars)
        {
            case 1 : countOfOneStar--;
                subtractCount();

                changeRating(noOfStars,false);
                break;
            case 2 : countOfTwoStars--;
                subtractCount();

                changeRating(noOfStars,false);
                break;
            case 3 : countOfThreeStars--;
                subtractCount();

                changeRating(noOfStars,false);
                break;
            case 4 : countOfFourStars--;
                subtractCount();

                changeRating(noOfStars,false);
                break;
            case 5 : countOfFiveStars--;
                subtractCount();

                changeRating(noOfStars,false);
                break;
            default:{

            }
        }
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public void addRating(int noOfStars)
    {
        switch (noOfStars)
        {
            case 1 : countOfOneStar++;
                count++;

                changeRating(noOfStars,true);
                break;
            case 2 : countOfTwoStars++;

                count++;
                changeRating(noOfStars,true);
                break;
            case 3 : countOfThreeStars++;

                count++;
                changeRating(noOfStars,true);
                break;
            case 4 : countOfFourStars++;
                count++;

                changeRating(noOfStars,true);
                break;
            case 5 : countOfFiveStars++;
                count++;

                changeRating(noOfStars,true);
                break;
            default: {

            }
        }
    }

    public void changeOldRating(int oldStars , int newStar)
    {
        if(newStar==oldStars)
        {
            return;
        }

            averageRating = (totalRatings - oldStars+newStar)/count;

    }

    private void changeRating(int noOfStars,boolean isAdd)
    {
        if(isAdd){
        averageRating = (totalRatings + noOfStars)/count;
        totalRatings += noOfStars;
        }
        else
        {
            averageRating = (totalRatings - noOfStars)/count;
            totalRatings -= noOfStars;
        }
    }

    public Notification sendRatingNotificationToGarage(int noOfStars)
    {
        Notification notification = new Notification("New Rating","Your garage was rated with a "+noOfStars+" star","Important");
        return notification;
    }

    @Override
    public String toString() {
        return "\n\t\tRating" +
                "\nAverage Rating ->" + averageRating +
                "\nCount Of One Star Ratings ->" + countOfOneStar +
                "\t\tCount Of One Star Ratings ->" + countOfTwoStars +
                "\nCount Of One Star Ratings ->" + countOfThreeStars +
                "\t\tCount Of One Star Ratings ->" + countOfFourStars +
                "\nCount Of One Star Ratings ->" + countOfFiveStars +
                "\nTotal count ->" + count ;
    }
}
