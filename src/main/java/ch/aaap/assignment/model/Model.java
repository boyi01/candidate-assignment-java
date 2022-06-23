package ch.aaap.assignment.model;

import java.util.Set;

public interface Model {

    public Set<PoliticalCommunity> getPoliticalCommunities();

    public Set<PostalCommunity> getPostalCommunities();

    public Set<Canton> getCantons();

    public Set<District> getDistricts();

    public void addPoliticalCommunities(PoliticalCommunity politicalCommunities);

    public void addDistricts(District districts);

    public void addCantons(Canton cantons);

    public void addPostalCommunities(PostalCommunity postalCommunities);


}
