package mods.railcraft.common.blocks.machine.engine;

public enum EngineType {

    HOBBY("hobby"),
    LOW("low"),
    HIGH("high");

    String name;

    private EngineType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
