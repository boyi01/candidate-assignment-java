package ch.aaap.assignment.raw;

import java.util.Objects;

public class Canton implements ch.aaap.assignment.model.Canton {

    private String Code;
    private String Name;

    public Canton(String code, String name) {
        Code = code;
        Name = name;
    }

    @Override
    public String getCode() {
        return Code;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Canton canton = (Canton) o;
        return Code.equals(canton.Code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Code);
    }
}
