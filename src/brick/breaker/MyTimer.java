package brick.breaker;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    public int secondsRight;
    public int secondsLeft;
    public int minutes;
    public Timer timer;
    public TimerTask timerTask;

    private void timerFoo() {
        secondsRight = 0;
        secondsLeft = 0;
        minutes = 0;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                secondsRight++;
                if (secondsRight < 10) {
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                } else {
                    secondsRight = 0;
                    secondsLeft++;
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                }
                if (secondsLeft > 4 && secondsRight > 8) {
                    secondsRight = 0;
                    secondsLeft = 0;
                    minutes++;
                    System.out.println("Time passed: " + minutes + " : " + secondsLeft + secondsRight);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }


    public void start() {
        timerFoo();
    }

    public void stop() {
        //Timer needs to be recreated as another instance because timer.cancel kills the timer for good
        timer.cancel();
    }

    public void restart() {
        start();
    }
}
