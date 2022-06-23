package ch.aaap.assignment.raw;

import java.util.Objects;
import java.util.Set;

public class PostalCommunity implements ch.aaap.assignment.model.PostalCommunity {
    private final String ZipCode;
    private final String ZipCodeAddition;
    private final String Name;

    private final Set<String> PoliticalCommunityNumber;

    public PostalCommunity(String zipCode, String zipCodeAddition, String name, Set<String> politicalCommunityNumber) {
        ZipCode = zipCode;
        ZipCodeAddition = zipCodeAddition;
        Name = name;
        PoliticalCommunityNumber = politicalCommunityNumber;
    }

    @Override
    public String getZipCode() {
        return ZipCode;
    }

    @Override
    public String getZipCodeAddition() {
        return ZipCodeAddition;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public Set<String> getPoliticalCommunityNumber() {
        return PoliticalCommunityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostalCommunity that = (PostalCommunity) o;
        return ZipCode.equals(that.ZipCode) && ZipCodeAddition.equals(that.ZipCodeAddition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ZipCode, ZipCodeAddition);
    }
}
