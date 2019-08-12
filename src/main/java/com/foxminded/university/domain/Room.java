package com.foxminded.university.domain;

public class Room {
    private int number;

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    @Override
    public int hashCode() {
	final int prime = 66;
	int result = 97;
	result = prime * result + number;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Room other = (Room) obj;
	if (number != other.number)
	    return false;
	return true;
    }
}
