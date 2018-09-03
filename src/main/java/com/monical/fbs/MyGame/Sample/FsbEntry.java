package com.monical.fbs.MyGame.Sample;

import com.google.flatbuffers.FlatBufferBuilder;

/**
 * @author zijie.cao
 * @date 2018-08-31 10:43:22
 */
public class FsbEntry {
    public static void main(String[] args) {

        FlatBufferBuilder builder = new FlatBufferBuilder();
        int weaponOneName = builder.createString("Sword");
        short weaponOneDamage = 3;

        int weaponTwoName = builder.createString("Axe");
        short weaponTwoDamage = 5;

        // Use the `createWeapon()` helper function to create the weapons, since we set every field.
        int sword = Weapon.createWeapon(builder, weaponOneName, weaponOneDamage);
        int axe = Weapon.createWeapon(builder, weaponTwoName, weaponTwoDamage);

        Weapon.endWeapon(builder);
    }
}
