package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    private final String labels;

    private RoomType(String labels) {
        this.labels = labels;
    }

    public static RoomType type(String label) {
        for (RoomType roomType :  values()) {
            if (roomType.labels.equals(label)) {
                return roomType;
            }
        }
        throw new IllegalArgumentException();
    }
}
