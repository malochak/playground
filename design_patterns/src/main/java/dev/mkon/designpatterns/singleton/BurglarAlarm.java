package dev.mkon.designpatterns.singleton;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

import java.util.logging.Logger;

public class BurglarAlarm {

  Logger log = Logger.getLogger(BurglarAlarm.class.getName());

  private static BurglarAlarm instance;

  private static final int MAX_TRIES = 3;

  private int tries = 0;

  private int pin;

  private boolean isArmed;

  private boolean isTriggered;

  private BurglarAlarm(int pin) {
    this.pin = pin;
  }

  public static BurglarAlarm getInstance(int pin) {
    if (instance == null) {
      instance = new BurglarAlarm(pin);
    }
    return instance;
  }

  public boolean arm(int pin) {
    if (this.pin == pin) {
      isArmed = true;
      tries = 0;
      log.log(INFO, "Armed");
      return true;
    } else {
      log.log(WARNING, "WRONG PIN '" + tries + "' times");
      checkTries();
      return false;
    }
  }

  public boolean disarm(int pin) {
    if (this.pin == pin) {
      isArmed = false;
      isTriggered = false;
      tries = 0;
      log.log(INFO, "Disarmed");

      return true;
    } else {
      log.log(WARNING, "WRONG PIN '" + tries + "' times");
      checkTries();

      return false;
    }
  }

  private void checkTries() {
    tries++;
    if (tries >= MAX_TRIES && !isTriggered) {
      log.log(SEVERE, "ALARM TRIGGERED - only " + MAX_TRIES + " tries allowed");
      isTriggered = true;
    }

    if (isTriggered) {
      log.log(SEVERE, "To turn off the alarm - disarm with correct pin ");
    }
  }

  public boolean isArmed() {
    return isArmed;
  }

  public boolean isTriggered() {
    return isTriggered;
  }
}
