/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hec.soar.tuneup.v1.exceptions;

public class WrongFormatException extends Exception {

    private final String message;

    public WrongFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}