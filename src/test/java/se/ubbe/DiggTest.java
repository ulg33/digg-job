package se.ubbe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.ubbe.model.DiggUser;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiggTest {
    Digg digg;
    @BeforeEach
    void beforeEach(){
        digg= new Digg();
    }

    @Test
    void list() {
        assertEquals(Digg.NROF_GENERATED_USERS,digg.list(0,Integer.MAX_VALUE).size());
    }

    @Test
    void list_paginate() {
        assertEquals(2,digg.list(1,2).size());
    }

    @Test
    void add() {
        int nroUsers=digg.list(0,Integer.MAX_VALUE).size();
        assertEquals(nroUsers+1,digg.add(DiggUser.generateDiggUser()).size());

    }

    @Test
    void put() {
        Set<DiggUser> users = digg.list(0,Integer.MAX_VALUE);
        int nroUsers = users.size();
        DiggUser diggUserToUpdate = users.iterator().next();
        diggUserToUpdate.name="Kalle anka";
        diggUserToUpdate.telephone="11111";
        diggUserToUpdate.address="ankeborgsvägen";
        assertEquals(nroUsers,digg.put(diggUserToUpdate).size());
        DiggUser diggUserwithEmail = digg.list(0,Integer.MAX_VALUE).stream().filter(du->du.email.equals(diggUserToUpdate.email)).findFirst().get();
        assertEquals(diggUserToUpdate.name,diggUserwithEmail.name);
        assertEquals(diggUserToUpdate.telephone,diggUserwithEmail.telephone);
        assertEquals(diggUserToUpdate.address,diggUserwithEmail.address);


    }

    @Test
    void delete() {
        Set<DiggUser> users = digg.list(0,Integer.MAX_VALUE);
        int nroUsers = users.size();
        DiggUser diggUserToDelete = users.iterator().next();

        assertEquals(nroUsers-1,digg.delete(diggUserToDelete).size());
    }


}