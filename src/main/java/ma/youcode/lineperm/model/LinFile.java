package ma.youcode.lineperm.model;

import ma.youcode.lineperm.enums.Permission;


public class LinFile {
    private final String name;
    private final String owner;
    private Permission permission;


    public LinFile(String name, String owner,Permission permission) {
        this.name = name;
        this.owner = owner;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Permission getPermission() {
        return permission;
    }


    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    
}

