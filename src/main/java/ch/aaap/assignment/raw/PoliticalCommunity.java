package ch.aaap.assignment.raw;

import java.time.LocalDate;
import java.util.Objects;

public class PoliticalCommunity implements ch.aaap.assignment.model.PoliticalCommunity {

    private String Number;
    private String Name;
    private String ShortName;
    private LocalDate LastUpdate;
    private String DistrictCode;


    public PoliticalCommunity(String number, String name, String shortName, LocalDate lastUpdate, String districtCode) {
        Number = number;
        Name = name;
        ShortName = shortName;
        LastUpdate = lastUpdate;
        DistrictCode = districtCode;
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
    public String getShortName() {
        return ShortName;
    }

    @Override
    public LocalDate getLastUpdate() {
        return LastUpdate;
    }

    @Override
    public String getDistrictCode() {
        return DistrictCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliticalCommunity community = (PoliticalCommunity) o;
        return Number.equals(community.Number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Number);
    }
}
