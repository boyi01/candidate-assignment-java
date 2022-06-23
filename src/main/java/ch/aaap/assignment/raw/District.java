package ch.aaap.assignment.raw;

import java.util.Objects;

public class District implements ch.aaap.assignment.model.District {

    private String Number;
    private String Name;
    private String CantonCode;

    public District(String number, String name, String cantonCode) {
        Number = number;
        Name = name;
        CantonCode = cantonCode;
    }

    @Override
    public String getNumber() {
        return Number;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getCantonCode() {
        return CantonCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return Number.equals(district.Number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Number);
    }
}
