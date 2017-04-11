package org.sqg;

/**
 *
 * As it says the "bit order".
 * <p>
 * 0x2 = 0b00000010
 * </p>
 *
 * @author samuel
 *
 */
public enum BitOrder {

    /**
     * The first bit is least significant bit, the last is the most significant
     * bit.
     */
    LM,

    /**
     * The first bit is most significant bit, the last is the least significant
     * bit.
     */
    ML,
}
