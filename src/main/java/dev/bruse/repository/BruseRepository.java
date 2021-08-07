package dev.bruse.repository;

import dev.bruse.model.Bruse;

public interface BruseRepository {

    Bruse getBruse(final String gamePin);

}
