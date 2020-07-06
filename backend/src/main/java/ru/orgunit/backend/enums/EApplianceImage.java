package ru.orgunit.backend.enums;

public enum EApplianceImage {
    UNKNOWN("unknown", "unknown", 0),
    SUN("light_classic","sun", 0),
    BRIGHTER_SUN("light_classic", "bright_sun", 1),
    SOUND("classic_sound", "sound", 0),
    BRIGHTER_SOUND("classic_sound", "bright_sound", 1),
    THERMOMETER("temperature_alternative", "thermometer", 0),
    THERMOMETER_CLASSIC("temperature_classic", "thermometer", 0),
    BRIGHT_THERMOMETER("temperature_alternative", "bright_thermometer", 1),
    BRIGHT_THERMOMETER_CLASSIC("temperature_classic", "bright_thermometer", 1)
    ;

    private String deviceType;

    private String imageType;

    private int active;

    EApplianceImage(String deviceType, String imageType, int value) {
        this.deviceType = deviceType;
        this.imageType = imageType;
        this.active = value;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getImageType() {
        return imageType;
    }

    public int getValue() {
        return active;
    }

    public static EApplianceImage getImageTypeByApplianceAndValue (String applianceType, int active) {
        for (EApplianceImage applianceImage : values()) {
            if (applianceImage.active == active && applianceImage.deviceType.equals(applianceType)) {
                return applianceImage;
            }
        }

        return UNKNOWN;
    }

}
