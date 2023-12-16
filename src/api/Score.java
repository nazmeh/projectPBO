package api;

public class Score {
    public static int userScore;

    public static int getScore() {
        return userScore;
    }

    public static void updateScore(int update) {
        userPrevScore=userScore;
        userScore+=update;
    }

    public static void setUserScore(int score){
        userScore=score;
    }
}
