package ch.aaap.assignment.raw;

import ch.aaap.assignment.model.Canton;
import ch.aaap.assignment.model.District;
import ch.aaap.assignment.model.PoliticalCommunity;
import ch.aaap.assignment.model.PostalCommunity;

import java.util.HashSet;
import java.util.Set;

public class Model implements ch.aaap.assignment.model.Model {
    Set<PoliticalCommunity> PoliticalCommunities = new HashSet<PoliticalCommunity>();
    Set<PostalCommunity> PostalCommunities = new HashSet<PostalCommunity>();
    Set<Canton> Cantons = new HashSet<Canton>();
    Set<District> Districts = new HashSet<District>();

    public void addPoliticalCommunities(PoliticalCommunity politicalCommunity) {
        PoliticalCommunities.add(politicalCommunity);
    }

    public void addPostalCommunities(PostalCommunity postalCommunity) {
        PostalCommunities.add(postalCommunity);
    }

    public void addCantons(Canton canton) {
        Cantons.add(canton);
    }

    public void addDistricts(District district) {
        Districts.add(district);
    }

    @Override
    public Set<PoliticalCommunity> getPoliticalCommunities() {
        return PoliticalCommunities;
    }

    @Override
    public Set<PostalCommunity> getPostalCommunities() {
        return PostalCommunities;
    }

    @Override
    public Set<Canton> getCantons() {
        return Cantons;
    }

    @Override
    public Set<District> getDistricts() {
        return Districts;
    }

}
