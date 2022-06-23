package ch.aaap.assignment.raw;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CSVPostalCommunity {

    // PLZ4
    private String zipCode;

    // PLZZ
    private String zipCodeAddition;

    // PLZNAMK
    private String name;

    // KTKZ
    private String cantonCode;

    // GDENR
    private String politicalCommunityNumber;

    // GDENAMK
    private String politicalCommunityShortName;

    public PostalCommunity getPostalCommunity(Set<String> politicalCommunityNumbers) {
        PostalCommunity PostalCommunity = new PostalCommunity(zipCode, zipCodeAddition, name, politicalCommunityNumbers);
        return PostalCommunity;
    }
}
