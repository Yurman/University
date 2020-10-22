package com.yurman.university.domain;

public class Professor extends Person {

    private int id;
    private String degree;
    private String position;
    private Department department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((degree == null) ? 0 : degree.hashCode());
        result = prime * result + ((department == null) ? 0 : department.hashCode());
        result = prime * result + id;
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Professor other = (Professor) obj;
        if (degree == null) {
            if (other.degree != null) {
                return false;
            }
        } else if (!degree.equals(other.degree))
            return false;
        if (department == null) {
            if (other.department != null) {
                return false;
            }
        } else if (!department.equals(other.department))
            return false;
        if (id != other.id)
            return false;
        if (position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!position.equals(other.position))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", " + (degree != null ? "degree=" + degree + ", " : "")
                + (position != null ? "position=" + position + ", " : "")
                + (department != null ? "department=" + department : "") + "]";
    }
}
