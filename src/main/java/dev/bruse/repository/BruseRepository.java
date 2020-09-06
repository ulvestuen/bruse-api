package dev.bruse.repository;

import dev.bruse.model.Bruse;

public interface BruseRepository {

    public Bruse getBruse(final String gamePin);

}
