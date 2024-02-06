package se.ubbe;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import se.ubbe.model.DiggUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Path("/digg/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Digg {

    final private SortedSet<DiggUser> diggUsers = Collections.synchronizedSortedSet(new TreeSet<DiggUser>());

    public static final int NROF_GENERATED_USERS = 50;

    public Digg() {
        init();
    }

    public void init() {
        for (int i = 0; i < NROF_GENERATED_USERS; ++i) {
            DiggUser generatedUser = DiggUser.generateDiggUser();
            while (diggUsers.contains(generatedUser)) {
                generatedUser = DiggUser.generateDiggUser();
            }
            diggUsers.add(generatedUser);
        }
    }


    @GET
    public Set<DiggUser> list(@DefaultValue("0") @QueryParam("start") Integer start, @DefaultValue("2") @QueryParam("nrtoReturn") Integer nrtoReturn) {
        ArrayList<DiggUser> toReturn = new ArrayList<>(diggUsers);
        int startIndex = start <= toReturn.size() ? start : toReturn.size();
        int stopIndex = Math.min(nrtoReturn + start, toReturn.size());

        return new LinkedHashSet<>(toReturn.subList(startIndex, stopIndex));
    }


    @POST
    public Set<DiggUser> add(DiggUser diggUser) {
        diggUsers.add(diggUser);
        return diggUsers;
    }

    @PUT
    public Set<DiggUser> put(DiggUser diggUser) {
        if (diggUsers.contains(diggUser)) {
            diggUsers.remove(diggUser);
            diggUsers.add(diggUser);
        }

        return diggUsers;
    }

    @DELETE
    public Set<DiggUser> delete(DiggUser diggUser) {
        diggUsers.remove(diggUser);
        return diggUsers;
    }

}
