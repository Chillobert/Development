
package de.hsa.games.fatsquirrel.core;


public class NotEnoughEnergyException extends Exception {

    public NotEnoughEnergyException() {
    }
    public NotEnoughEnergyException(String msg) {
        super(msg);
    }
}
