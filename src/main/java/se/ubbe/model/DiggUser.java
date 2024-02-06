package se.ubbe.model;

import io.smallrye.common.constraint.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class DiggUser implements Comparable {

    private static List<String> maleGivenNames = Arrays.asList("Per","Nils","Karl","Emil","Viktor","Urban","Jonas","Johan","Simon","Josef","Joel","Markus","Ulf","Anders") ;
    private static List<String> femaleGivenNames =Arrays.asList("Anna","Maria","Johanna","Eva","Gun","Sara","Jenny","Katarina","Maja","Sophie","Lena") ;


    private static List<String> givenNames = Stream.concat(maleGivenNames.stream(), femaleGivenNames.stream()).toList();
    public String name;
    public String address;
    @NotNull
    public String email;
    public String telephone;

    public DiggUser(){
    }

    public static DiggUser generateDiggUser(){
        String firstName = givenNames.get(ThreadLocalRandom.current().nextInt(givenNames.size()));
        String lastName = maleGivenNames.get(ThreadLocalRandom.current().nextInt(maleGivenNames.size()))+"son";
        DiggUser diggUser = new DiggUser();

        diggUser.name =firstName+" "+ lastName;
        diggUser.address="Storgatan "+ThreadLocalRandom.current().nextInt(1,100);
        diggUser.telephone="060-"+ThreadLocalRandom.current().nextInt(100300,550500);
        diggUser.email=String.format("%s.%s@digg.se",firstName,lastName);
        return diggUser;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiggUser diggUser = (DiggUser) o;
        return Objects.equals(email, diggUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "DiggUser{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.email.compareTo(((DiggUser)o).email);
    }
}
