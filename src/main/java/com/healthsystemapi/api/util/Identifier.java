/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.healthsystemapi.api.util;

/**
 *
 * @author dhanu
 */

import com.healthsystemapi.api.util.exception.CustomIDException;
import com.healthsystemapi.api.util.customenum.IDPrefix;
import java.util.UUID;

/**
 * Utility class for generating and validating unique identifiers (IDs).
 */
public class Identifier {

    /** The fixed length for all IDs. */
    public static final int idLength = 13;

    /**
     * Validates the format of the provided ID against the expected prefix and length.
     * @param id the ID string to validate
     * @param idPrefix the expected prefix for the ID
     * @throws CustomIDException if the ID format is invalid
     */
    public static void validateID(String id, IDPrefix idPrefix) throws CustomIDException {
        if (id.length() != idLength) {
            throw CustomIDException.idFormatInvalid();
        }

        if (!id.startsWith(idPrefix.name())) {
            throw CustomIDException.idFormatInvalid();
        }
    }

    /**
     * Generates a unique ID with the specified prefix.
     * @param prefix the prefix for the ID
     * @return a unique ID string
     */
    public static String generateID(IDPrefix prefix) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);
        return prefix + uuid.toUpperCase();
    }
}
