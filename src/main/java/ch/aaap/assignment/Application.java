package ch.aaap.assignment;

import ch.aaap.assignment.model.Model;
import ch.aaap.assignment.raw.CSVPoliticalCommunity;
import ch.aaap.assignment.raw.CSVPostalCommunity;
import ch.aaap.assignment.raw.CSVUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    private Model model = null;

    public Application() {
        initModel();
    }

    public static void main(String[] args) {
        new Application();
    }

    /**
     * Reads the CSVs and initializes a in memory model
     */
    private void initModel() {
        Set<CSVPoliticalCommunity> politicalCommunities = CSVUtil.getPoliticalCommunities();
        Set<CSVPostalCommunity> postalCommunities = CSVUtil.getPostalCommunities();
        model = new ch.aaap.assignment.raw.Model();
        politicalCommunities.forEach(element -> {
            model.addPoliticalCommunities(element.getPoliticalCommunity());
            model.addCantons(element.getCaton());
            model.addDistricts(element.getDistrict());
        });
        Map<String, Set<String>> postalCommunitiesaggregatet = postalCommunities.stream().collect(Collectors.groupingBy(elem -> elem.getZipCode() + elem.getZipCodeAddition(), Collectors.mapping(CSVPostalCommunity::getPoliticalCommunityNumber, Collectors.toSet())));
        postalCommunities.forEach(element -> {
            model.addPostalCommunities(element.getPostalCommunity(postalCommunitiesaggregatet.get(element.getZipCode() + element.getZipCodeAddition())));
        });
    }

    /**
     * @return model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param cantonCode of a canton (e.g. ZH)
     * @return amount of political communities in given canton
     */
    public long getAmountOfPoliticalCommunitiesInCanton(String cantonCode) {
        Set<String> districtCodes = getDisctrictCodesinCaton(cantonCode);
        Long AmountofPoliticalCommunities;
        AmountofPoliticalCommunities = districtCodes.stream().collect(Collectors.summingLong(districtCode -> getAmountOfPoliticalCommunitiesInDistrict(districtCode)));
        return AmountofPoliticalCommunities;
    }

    public Set<String> getDisctrictCodesinCaton(String cantonCode) {
        Set<String> districtCodes;
        districtCodes = model.getDistricts().stream().filter(element -> element.getCantonCode().equals(cantonCode)).map(element -> element.getNumber()).collect(Collectors.toSet());
        if (districtCodes.isEmpty()) {
            throw new IllegalArgumentException(cantonCode + "is no valid CantonCode");
        }
        return districtCodes;
    }

    /**
     * @param cantonCode of a canton (e.g. ZH)
     * @return amount of districts in given canton
     */
    public long getAmountOfDistrictsInCanton(String cantonCode) {
        return getDisctrictCodesinCaton(cantonCode).size();
    }

    /**
     * @param districtCode of a district (e.g. 101)
     * @return amount of districts in given canton
     */
    public long getAmountOfPoliticalCommunitiesInDistrict(String districtCode) {
        Long AmountofPoliticalCommunities = Long.valueOf(0);
        AmountofPoliticalCommunities += model.getPoliticalCommunities().stream().filter(element -> districtCode.equals(element.getDistrictCode())).count();
        if (AmountofPoliticalCommunities == 0) {
            throw new IllegalArgumentException(districtCode + "is no valid districtCode");
        }
        return AmountofPoliticalCommunities;
    }

    /**
     * @param zipCode 4 digit zip code
     * @return district that belongs to specified zip code
     */
    public Set<String> getDistrictsForZipCode(String zipCode) {
        Set<String> politicalCommunityNumbers;
        politicalCommunityNumbers = model.getPostalCommunities().stream().filter(element -> element.getZipCode().equals(zipCode)).map(element -> element.getPoliticalCommunityNumber()).flatMap(Collection::stream).collect(Collectors.toSet());
        Set<String> districts;
        districts = model.getPoliticalCommunities().stream().filter(element -> politicalCommunityNumbers.contains(element.getNumber())).map(element -> element.getDistrictCode()).collect(Collectors.toSet());
        Set<String> district_names;
        district_names = model.getDistricts().stream().filter(element -> districts.contains(element.getNumber())).map(element -> element.getName()).collect(Collectors.toSet());
        return district_names;
    }

    /**
     * @param postalCommunityName name
     * @return lastUpdate of the political community by a given postal community name
     */
    public LocalDate getLastUpdateOfPoliticalCommunityByPostalCommunityName(String postalCommunityName) {
        Set<String> politicalCommunityNumbers;
        politicalCommunityNumbers = model.getPostalCommunities().stream().filter(element -> element.getName().equals(postalCommunityName)).map(element -> element.getPoliticalCommunityNumber()).flatMap(Collection::stream).collect(Collectors.toSet());
        if (politicalCommunityNumbers.isEmpty()) {
            throw new IllegalArgumentException(postalCommunityName + " is no valid Postal Community Name");
        }
        LocalDate lastUpdate = model.getPoliticalCommunities().stream().filter(element -> politicalCommunityNumbers.contains(element.getNumber())).map(element -> element.getLastUpdate()).max(Comparator.comparing(LocalDate::toEpochDay)).orElseThrow(NoSuchElementException::new);
        return lastUpdate;
    }

    /**
     * https://de.wikipedia.org/wiki/Kanton_(Schweiz)
     *
     * @return amount of canton
     */
    public long getAmountOfCantons() {
        return model.getCantons().size();
    }

    /**
     * https://de.wikipedia.org/wiki/Kommunanz
     *
     * @return amount of political communities without postal communities
     */
    public long getAmountOfPoliticalCommunityWithoutPostalCommunities() {
        Set<String> postalCommunities;
        postalCommunities = model.getPostalCommunities().stream().map(element -> element.getPoliticalCommunityNumber()).flatMap(Collection::stream).collect(Collectors.toSet());
        Set<String> postal;
        Long amountofPoliticalCommunityWithoutPostalComnmunities;
        amountofPoliticalCommunityWithoutPostalComnmunities = model.getPoliticalCommunities().stream().filter(politicalCommunity -> !postalCommunities.contains(politicalCommunity.getNumber())).count();
        return amountofPoliticalCommunityWithoutPostalComnmunities;
    }
}
